package com.kpkdev.youlocaltask.ui.networking;

import com.kpkdev.youlocaltask.ui.models.*;
import com.kpkdev.youlocaltask.ui.networking.events.ErrorEvent;
import com.kpkdev.youlocaltask.ui.networking.events.UserDetailsReadyEvent;
import com.kpkdev.youlocaltask.ui.utils.BusProvider;
import com.kpkdev.youlocaltask.ui.utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by krasimir.karamazov on 1/7/2016.
 */
public class APIClient {

    private static APIClient sInstance;
    private APIClient() {

    }

    public static synchronized APIClient getInstance() {
        if(sInstance == null) {
            sInstance = new APIClient();
        }
        return sInstance;
    }

    public Call<User> getUserDetails(String email, String password) {
        Call<User> call = ServiceCreator.createAPIService().getUserDetails(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                BufferedReader reader = null;
                if(response.body() == null) {
                    try{
                        reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                        StringBuilder builder = new StringBuilder();
                        String line = null;
                        while((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                        reader.close();
                        YouLocalError error = YouLocalGsonBuilder.gson().fromJson(builder.toString(), YouLocalError.class);
                        BusProvider.getInstance().post(new UserDetailsReadyEvent(null, error));
                    }catch(IOException e){
                        BusProvider.getInstance().post(new UserDetailsReadyEvent(null, null));
                    }finally {
                        if(reader != null) {
                            try{
                                reader.close();;
                            }catch(IOException e) {
                                BusProvider.getInstance().post(new UserDetailsReadyEvent(null, null));
                            }
                        }
                    }

                }else {
                    BusProvider.getInstance().post(new UserDetailsReadyEvent(response.body(), null));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                BusProvider.getInstance().post(new ErrorEvent());
            }
        });
        return call;
    }
}
