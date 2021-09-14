package com.linchtech.myapplication;

import com.linchtech.myapplication.slice.SecondMainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class SecondMainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SecondMainAbilitySlice.class.getName());
    }
}
