package com.github.recycleviewrxjava;

import java.util.ArrayList;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface Service {

    @POST("GitHub/RXJava/post.php")
    @FormUrlEncoded
    Observable<User> savepost(@Field("name") String name,
                                          @Field("stats") String stats,
                                          @Field("namaPicture") String namaPicture,
                                          @Field("picture") String picture);

    @GET("/GitHub/RXJava/get.php/")
    Observable<ArrayList<User>> get();

    @POST("GitHub/RXJava/postandget.php")
    @FormUrlEncoded
    Observable<ArrayList<User>> postandget(@Field("name") String name,
                              @Field("stats") String stats,
                              @Field("namaPicture") String namaPicture,
                              @Field("picture") String picture);

}
