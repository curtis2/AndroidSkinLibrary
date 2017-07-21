package com.simon.skin.attr;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.simon.skin.R;
import com.simon.skin.constant.SkinConfig;

import java.util.ArrayList;
import java.util.List;
/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class SkinAttrSupport {

    public static List<SkinView> getSkinViews(Activity activity) {
        List<SkinView> skinViews=new ArrayList<>();
        View view= activity.findViewById(android.R.id.content);
        addSkinViews(view,skinViews);
        return  skinViews;
    }


    private static void addSkinViews(View view, final List<SkinView> skinViews) {
        SkinView skinView=getSkinView(view);
        if(skinView!=null)skinViews.add(skinView);
        if(view instanceof  ViewGroup){
            ViewGroup container=(ViewGroup)view;
            for (int i = 0; i <container.getChildCount() ; i++) {
                View childAt = container.getChildAt(i);
                addSkinViews(childAt,skinViews);
            }
        }
    }

    private static SkinView getSkinView(View view) {
        Object tag = view.getTag(R.id.skin_tag_id);
         if(tag==null){
             tag=view.getTag();
         }
         if(tag==null)return null;

         if(!(tag instanceof String))return null;

         String tagStr=(String)tag;

         List<SkinAttr> skinAttrs=parseTag(tagStr);

         if(!skinAttrs.isEmpty()){
            changeViewTag(view);
             return new SkinView(view,skinAttrs);
         }
         return null;
    }

    private static void changeViewTag(View view) {
        Object tag = view.getTag(R.id.skin_tag_id);
        if(tag==null){
            tag=view.getTag();
            view.setTag(R.id.skin_tag_id,tag);
            view.setTag(null);
        }
    }

    private static List<SkinAttr> parseTag(String tagStr) {
        List<SkinAttr> skinAttrs=new ArrayList<>();
        if(TextUtils.isEmpty(tagStr))return skinAttrs;
        String[] items=tagStr.split("[|]");
        for(String item:items){
           if(!item.startsWith(SkinConfig.SKIN_PREFIX))continue;
            String[] resItems=tagStr.split("[|]");
            if(resItems.length!=3)continue;

            String resName=resItems[1];
            String resType=resItems[2];
            SkinAttrType attrType= getSupportAttrType(resType);
            SkinAttr attr=new SkinAttr(attrType,resName);
            if(attrType==null)continue;
            skinAttrs.add(attr);
        }
        return null;
    }

    private static SkinAttrType getSupportAttrType(String resType) {

        return null;
    }


}
