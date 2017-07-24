package com.simon.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.skin.ResourceManager;
import com.simon.skin.SkinManager;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public enum  SkinAttrType {
    BACKGROUND("background"){
        @Override
        public void apply(View view, String resName) {
           Drawable drawable=getResourceManager().getDrawableByResName(resName);
            if(drawable!=null){
                view.setBackgroundDrawable(drawable);
            }else{
                int color=getResourceManager().getColorByResName(resName);
                view.setBackgroundColor(color);
            }
        }
    },
    COLOR("textColor"){
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorList = getResourceManager().getColorStateList(resName);
            if(colorList==null) return;
            if(view instanceof TextView){
                ((TextView)view).setTextColor(colorList);
            }
        }
    },
    SRC("src"){
        @Override
        public void apply(View view, String resName) {
            Drawable drawable = getResourceManager().getDrawableByResName(resName);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(drawable);
            }
        }
    };

    String attrTypeName;

    SkinAttrType(String attrTypeName) {
        this.attrTypeName=attrTypeName;
    }

    public String getAttrTypeName() {
        return attrTypeName;
    }

    public abstract void apply(View view, String resName);


   public ResourceManager getResourceManager(){
         return SkinManager.getmInstance().getResourceManager();
   }

}
