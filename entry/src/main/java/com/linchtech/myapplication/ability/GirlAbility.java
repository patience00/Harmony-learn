package com.linchtech.myapplication.ability;

import com.linchtech.myapplication.ability.slice.GirlAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class GirlAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(GirlAbilitySlice.class.getName());
    }
}
