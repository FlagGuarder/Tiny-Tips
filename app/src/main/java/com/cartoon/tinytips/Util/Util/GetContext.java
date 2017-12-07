package com.cartoon.tinytips.Util.Util;

import android.app.Application;
import android.content.Context;

/**
 * Created by cartoon on 2017/10/25.
 */

public class GetContext extends Application{
    //全局获取Context

    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
