package ir.beigirad.sample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by farhad-mbp on 3/30/18.
 */

public interface APIService {
    String BASE_URL = "https://khalsaiptv.net";
    @GET("/encrypt/livetv.json")
    Call<List<Person>> getPerson();
}
