package com.linchtech.myapplication.utils;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;

import java.io.InputStream;

/**
 * 图片读取工具类
 * user：lixiaoda
 * date：2021/1/29
 */
public class ImageReadUtil {

    public static PixelMap createPixelMap(String imageUrl) {

        InputStream inputStream = HttpsUtil.getInputStream(imageUrl, RequestMethod.GET.name());
        /*
        设置sourceOptions的formatHint为指定mime类型可提高解析效果；
        sourceOptions为null则ImageSource自动根据mime类型进行解析；
         */

        ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
        sourceOptions.formatHint = "image/jpeg";

        ImageSource imageSource = ImageSource.create(inputStream, sourceOptions);
        PixelMap pixelMap = imageSource.createPixelmap(null);

        /*
        读取完成后，需要关闭流资源。流未关闭会造成流资源阻塞，
        即使数据读取完成也会持续等待流数据信息，导致调用方获取不到数据
         */
        HttpsUtil.closeStream();
        HiLog.info(new HiLogLabel(0, 0, "ImageReadUtil"), "PixelMap info:%{public}s ", pixelMap.getImageInfo().size);

        return pixelMap;
    }
}
