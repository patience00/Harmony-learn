package com.linchtech.myapplication.ability;

import com.linchtech.myapplication.ability.slice.WebImageAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class WebImageAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(WebImageAbilitySlice.class.getName());
    }
}
