package com.linchtech.myapplication.ability.slice;

import com.linchtech.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

public class LoginAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    Text text;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_login);

         text = (Text) findComponentById(ResourceTable.Id_forgotPassword);
         text.setClickedListener(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("") // 要跳转的设备
                .withBundleName("com.linchtech.myapplication") // 要跳转的应用
                .withAbilityName("com.linchtech.myapplication.ability.ResetPassWordAbility")// 要跳转的页面
                .withAction("")
                .build();
        // 把打包后的operation设置到意图中
        intent.setOperation(operation);
        startAbility(intent);
    }
}
