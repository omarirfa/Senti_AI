package android.example.recommender_app;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface JsonParser {
/*
This are for reference purposes to understand how GET calls and POST calls are made

    @GET("posts")
    Call<List<ParseContent>> getPosts(@Query("userId") Integer[] userId,@Query("_sort") String sort,@Query("_order") String order);

    @GET("posts")
    Call<List<ParseContent>> getPosts(@QueryMap Map<String,String> params);


    @GET("posts/{id}/comments")
    Call<List<ParseContent>> getPosts(@Path("id") int id);

 */
    @POST("/sentimenter")
    Call<String> sendContent(@Body String content);


}
