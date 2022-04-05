package com.Dental.Check.Retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        return retrofit;
    }
    public  static INodeJS getVoisin(){
            INodeJS iNodeJS =getRetrofit().create(INodeJS.class);
            return iNodeJS;
    }
    public static INodeJS getAllConsultations()
    {
        INodeJS iNodeJS = getRetrofit().create(INodeJS.class);
                return iNodeJS;
    }


    public  static INodeJS getpatient(){
        INodeJS iNodeJS =getRetrofit().create(INodeJS.class);
        return iNodeJS;
    }
}
