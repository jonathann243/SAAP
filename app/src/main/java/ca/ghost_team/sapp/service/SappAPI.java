package ca.ghost_team.sapp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SappAPI {
    public static Retrofit api = null;

    public static Retrofit getApi(){
        if(api == null){
            Gson gson = new GsonBuilder().create();
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            api =  new Retrofit.Builder()
                    .baseUrl("http://192.168.0.38/sappserver/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }

        return api;
    }

}
