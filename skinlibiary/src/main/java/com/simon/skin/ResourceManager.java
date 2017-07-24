package com.simon.skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class ResourceManager {

    private static String DEFAULT_TYPE_DRAWABLE="drawable";
    private static String DEFAULT_TYPE_COLOR="color";

    private String mSuffix;
    private Resources mResources;
    private String mPackName;

    public ResourceManager(String mSuffix, Resources mResources, String mPackName) {
        this.mResources = mResources;
        this.mPackName = mPackName;
        this.mSuffix = mSuffix;
    }

    public Drawable getDrawableByResName(String resName) {
        try {
            resName=appendmSuffix(resName);
          return  mResources.getDrawable(mResources.getIdentifier(resName, DEFAULT_TYPE_DRAWABLE,mPackName));
        }catch (Exception e){
            Log.i("","resName="+resName);
           e.printStackTrace();
        }
        return null;
    }


    public int getColorByResName(String resName) {
        try {
            resName=appendmSuffix(resName);
            return  mResources.getColor(mResources.getIdentifier(resName,DEFAULT_TYPE_COLOR,mPackName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public ColorStateList getColorStateList(String resName) {
        try {
            resName=appendmSuffix(resName);
            return  mResources.getColorStateList(mResources.getIdentifier(resName,DEFAULT_TYPE_COLOR,mPackName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setmSuffix(String mSuffix) {
        this.mSuffix = mSuffix;
    }

    private String appendmSuffix(String resName) {
        if(!TextUtils.isEmpty(mSuffix)){
            resName=resName+"_"+mSuffix;
        }
        return resName;
    }

}
