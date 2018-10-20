package ir.beigirad.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import ir.beigirad.encryptedgsonconverter.GsonEncryptConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import se.simbio.encryption.Encryption;

import static ir.beigirad.sample.APIService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.lview);
        Encryption encryption = Encryption.getDefault("MyKey",
                "MySalt", new byte[16]);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("Network log", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonEncryptConverterFactory.create(encryption))
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Person>> call = apiService.getPerson();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(@NonNull Call<List<Person>> call, @NonNull Response<List<Person>> response) {
                List<Person> personList = response.body();
                assert personList != null;
                String[] ProductNames = new String[personList.size()];
                for (int i = 0; i<personList.size(); i++){
                    ProductNames[i] = personList.get(i).getName();
                }
                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                ProductNames
                        )
                );
            }

            @Override
            public void onFailure(@NonNull Call<List<Person>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Fail : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}



