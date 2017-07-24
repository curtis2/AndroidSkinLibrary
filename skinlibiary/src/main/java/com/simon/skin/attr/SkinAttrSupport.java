package com.simon.skin.attr;

import android.view.View;
import android.view.ViewGroup;

import com.simon.skin.contant.SkinConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class SkinAttrSupport {

    public static void getSkinViews(View view, List<SkinView> skinViews) {
        SkinView skinView= getSkinView(view);
        if(skinView!=null)skinViews.add(skinView);
        if(view instanceof ViewGroup){
            ViewGroup viewGroup= (android.view.ViewGroup) view;
             for (int i = 0; i <viewGroup.getChildCount(); i++) {
                 View childAt = viewGroup.getChildAt(i);
                 getSkinViews(childAt,skinViews);
             }
         }
    }

    private static  SkinView getSkinView(View view) {
        Object tag = view.getTag();
        if(tag==null)return null;
        if(!(tag instanceof String))return null;
        String tagStr= (String) tag;

        List<SkinAttr> skinAttrs=parseTag(tagStr);

        if(skinAttrs!=null&&!skinAttrs.isEmpty()){

            return  new SkinView(view,skinAttrs);
        }
        return  null;
    }

    private static List<SkinAttr> parseTag(String tagStr) {
        List<SkinAttr> skinAttrs=new ArrayList<>();
        String[] skinStrs= tagStr.split("[|]");
        if(skinStrs!=null&&skinStrs.length>0){
           for(String skinItemStr:skinStrs){
               if(skinItemStr.startsWith(SkinConfig.SKIN_PRESUFFIX)){
                   String[] items = skinItemStr.split(":");
                   if(items!=null&&items.length==3){
                       String resName=items[1];
                       String resType=items[2];
                       SkinAttrType SkinAttrType=getSupportType(resType);
                       SkinAttr attr=new SkinAttr(SkinAttrType,resName);
                       if(SkinAttrType!=null) skinAttrs.add(attr);
                   }
               }
           }
        }
        return skinAttrs;
    }

    private static SkinAttrType getSupportType(String resType) {
        for (SkinAttrType type:SkinAttrType.values()){
            if(type.getAttrTypeName().equals(resType)){
                return type;
            }
        }
        return null;
    }


}
