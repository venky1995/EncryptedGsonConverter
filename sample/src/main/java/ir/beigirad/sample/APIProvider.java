package ir.beigirad.sample;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import ir.beigirad.encryptedgsonconverter.GsonEncryptConverterFactory;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import se.simbio.encryption.Encryption;
import third.part.android.util.Base64;

public class APIProvider {
    private String BASE_URL = "https://khalsaiptv.net";

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private APIService apiService;

    public APIProvider() {

        // for more detail visit https://github.com/simbiose/Encryption
        Encryption encryption = Encryption.getDefault("SomeKey",
                "SomeSalt", new byte[16]);


        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new StethoInterceptor())
                .build();


        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonEncryptConverterFactory.create(encryption))
                .build();

        apiService = retrofit.create(APIService.class);

    }

    public APIService getApiService() {
        return apiService;
    }
}
