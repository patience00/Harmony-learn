package com.linchtech.myapplication.ability.slice;

import com.linchtech.myapplication.ResourceTable;
import com.linchtech.myapplication.utils.Utils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.Image;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.graphics.SurfaceOps;
import ohos.global.resource.RawFileDescriptor;
import ohos.media.common.Source;
import ohos.media.player.Player;

public class VideoAbilitySlice extends AbilitySlice implements Component.ClickedListener, Component.DoubleClickedListener {
    private Image image;
    // SurfaceProvider对象
    private SurfaceProvider mSurfaceProvider;
    // 播放器对象
    private Player mPlayer;

    private boolean play = false;
    private float x;
    private float y;
    /**
     * false 为没有点赞,true已经点赞
     */
    private boolean like = false;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_video);
        mSurfaceProvider = (SurfaceProvider) findComponentById(ResourceTable.Id_surface_provider_video);
        image = (Image) findComponentById(ResourceTable.Id_likeVideo);
        // mBtnVideoPlay = (Button) findComponentById(ResourceTable.Id_btn_video_play);
        // mBtnVideoPlay.setClickedListener(this);
        // mBtnVideoPause = (Button) findComponentById(ResourceTable.Id_btn_video_pause);
        // mBtnVideoPause.setClickedListener(this);
        // mBtnVideoStop = (Button) findComponentById(ResourceTable.Id_btn_video_stop);
        // mBtnVideoStop.setClickedListener(this);
        mSurfaceProvider.setClickedListener(this);
        mSurfaceProvider.setDoubleClickedListener(this);
        image.setClickedListener(this);
        image.setDoubleClickedListener(this);

        mSurfaceProvider.pinToZTop(true);
        mSurfaceProvider.getSurfaceOps().get().addCallback(new SurfaceOps.Callback() {
            @Override
            public void surfaceCreated(SurfaceOps surfaceOps) {
                // 初始化播放器
                initPlayer();
            }

            @Override
            public void surfaceChanged(SurfaceOps surfaceOps, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceOps surfaceOps) {
            }
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

    @Override
    public void onClick(Component component) {
        // TODO 点击播放后点赞按钮消失的问题
        // ComponentContainer stackLayout = (ComponentContainer) findComponentById(ResourceTable.Id_stack_layout);
        // stackLayout.moveChildToFront(image);
        // 点击视频页面
        if (component == mSurfaceProvider) {
            System.out.println("点击播放:" + play);
            try {
                if (!play) {
                    play = mPlayer.play();
                } else {
                    play = mPlayer.pause();
                    play = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (component == image) {
            System.out.println("点击点赞");
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

    @Override
    public void onDoubleClick(Component component) {
        System.out.println("点赞");
        // 修改图片
        if (!like) {
            like = true;
            image.setImageAndDecodeBounds(ResourceTable.Media_like);
        }
    }


    /**
     * 初始化Player对象
     */
    private void initPlayer() {
        System.out.println("初始化播放器");
        // 创建播放器对象
        mPlayer = new Player(this);
        try {
            // 打开播放音频源文件
            RawFileDescriptor fd = getResourceManager()
                    .getRawFileEntry("resources/base/media/1234.mp4")
                    .openRawFileDescriptor();
            Source source = new Source(fd.getFileDescriptor(),
                    fd.getStartPosition(),
                    fd.getFileSize());
           /* AVDescription bean =
                    new AVDescription.Builder()
                            .setTitle("web_video_01")
                            .setIMediaUri(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
                            .setMediaId("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
                            .build();
            AVElement avElement = new AVElement(bean, AVElement.AVELEMENT_FLAG_PLAYABLE);
            Source source = new Source(avElement.getAVDescription().getMediaUri().toString());*/

            // 设置音频源
            mPlayer.setSource(source);

        } catch (Exception e) {
            Utils.log("Exception : " + e.getLocalizedMessage());
        }
        // 准备播放
        mPlayer.prepare();
        // 设置Surface配置
        mPlayer.setSurfaceOps(mSurfaceProvider.getSurfaceOps().get());
    }
}
