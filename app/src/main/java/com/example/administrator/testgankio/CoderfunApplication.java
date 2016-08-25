package com.example.administrator.testgankio;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Administrator on 2016/6/18.
 */
public class CoderfunApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FlowManager.init(this);
    }
}
