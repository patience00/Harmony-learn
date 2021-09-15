package com.linchtech.myapplication.slice;

import com.linchtech.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;
import ohos.multimodalinput.event.MmiPoint;
import ohos.multimodalinput.event.TouchEvent;

import java.util.Random;
import java.util.UUID;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener, Component.TouchEventListener, Component.LongClickedListener, Component.DoubleClickedListener {

    private Button button;
    private Button doubleButton;
    private Button longClickButton;
    private Text text;
    private Text text2;

    private float x;
    private float y;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
//        button = (Button) findComponentById(ResourceTable.Id_button1);
//        button.setClickedListener(this::onClick);
//
//        doubleButton = (Button) findComponentById(ResourceTable.Id_button2);
//        doubleButton.setDoubleClickedListener(this);
//
//        longClickButton = (Button) findComponentById(ResourceTable.Id_button3);
//        longClickButton.setLongClickedListener(this);
        text = (Text) findComponentById(ResourceTable.Id_text_helloworld);
        text2 = (Text) findComponentById(ResourceTable.Id_text2);

        DirectionalLayout layout = (DirectionalLayout) findComponentById(ResourceTable.Id_main);
        layout.setTouchEventListener(this);
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
//        System.out.println("跳转");
//        if (component == button) {
//            Intent intent = new Intent();
//            Operation operation = new Intent.OperationBuilder().withDeviceId("")
//                    .withBundleName("com.linchtech.myapplication")
//                    .withAbilityName("com.linchtech.myapplication.SecondMainAbility")
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//        }
        text = (Text) findComponentById(ResourceTable.Id_text_helloworld);
        text2 = (Text) findComponentById(ResourceTable.Id_text2);
        text.setText(UUID.randomUUID().toString().split("-")[0]);
        text.setTextColor(Color.CYAN);


    }


    @Override
    public void onLongClicked(Component component) {
        System.out.println("****************");
        text.setText("长按");
        text.setTextColor(Color.YELLOW);
    }

    @Override
    public void onDoubleClick(Component component) {
        System.out.println("++++++++++++");
        text.setText("双击了");
        text.setTextColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
        System.out.println("!!!!!!!!!!!");
//        text.setText("滑动");
//        text.setTextColor(Color.BLUE);

        int action = touchEvent.getAction();
        if (action == TouchEvent.PRIMARY_POINT_DOWN) {
            // 一个手指操作屏幕传0,两个手指则1
            MmiPoint position = touchEvent.getPointerPosition(0);
            x = position.getX();
            y = position.getY();

        } else if (action == TouchEvent.POINT_MOVE) {
        } else if (action == TouchEvent.PRIMARY_POINT_UP) {
            MmiPoint position = touchEvent.getPointerPosition(0);
            float endX = position.getX();
            float endY = position.getY();

            if (endY > y && Math.abs(endX - x) < 100) {
//                text.setText("向下滑动");
            }
            if (endY < y && Math.abs(endX - x) < 100) {
//                text.setText("上滑动");
            }

            if (endX > x && Math.abs(endY - y) < 100) {
//                text2.setText("右滑动");
            }
            if (endX < x && Math.abs(endY - y) < 100) {
//                text2.setText("左滑动");
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("") // 要跳转的设备
                        .withBundleName("com.linchtech.myapplication") // 要跳转的应用
                        .withAbilityName("com.linchtech.myapplication.ability.SecondAbility")// 要跳转的页面
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
}
