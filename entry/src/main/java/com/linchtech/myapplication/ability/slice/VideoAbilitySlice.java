package com.linchtech.myapplication.ability.slice;

import com.linchtech.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.media.codec.Codec;
import ohos.media.codec.CodecDescriptionList;
import ohos.media.common.AVDescription;
import ohos.media.common.BufferInfo;
import ohos.media.common.Format;
import ohos.media.common.Source;
import ohos.media.common.sessioncore.AVElement;
import ohos.media.player.Player;
import ohos.utils.net.Uri;

import java.nio.ByteBuffer;
import java.util.List;

public class VideoAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private Image image;

    private boolean play = false;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_video);
        image = (Image) findComponentById(ResourceTable.Id_video);
        image.setClickedListener(this);

        // 视频解码能力查询
        List<String> mimes = CodecDescriptionList.getSupportedMimes();
        boolean result = CodecDescriptionList.isDecodeSupportedByMime(Format.VIDEO_MPEG4);
        if (result) {
            System.out.println("可以播放mp4");
            final Codec codec = Codec.createDecoder();
            Format fmt = new Format();
            fmt.putStringValue(Format.MIME, Format.VIDEO_MPEG4);
            fmt.putIntValue(Format.WIDTH, 1920);
            fmt.putIntValue(Format.HEIGHT, 1080);
            fmt.putIntValue(Format.BIT_RATE, 392000);
            fmt.putIntValue(Format.FRAME_RATE, 30);
            fmt.putIntValue(Format.FRAME_INTERVAL, -1);
            codec.setCodecFormat(fmt);

            Codec.ICodecListener listener = new Codec.ICodecListener() {
                @Override
                public void onReadBuffer(ByteBuffer byteBuffer, BufferInfo bufferInfo, int trackId) {
                    Format fmt = codec.getBufferFormat(byteBuffer);
                }

                @Override
                public void onError(int errorCode, int act, int trackId) {
                    throw new RuntimeException();
                }
            };
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

    @Override
    public void onClick(Component component) {
        System.out.println("点击播放");
        AVDescription bean =
                new AVDescription.Builder()
                        .setTitle("web_video_01")
                        .setIMediaUri(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
                        .setMediaId("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
                        .build();
        AVElement avElement = new AVElement(bean, AVElement.AVELEMENT_FLAG_PLAYABLE);
        try {
            Player player = new Player(this);
            // 根据实际情况设置文件路径
//            File file = new File("entry/src/main/resources/base/media/123.mp4");
//            FileInputStream in = new FileInputStream(file);
//            FileDescriptor fd = in.getFD(); // 从输入流获取FD对象
//            Source source = new Source(fd);
            Source source = new Source(avElement.getAVDescription().getMediaUri().toString());
            player.setSource(source);
            if (!play) {
                play = player.play();
            } else {
                play = player.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
