package com.example.administrator.testgankio.retrofit;


import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/6/5.
 */
public class CoderfunSingle {
    private volatile static CoderfunAPI CoderfunAPISingleton = null;

    public static CoderfunAPI getInstance() {
        if (CoderfunAPISingleton == null) {
//            synchronized (CoderfunSingle.class) {
//                if (CoderfunAPISingleton == null) {
//                    CoderfunAPISingleton = new CoderfunRetrofit().createService(CoderfunAPI.class);
//                }
//            }
            Retrofit retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            CoderfunAPISingleton = retrofit.create(CoderfunAPI.class);
        }


        return CoderfunAPISingleton;
    }
}
