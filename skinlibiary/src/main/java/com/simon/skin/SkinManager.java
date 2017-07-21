package com.simon.skin;

import android.app.Activity;

import com.simon.skin.attr.SkinAttrSupport;
import com.simon.skin.attr.SkinView;

import java.util.ArrayList;
import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class SkinManager{

    private List<Activity> mActivitys;
    private SkinManager(){
    }

    public void register(final Activity activity) {
        addAcititys(activity);
        activity.findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                apply(activity);
            }
        });
    }

    public void addAcititys(Activity activity){
        if(mActivitys==null){
            mActivitys=new ArrayList<>();
        }
        mActivitys.add(activity);
    }


    private void apply(Activity activity) {
      //遍历所有的view
        List<SkinView> skinViews=SkinAttrSupport.getSkinViews(activity);
    }


    private static class SingletonHolder{
        static  SkinManager mInstance=new SkinManager();
    }

    public static SkinManager getmInstance(){
        return  SingletonHolder.mInstance;
    }








}
