package com.linchtech.myapplication.slice;

import com.linchtech.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;

public class SecondMainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
//        super.setUIContent(ResourceTable.Layout_ability_second_main);
        // 1创建布局对象
        DirectionalLayout directionalLayout = new DirectionalLayout(this);
        // 2创建文本对象
        Text t = new Text(this);
        t.setText("fff");
        t.setTextSize(55);
        t.setTextColor(Color.MAGENTA);
        // 文本添加到布局
        directionalLayout.addComponent(t);
        super.setUIContent(directionalLayout);
        System.out.println("--------------");
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
