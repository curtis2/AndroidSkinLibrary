package com.simon.skin;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.simon.skin.attr.SkinAttrSupport;
import com.simon.skin.attr.SkinView;

import java.util.ArrayList;
import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class SkinManager {
    private ResourceManager mResourceManager;
    private List<Activity> mActivities;

    private SkinManager(){}

    public static SkinManager getmInstance(){
        return SingleHolder.mInstance;
    }

    public static class SingleHolder{
        static SkinManager mInstance =new SkinManager();
    }

    public void init(Context context){
        mResourceManager=new ResourceManager(null,context.getResources(),context.getPackageName());
    }

    public void register(final  Activity activity){
        if(mActivities==null){
            mActivities=new ArrayList<>();
        }
        mActivities.add(activity);
        activity.findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                apply(activity);
            }
        });
    }

    private void apply(Activity activity) {
        List<SkinView> skinViews=new ArrayList<>();
        View view=activity.findViewById(android.R.id.content);
        SkinAttrSupport.getSkinViews(view,skinViews);
        if(!skinViews.isEmpty()){
            for(SkinView sView:skinViews){
                sView.apply();
            }
        }
    }

    public void changeSkin(String suffix){
        mResourceManager.setmSuffix(suffix);
        notifyAllActivitiesChangeShin();
    }

    private void notifyAllActivitiesChangeShin() {
        for (Activity activity:mActivities){
             apply(activity);
        }
    }


    public void injectSkin(View convertView) {
        List<SkinView> skinViews=new ArrayList<>();
        SkinAttrSupport.getSkinViews(convertView,skinViews);
        if(!skinViews.isEmpty()){
            for(SkinView view:skinViews){
                view.apply();
            }
        }
    }

    public void unRegister(final  Activity activity){
        if(mActivities!=null&&mActivities.contains(activity)){
            mActivities.remove(activity);
        }
    }

    public ResourceManager getResourceManager() {
        return mResourceManager;
    }

}
