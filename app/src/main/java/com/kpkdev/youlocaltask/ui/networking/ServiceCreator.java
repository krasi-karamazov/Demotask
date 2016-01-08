package com.kpkdev.youlocaltask.ui.networking;

import com.kpkdev.youlocaltask.ui.utils.Constants;
import com.kpkdev.youlocaltask.ui.utils.Logger;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by krasimir.karamazov on 1/6/2016.
 */
public class ServiceCreator {
    private static Retrofit retrofit;

    public static YouLocalAPIInterface createAPIService() {
        if(retrofit == null) {
            OkHttpClient httpClient = new OkHttpClient();
            if(Logger.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.interceptors().add(interceptor);
            }
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Constants.API_BASE_URL).addConverterFactory(GsonConverterFactory.create(YouLocalGsonBuilder.gson()));
            retrofit = builder.client(httpClient).build();
        }

        return retrofit.create(YouLocalAPIInterface.class);
    }
}
