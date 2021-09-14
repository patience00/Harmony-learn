package com.linchtech.myapplication;

import com.linchtech.myapplication.slice.LongClickAblitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class LongClickAblity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(LongClickAblitySlice.class.getName());
    }
}
