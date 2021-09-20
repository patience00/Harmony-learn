package com.linchtech.myapplication.utils;

import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.TextAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_CONTENT;
import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_PARENT;

public class Utils {

    private static final String S_BUNDLE_NAME = "com.example.media";

    // HiLogLabel定义了域信息和标签等
    private static final HiLogLabel S_LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00101, "Media");

    /**
     * HiLog输出
     * @param logStr 输出字符串
     */
    public static void log(String logStr) {
        HiLog.info(S_LOG_LABEL, logStr);
    }


    /**
     * 生成Intent对象用于Page之间的跳转
     * @param pagename Page Ability的全名称
     * @return Intent对象
     */
    public static Intent generatePageNavigationIntent(String pagename) {
        // 创建Intent对象
        Intent intent = new Intent();
        // 创建Operation对象
        Operation operation = new Intent.OperationBuilder() // 创建Operation对象
                .withDeviceId("")  // 目标设备，空字符串代表本设备
                .withBundleName(S_BUNDLE_NAME) // 通过BundleName指定应用程序
                .withAbilityName(pagename) // 通过Ability的全名称（包名+类名）指定启动的Ability
                .build();
        // 设置Intent对象的operation属性
        intent.setOperation(operation);

        return intent;
    }

    /**
     * 生成简单的ShapeElement对象
     * @param red 红色
     * @param green 绿色
     * @param blue 蓝色
     * @return 简单的ShapeElement对象
     */
    public static ShapeElement generateSimpleShapeElement(int red, int green, int blue) {
        ShapeElement element = new ShapeElement();
        element.setRgbColor(new RgbColor(red,green,blue));
        return element;
    }

    /**
     * 显示灰色背景Toast对话框
     * @param context 当前上下文对象
     * @param str 显示内容
     */
    public static void showToast(Context context, String str) {

        // 创建文本组件
        Text text = new Text(context);
        text.setWidth(MATCH_CONTENT);
        text.setHeight(MATCH_CONTENT);
        text.setText(str); // 显示文本内容
        text.setTextSize(45); // 字号
        text.setPadding(30,10,30,10); // 内边距
        text.setMultipleLine(true); // 可多行显示文本内容
        text.setTextColor(Color.WHITE); // 文字颜色为白色
        text.setTextAlignment(TextAlignment.CENTER); // 居中显示
        // 文本组件使用灰色圆角背景
        ShapeElement element = new ShapeElement();
        element.setRgbColor(new RgbColor(0x888888FF));
        element.setShape(ShapeElement.RECTANGLE);
        element.setCornerRadius(15); // 圆角半径
        text.setBackground(element);

        // 创建定向布局，并加入文本组件
        DirectionalLayout layout = new DirectionalLayout(context);
        layout.setWidth(MATCH_PARENT);
        layout.setHeight(MATCH_CONTENT);
        layout.setAlignment(LayoutAlignment.CENTER); // 居中内容
        layout.addComponent(text);

        // 创建Toast对话框
        ToastDialog toastDialog = new ToastDialog(context);
        toastDialog.setComponent(layout); // 使用自定义组件
        toastDialog
                .setTransparent(true) // 设置背景透明
                .setDuration(2000) // 显示时间 2000毫秒
                .setAlignment(LayoutAlignment.BOTTOM + LayoutAlignment.HORIZONTAL_CENTER) // 居中下方显示
                .setOffset(0, 200) // 距离底边距200px距离
                .show();
    }
}
