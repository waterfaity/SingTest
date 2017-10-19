package com.xueduoduo.singtest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.constraint.CoreProvideTypeEnum;
import com.google.gson.Gson;
import com.xs.BaseSingEngine;
import com.xs.SingEngine;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author water_fairy
 * @email 995637517@qq.com
 * @date 2017/10/19
 * @Description:
 */

public class SingEvaluateManger {

    private static final String TAG = "SingEvaluateManger";

    public static final int TYPE_ENGLISH = 1;
    public static final int TYPE_CHINESE = 2;

    public final static int TEXT_TYPE_WORD = 1;//单词
    public final static int TEXT_TYPE_SENTENCE = 2;//句子
    public final static int TEXT_TYPE_PASSAGE = 3;//段落
    private boolean running;
    private boolean playing;

    private Activity activity;//页面关闭 activity 引用


    private SingEvaluateManger() {

    }

    private static SingEvaluateManger SingEvaluateManger;
    private SingEngine singEngine = null;


    public static SingEvaluateManger getInstance() {
        if (SingEvaluateManger == null) {
            return new SingEvaluateManger();
        }
        return SingEvaluateManger;
    }

    public SingEngine initSingEngine(@NonNull Activity activity, OnEvaluateListener onEvaluateListener) {
        this.activity = activity;
        if (singEngine == null) {
            try {//  获取引擎实例,设置测评监听对象
                singEngine = SingEngine.newInstance(activity);
                singEngine.setListener(new ResultListener(onEvaluateListener));
                //  设置引擎类型
                singEngine.setServerType(CoreProvideTypeEnum.CLOUD);
                //  设置是否开启VAD功能
                singEngine.setServerAPI("ws://trial.cloud.ssapi.cn:8080");
                singEngine.setOpenVad(false, null);
                //singEngine.setOpenVad(true, "vad.0.1.bin");//开启后， 录音一段时间后不说话 自动调用引擎stop()，结束录音并返回评测结果。(默认关闭)
                singEngine.setFrontVadTime(3000);//前置时间
                singEngine.setBackVadTime(1000);//后置时间
                //   构建引擎初始化参数
                JSONObject cfg_init = null;
                //   设置引擎初始化参数
                singEngine.setNewCfg(cfg_init);
                //   引擎初始化
                singEngine.newEngine();
            } catch (JSONException e) {
                e.printStackTrace();
                singEngine = null;
            }
        }
        return singEngine;
    }

    public SingEngine getSingEngine() {
        return singEngine;
    }


    public void start(String text, int type, String saveWavFilePath) {
        if (singEngine != null) {
            if (running) {
                singEngine.stop();
                running = false;
            }
            try {
                JSONObject request = new JSONObject();
                String language = "en";
                String textType = "sent";
                if (type == TYPE_CHINESE) {
                    language = "cn";
                } else {
                    if (!(text.contains(" ") || text.contains("　"))) {
                        textType = "word";
                    }
                }
                request.put("coreType", language + "." + textType + ".score");//类型
                request.put("refText", text);//评测文字内容
                request.put("rank", 100);//100 分制
                //构建评测请求参数
                JSONObject startCfg = singEngine.buildStartJson("guest", request);
                //设置评测请求参数
                singEngine.setStartCfg(startCfg);
                //开始测评
                singEngine.setWavPath(saveWavFilePath);
                singEngine.start();
                running = true;
            } catch (Exception e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    public void stop() {
        if (singEngine != null) {
            singEngine.stop();
            running = false;
        }
    }

    public void cancel() {
        if (singEngine != null) {
            singEngine.cancel();
            running = false;
        }
    }


    public void playBack() {
        if (singEngine != null) {
            singEngine.playback();
        }
        playing = true;

    }

    public void stopPlayBack() {
        if (singEngine != null) {
            singEngine.stopPlayBack();
        }
        playing = false;

    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPlaying() {
        return playing;
    }

    public class ResultListener implements BaseSingEngine.ResultListener {


        private OnEvaluateListener onEvaluateListener;

        public ResultListener(OnEvaluateListener onEvaluateListener) {
            this.onEvaluateListener = onEvaluateListener;
        }

        @Override
        public void onBegin() {
            Log.i(TAG, "onBegin: ");
            if (onEvaluateListener != null && activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onEvaluateListener.onBegin();
                    }
                });
            }
        }

        @Override
        public void onResult(final JSONObject result) {
            Log.i(TAG, "onResult: ");
            if (activity != null && onEvaluateListener != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            SingEvaluateBean singEvaluateBean = gson.fromJson(result.toString(), SingEvaluateBean.class);
                            SingEvaluateBean.ResultBean resultBean = singEvaluateBean.getResult();
                            onEvaluateListener.onEvaluateSuccess(resultBean);
                        } catch (Exception e) {
                            onEvaluateListener.onEvaluateError(null);
                        }

                    }
                });
            }
        }

        @Override
        public void onEnd(final int code, final String msg) {
            //code==0 成功
            Log.i(TAG, "onEnd: " + code + "  :  " + msg);
            if (activity != null && onEvaluateListener != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code != 0) {
                            onEvaluateListener.onEvaluateError(msg);
                        }
                    }
                });
            }
            running = false;
        }

        @Override
        public void onUpdateVolume(final int volume) {
            //声音大小
            if (activity != null && onEvaluateListener != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onEvaluateListener.onRecording(volume);
                    }
                });
            }
        }

        @Override
        public void onFrontVadTimeOut() {
            Log.i(TAG, "onFrontVadTimeOut: ");
            running = false;
        }

        @Override
        public void onBackVadTimeOut() {
            Log.i(TAG, "onBackVadTimeOut: ");
            running = false;
        }

        @Override
        public void onRecordingBuffer(byte[] data, int size) {
        }

        @Override
        public void onRecordLengthOut() {
            Log.i(TAG, "onRecordLengthOut: ");
            stop();
        }

        @Override
        public void onReady() {
            Log.i(TAG, "onReady: ");
            if (onEvaluateListener != null && activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onEvaluateListener.onEvaluateReady();
                    }
                });
            }
        }

        @Override
        public void onPlayCompeleted() {
            playing = false;
        }

        @Override
        public void onRecordStop() {
            if (onEvaluateListener != null && activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onEvaluateListener.onRecordComplete();
                    }
                });
            }
        }
    }

    public interface OnEvaluateListener {
        /**
         * 引擎启动成功
         */
        void onEvaluateReady();

        /**
         * 开始评测
         */
        void onBegin();

        /**
         * 评测完成
         */
        void onEvaluateSuccess(SingEvaluateBean.ResultBean resultBean);

        /**
         * 评测失败
         *
         * @param msg
         */
        void onEvaluateError(String msg);

        /**
         * 录音中
         *
         * @param volume
         */
        void onRecording(int volume);

        /**
         * 录音完成
         */
        void onRecordComplete();
    }

    public void onDestroy() {
        if (singEngine != null) {
            singEngine.delete();
            singEngine = null;
        }
        activity = null;
    }

}
