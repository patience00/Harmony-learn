package com.linchtech.myapplication.utils;

import com.linchtech.myapplication.ResourceTable;
import com.linchtech.myapplication.domain.Girl;
import ohos.agp.components.Button;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.CommonDialog;
import ohos.app.Context;

import java.util.Random;

import static com.linchtech.myapplication.ability.slice.GirlAbilitySlice.girls;

public class DialogUtil {

    public static void showDialog(Context context, String title,String content) {
        // 创建弹框的对象,this表示当前界面
        CommonDialog commonDialog = new CommonDialog(context);
        // 标题0
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
        DirectionalLayout directionalLayout = (DirectionalLayout) LayoutScatter.getInstance(context).parse(ResourceTable.Layout_messagedialog, null, false);

        Text alertTitle = (Text) directionalLayout.findComponentById(ResourceTable.Id_alertTitle);
        alertTitle.setText(title);
        Text alertContent = (Text) directionalLayout.findComponentById(ResourceTable.Id_alertContent);
        alertContent.setText(content);

        Button likeGirl = (Button) directionalLayout.findComponentById(ResourceTable.Id_likeGirl);
        likeGirl.setClickedListener(component12 -> commonDialog.destroy());
        Button disLikeGirl = (Button) directionalLayout.findComponentById(ResourceTable.Id_disLikeGirl);
        disLikeGirl.setClickedListener(component1 -> {
            commonDialog.destroy();
            int girlIndex = new Random().nextInt(girls.size());
            Girl girl = girls.get(girlIndex);
            System.out.println(girl);
            // age.setText("年龄:" + girl.getAge());
            // name.setText("姓名:" + girl.getName());
            // image.setImageAndDecodeBounds(girl.getPhotoId());
        });
        // 将xml的布局对象设置到content中
        commonDialog.setContentCustomComponent(directionalLayout);
        // 弹框显示
        commonDialog.show();
    }
}
