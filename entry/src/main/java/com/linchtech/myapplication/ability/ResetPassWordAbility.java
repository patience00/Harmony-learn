package com.linchtech.myapplication.ability;

import com.linchtech.myapplication.ability.slice.ResetPassWordAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class ResetPassWordAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ResetPassWordAbilitySlice.class.getName());
    }
}
