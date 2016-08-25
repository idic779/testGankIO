package com.example.administrator.testgankio.retrofit;


import com.example.administrator.testgankio.model.DataResults;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/2.
 */
public interface CoderfunAPI {
    @GET("data/{type}/{number}/{page}")
    Observable<DataResults> getDataResults(
            @Path("type") String type,
            @Path("number") int number,
            @Path("page") int page
    );


}
