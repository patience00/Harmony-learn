package com.linchtech.myapplication.ability.slice;

import com.google.common.collect.Lists;
import com.linchtech.myapplication.ResourceTable;
import com.linchtech.myapplication.domain.Girl;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.IDialog;

import java.util.ArrayList;
import java.util.Random;

public class GirlAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    private Text name;
    private Text age;
    private Button button;
    private Button alertButton;
    private Image image;
    ArrayList<Girl> girls = Lists.newArrayList();


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_girl);

        name = (Text) findComponentById(ResourceTable.Id_girlName);
        age = (Text) findComponentById(ResourceTable.Id_girlAge);
        image = (Image) findComponentById(ResourceTable.Id_girlList);

        button = (Button) findComponentById(ResourceTable.Id_nextGirl);
        alertButton = (Button) findComponentById(ResourceTable.Id_alert);


        girls.add(new Girl(ResourceTable.Media_girl1, "哈哈哈", 16, "a"));
        girls.add(new Girl(ResourceTable.Media_girl2, "啊啊啊", 21, "aaa"));
        girls.add(new Girl(ResourceTable.Media_girl3, "滚滚滚", 20, "qqq"));
        girls.add(new Girl(ResourceTable.Media_girl4, "是是是", 21, "www"));
        girls.add(new Girl(ResourceTable.Media_girl5, "啧啧啧", 22, "eee"));
        girls.add(new Girl(ResourceTable.Media_girl6, "嘻嘻嘻", 23, "rrr"));
        girls.add(new Girl(ResourceTable.Media_girl7, "那那那", 19, "ttt"));

        button.setClickedListener(this);
        alertButton.setClickedListener(this);
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
        if (component == alertButton) {
            // 创建弹框的对象,this表示当前界面
            CommonDialog commonDialog = new CommonDialog(this);
            // 标题
            commonDialog.setTitleText("喜欢吗");
            // 自动关闭
            commonDialog.setAutoClosable(true);
            // 内容
            commonDialog.setContentText("如果不喜欢就换一个");
            // 添加按钮
            commonDialog.setButton(0, "喜欢", new IDialog.ClickedListener() {
                @Override
                public void onClick(IDialog iDialog, int i) {
                    // 如果点击不需要做任何事情,则传参null
                    commonDialog.destroy();

                }
            });

            commonDialog.setButton(1, "不喜欢", new IDialog.ClickedListener() {
                @Override
                public void onClick(IDialog iDialog, int i) {
                    commonDialog.destroy();
                    int girlIndex = new Random().nextInt(girls.size());
                    Girl girl = girls.get(girlIndex);
                    System.out.println(girl);
                    age.setText(girl.getAge());
                    image.setImageAndDecodeBounds(girl.getPhotoId());
                    name.setText(girl.getName());
                    age.setText(girl.getAge());
                }
            });
            // 弹框显示
            commonDialog.show();

        } else {
            int girlIndex = new Random().nextInt(girls.size());
            Girl girl = girls.get(girlIndex);
            System.out.println(girl);
            age.setText(girl.getAge());
            image.setImageAndDecodeBounds(girl.getPhotoId());
            name.setText(girl.getName());
            age.setText(girl.getAge());
        }
    }
}
