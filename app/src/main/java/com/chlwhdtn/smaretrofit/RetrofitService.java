package com.chlwhdtn.smaretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("/student/{path}")
    public Call<Data> getStudent(
        @Path("path") int number
    );

    @POST("/student/")
    @FormUrlEncoded
    public Call<Data> setStudent(
            @Field("number") int number,
            @Field("name") String name
    );
}
