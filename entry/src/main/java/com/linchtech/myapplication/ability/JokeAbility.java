package com.linchtech.myapplication.ability;

import com.linchtech.myapplication.ability.slice.JokeAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class JokeAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(JokeAbilitySlice.class.getName());
    }
}
