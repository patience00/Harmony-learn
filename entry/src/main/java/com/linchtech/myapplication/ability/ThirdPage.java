package com.linchtech.myapplication.ability;

import com.linchtech.myapplication.ability.slice.ThirdPageSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class ThirdPage extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ThirdPageSlice.class.getName());
    }
}
