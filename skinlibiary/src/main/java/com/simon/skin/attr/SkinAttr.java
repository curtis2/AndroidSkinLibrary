package com.simon.skin.attr;

import android.view.View;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class SkinAttr
{
    private SkinAttrType skinAttrType;
    private String resName;

    public SkinAttr(com.simon.skin.attr.SkinAttrType skinAttrType, String resName) {
        this. skinAttrType = skinAttrType;
        this.resName = resName;
    }

    public void apply(View view) {
        skinAttrType.apply(view,resName);
    }
}
