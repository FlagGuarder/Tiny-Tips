package com.cartoon.tinytips.Util.Util;

import android.util.Log;

/**
 * Created by cartoon on 2017/10/25.
 */

public class LogUtil {

    //调试工具类
    private static final int verbose=0;
    private static final int debug=1;
    private static final int info=2;
    private static final int warn=3;
    private static final int error=4;
    private static final int nothing=5;
    private static final int level=verbose;

    public static void v(String tag,String msg){
        if(level<=verbose){
            Log.v(tag, msg);
        }
    }
    public static void d(String tag,String msg){
        if(level<=debug){
            Log.d(tag, msg);
        }
    }
    public static void i(String tag,String msg){
        if(level<=info){
            Log.i(tag, msg);
        }
    }
    public static void w(String tag,String msg){
        if(level<=warn){
            Log.w(tag, msg);
        }
    }
    public static void e(String tag,String msg){
        if(level<=error){
            Log.e(tag, msg);
        }
    }
}
