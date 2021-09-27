package com.linchtech.myapplication.ability.slice;

import com.linchtech.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WebImageAbilitySlice extends AbilitySlice {

    String urlImage = "https://www.harmonyos.com/resource/image/community/20201009-164134eSpace.jpg";


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_web_image);

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
        }
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
