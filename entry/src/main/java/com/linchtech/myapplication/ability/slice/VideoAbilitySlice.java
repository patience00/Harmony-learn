package com.linchtech.myapplication.ability.slice;

import com.linchtech.myapplication.ResourceTable;
import com.linchtech.myapplication.utils.Utils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.graphics.Surface;
import ohos.agp.graphics.SurfaceOps;
import ohos.agp.window.service.WindowManager;
import ohos.media.common.Source;
import ohos.media.player.Player;
import ohos.multimodalinput.event.MmiPoint;
import ohos.multimodalinput.event.TouchEvent;

public class VideoAbilitySlice extends AbilitySlice implements Component.TouchEventListener, Component.LongClickedListener, Component.ClickedListener, Component.DoubleClickedListener {
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
        mSurfaceProvider.setClickedListener(this);
        mSurfaceProvider.setDoubleClickedListener(this);
        mSurfaceProvider.setLongClickedListener(this);
        mSurfaceProvider.setTouchEventListener(this);
        image.setClickedListener(this);
        image.setDoubleClickedListener(this);

        // mSurfaceProvider.pinToZTop(true);
        // 将红心固定在最上面
        WindowManager.getInstance().getTopWindow().get().setTransparent(true);
        // mSurfaceProvider.setRotation(180);
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
            try {
                if (!play) {
                    System.out.println("播放");
                    play = mPlayer.play();
                } else {
                    System.out.println("暂停");
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
            // 打开播放视频源文件
            if (mSurfaceProvider.getSurfaceOps().isPresent()) {
                Surface surface = mSurfaceProvider.getSurfaceOps().get().getSurface();
                // Source source = new Source("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
                Source source = new Source("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4");
                // 设置音频源
                mPlayer.setSource(source);
                mPlayer.setVideoSurface(surface);
                mPlayer.setPlayerCallback(new VideoPlayerCallback());
                mPlayer.prepare();
                // mSurfaceProvider.setTop(0);
            }

        } catch (Exception e) {
            Utils.log("Exception : " + e.getLocalizedMessage());
        }
        // 准备播放
        // mPlayer.prepare();
        // 设置Surface配置
        // mPlayer.setSurfaceOps(mSurfaceProvider.getSurfaceOps().get());
    }

    @Override
    public void onLongClicked(Component component) {
        // 加速播放
        mPlayer.setPlaybackSpeed(3.0f);
        System.out.println("三倍速播放");
    }

    @Override
    public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
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
            // 左滑动
            if (endX > x && Math.abs(endY - y) < 100) {
                System.out.println("快退");
                mPlayer.rewindTo(-10000L);
            }
            // 右滑动
            if (endX < x && Math.abs(endY - y) < 100) {
                System.out.println("快进");
                mPlayer.rewindTo(10000L);
            }

        }
        // true,所有的动作都会触发当前方法
        // false,只有第一个动作会触发当前方法,后续动作就不会触发方法
        return true;


    }

    private class VideoPlayerCallback implements Player.IPlayerCallback {

        @Override
        public void onPrepared() {
            System.out.println("onPrepared");
        }


        @Override
        public void onMessage(int i, int i1) {
            System.out.println("onMessage");
        }

        @Override
        public void onError(int i, int i1) {
            System.out.println("onError: i=" + i + ", i1=" + i1);
        }


        @Override
        public void onResolutionChanged(int i, int i1) {
            System.out.println("onResolutionChanged");
        }


        @Override
        public void onPlayBackComplete() {
            System.out.println("onPlayBackComplete");
            if (mPlayer != null) {
                mPlayer.stop();
                mPlayer = null;
            }
        }


        @Override
        public void onRewindToComplete() {
            System.out.println("onRewindToComplete");
        }


        @Override
        public void onBufferingChange(int i) {
            System.out.println("onBufferingChange");
        }


        @Override
        public void onNewTimedMetaData(Player.MediaTimedMetaData mediaTimedMetaData) {
            System.out.println("onNewTimedMetaData");
        }


        @Override
        public void onMediaTimeIncontinuity(Player.MediaTimeInfo mediaTimeInfo) {
            System.out.println("onMediaTimeIncontinuity");
        }

    }
}
