package com.simon.skin.attr;

import android.view.View;

import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class SkinView {
    private View view;
    private List<SkinAttr> skinAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.view = view;
        this.skinAttrs = skinAttrs;
    }
    public void apply() {
        if(view==null) return;
        for(SkinAttr attr:skinAttrs){
            attr.apply(view);
        }
    }
}
