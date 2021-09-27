package com.linchtech.myapplication.ability.slice;

import com.linchtech.myapplication.ResourceTable;
import com.linchtech.myapplication.utils.ImageReadUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Image;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;
import ohos.media.image.PixelMap;

public class WebImageAbilitySlice extends AbilitySlice {

    // String urlImage = "http://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F0725%2Fb41eb116j00qwt2xq0017c000hs00qog.jpg&thumbnail=650x2147483647&quality=80&type=jpg";
    String urlImage = "https://scpic.chinaz.net/files/pic/pic9/202109/apic35235.jpg";
    // String urlImage = "https://tp1.tupiankucdn.com/ws/large/005GOaLIgy1fxg0o36dp4g308c05lqfy.gif";


    private Image image;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_web_image);
        this.image = (Image) findComponentById(ResourceTable.Id_net_image_example);
/*
        HttpURLConnection connection = null;
        DirectionalLayout imageLayout = (DirectionalLayout) findComponentById(ResourceTable.Id_webImageLayout);


        try {
            URL url = new URL(urlImage);
            URLConnection urlConnection = url.openConnection();
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            }
            if (connection != null) {
                connection.connect();
                // 之后可进行url的其他操作
                // 得到服务器返回过来的流对象
                InputStream inputStream = urlConnection.getInputStream();
                ImageSource imageSource = ImageSource.create(inputStream, new ImageSource.SourceOptions());
                ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
                decodingOptions.desiredPixelFormat = PixelFormat.ARGB_8888;
                // 普通解码叠加旋转、缩放、裁剪
                PixelMap pixelMap = imageSource.createPixelmap(decodingOptions);
                // 普通解码
                getUITaskDispatcher().syncDispatch(() -> {
                    Image image = new Image(this);
                    DirectionalLayout.LayoutConfig config = new DirectionalLayout.LayoutConfig(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT);
                    config.setMargins(10, 10, 10, 10);
                    image.setLayoutConfig(config);
                    image.setPixelMap(pixelMap);
                    imageLayout.addComponent(image);
                    pixelMap.release();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        /**
         * 读取网络数据的耗时操作在TaskDispatcher分发的单独线程中处理，
         * 否则会报 "NetworkOnMainThreadException"异常
         */
        TaskDispatcher refreshUITask = createParallelTaskDispatcher("", TaskPriority.DEFAULT);
        refreshUITask.syncDispatch(()->{
            PixelMap pixelMap = ImageReadUtil.createPixelMap(urlImage);
            getContext().getUITaskDispatcher().asyncDispatch(new Runnable() {
                @Override
                public void run() {
                    //Image组件填充位图数据，ui界面更新
                    image.setPixelMap(pixelMap);
                    pixelMap.release();
                }
            });

        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
