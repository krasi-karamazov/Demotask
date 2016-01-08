package com.kpkdev.youlocaltask.ui.networking;

import com.kpkdev.youlocaltask.ui.models.User;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by krasimir.karamazov on 1/6/2016.
 */
public interface YouLocalAPIInterface {

    @FormUrlEncoded
    @POST("/oauth2/2.0/signin")
    Call<User> getUserDetails(@Field("email") String email, @Field("password") String password);
}
