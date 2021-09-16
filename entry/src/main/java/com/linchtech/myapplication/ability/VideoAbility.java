package com.linchtech.myapplication.ability;

import com.linchtech.myapplication.ability.slice.VideoAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class VideoAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(VideoAbilitySlice.class.getName());
    }
}
