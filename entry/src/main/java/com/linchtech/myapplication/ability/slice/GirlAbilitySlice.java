package com.linchtech.myapplication.ability.slice;

import com.google.common.collect.Lists;
import com.linchtech.myapplication.ResourceTable;
import com.linchtech.myapplication.domain.Girl;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.window.dialog.CommonDialog;

import java.util.ArrayList;
import java.util.Random;

public class GirlAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    Text name;
    Text age;
    private Button button;
    private Button alertButton;
    private Image image;
    public static ArrayList<Girl> girls = Lists.newArrayList();

    static {
        girls.add(new Girl(ResourceTable.Media_girl11, "哈哈哈", 16, "a"));
        girls.add(new Girl(ResourceTable.Media_girl12, "啊啊啊", 21, "aaa"));
        girls.add(new Girl(ResourceTable.Media_girl13, "滚滚滚", 20, "qqq"));
        girls.add(new Girl(ResourceTable.Media_girl14, "是是是", 21, "www"));
        girls.add(new Girl(ResourceTable.Media_girl15, "啧啧啧", 22, "eee"));
        girls.add(new Girl(ResourceTable.Media_girl16, "嘻嘻嘻", 23, "rrr"));
        girls.add(new Girl(ResourceTable.Media_girl17, "那那那", 19, "ttt"));
    }


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_girl);

        name = (Text) findComponentById(ResourceTable.Id_girlName);
        age = (Text) findComponentById(ResourceTable.Id_girlAge);
        image = (Image) findComponentById(ResourceTable.Id_girlList);

        button = (Button) findComponentById(ResourceTable.Id_nextGirl);
        alertButton = (Button) findComponentById(ResourceTable.Id_alert);


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
            // commonDialog.setTitleText("喜欢吗");
            // 透明
            // commonDialog.setTransparent(true);
            // 圆角
            commonDialog.setCornerRadius(15);
            // 自动关闭
            commonDialog.setAutoClosable(true);
            // 内容
            // commonDialog.setContentText("如果不喜欢就换一个");

            // 加载弹框的布局xml
            // 参数1:xml的id,参数2:是否与其他xml文件有关,无关是独立的,传null即可;参数3:文件是独立的,传false
            DirectionalLayout directionalLayout = (DirectionalLayout) LayoutScatter.getInstance(this).parse(ResourceTable.Layout_messagedialog, null, false);

            Text alertTitle = (Text) directionalLayout.findComponentById(ResourceTable.Id_alertTitle);
            alertTitle.setText("是否喜欢");
            Text alertContent = (Text) directionalLayout.findComponentById(ResourceTable.Id_alertContent);
            alertContent.setText("不喜欢就下一批");

            // 添加按钮
            // commonDialog.setButton(0, "喜欢", new IDialog.ClickedListener() {
            //     @Override
            //     public void onClick(IDialog iDialog, int i) {
            //         // 如果点击不需要做任何事情,则传参null
            //         commonDialog.destroy();
            //
            //     }
            // });
            //
            // commonDialog.setButton(1, "不喜欢", new IDialog.ClickedListener() {
            //     @Override
            //     public void onClick(IDialog iDialog, int i) {
            //         commonDialog.destroy();
            //         int girlIndex = new Random().nextInt(girls.size());
            //         Girl girl = girls.get(girlIndex);
            //         System.out.println(girl);
            //         age.setText(girl.getAge());
            //         image.setImageAndDecodeBounds(girl.getPhotoId());
            //         name.setText(girl.getName());
            //         age.setText(girl.getAge());
            //     }
            // });
            Button likeGirl = (Button) directionalLayout.findComponentById(ResourceTable.Id_likeGirl);
            likeGirl.setClickedListener(component12 -> commonDialog.destroy());
            Button disLikeGirl = (Button) directionalLayout.findComponentById(ResourceTable.Id_disLikeGirl);
            disLikeGirl.setClickedListener(component1 -> {
                commonDialog.destroy();
                int girlIndex = new Random().nextInt(girls.size());
                Girl girl = girls.get(girlIndex);
                System.out.println(girl);
                age.setText("年龄:" + girl.getAge());
                name.setText("姓名:" + girl.getName());
                image.setImageAndDecodeBounds(girl.getPhotoId());
            });
            // 将xml的布局对象设置到content中
            commonDialog.setContentCustomComponent(directionalLayout);
            // 弹框显示
            commonDialog.show();

        } else {
            int girlIndex = new Random().nextInt(girls.size());
            Girl girl = girls.get(girlIndex);
            System.out.println(girl);
            age.setText("年龄:" + girl.getAge());
            name.setText("姓名:" + girl.getName());
            image.setImageAndDecodeBounds(girl.getPhotoId());
        }
    }
}
