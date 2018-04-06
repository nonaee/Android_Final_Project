package com.example.fernomazer.androidproject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FixerService {
    //@GET("latest access_key={access_key}")
    //Call<FixerObject> getLatest(@Query("access_key") String access_key);
    @GET("latest?access_key=07d3ae4dfadca745698d570b7cff098a")
    Call<FixerObject> getLatest();

}
