package com.linchtech.myapplication.slice;

import com.linchtech.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.multimodalinput.event.MmiPoint;
import ohos.multimodalinput.event.TouchEvent;

public class SecondAbilitySlice extends AbilitySlice implements Component.ClickedListener, Component.DoubleClickedListener, Component.TouchEventListener {


    private float x;
    private float y;
    private Image image;
    /**
     * false 为没有点赞,true已经点赞
     */
    private boolean like = false;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_second);
        DirectionalLayout layout = (DirectionalLayout) findComponentById(ResourceTable.Id_second);
        layout.setTouchEventListener(this);

        image = (Image) findComponentById(ResourceTable.Id_notLike);
        layout.setDoubleClickedListener(this);
        image.setClickedListener(this);
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
    public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
        int action = touchEvent.getAction();
        if (action == TouchEvent.PRIMARY_POINT_DOWN) {
            // 一个手指操作屏幕传0,两个手指则1
            MmiPoint position = touchEvent.getPointerPosition(0);
            x = position.getX();
            y = position.getY();
        } else if (action == TouchEvent.PRIMARY_POINT_UP) {
            MmiPoint position = touchEvent.getPointerPosition(0);
            float endX = position.getX();
            float endY = position.getY();

            if (endX < x && Math.abs(endY - y) < 100) {
                System.out.println("=============");
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("") // 要跳转的设备
                        .withBundleName("com.linchtech.myapplication") // 要跳转的应用
                        .withAbilityName("com.linchtech.myapplication.ability.ThirdPage")// 要跳转的页面
                        .build();
                // 把打包后的operation设置到意图中
                intent.setOperation(operation);
                startAbility(intent);
            }
            if (endX > x && Math.abs(endY - y) < 100) {
                System.out.println("=============");
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("") // 要跳转的设备
                        .withBundleName("com.linchtech.myapplication") // 要跳转的应用
                        .withAbilityName("com.linchtech.myapplication.ability.MainAbility")// 要跳转的页面
                        .build();
                // 把打包后的operation设置到意图中
                intent.setOperation(operation);
                startAbility(intent);
            }
        }
        // true,所有的动作都会触发当前方法
        // false,只有第一个动作会触发当前方法,后续动作就不会触发方法
        return true;
    }

    @Override
    public void onDoubleClick(Component component) {
        // 修改图片
        if (!like) {
            like = true;
            image.setImageAndDecodeBounds(ResourceTable.Media_like);
        }
    }

    @Override
    public void onClick(Component component) {
        // 单击,可以点赞,也可以取消点赞
        if (like) {
            like = false;
            image.setImageAndDecodeBounds(ResourceTable.Media_notLike);
        } else {
            like = true;
            image.setImageAndDecodeBounds(ResourceTable.Media_like);
        }
    }
}
