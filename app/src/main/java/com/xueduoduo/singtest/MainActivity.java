package com.xueduoduo.singtest;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xs.BaseSingEngine;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "main";

    ProgressDialog mProgressDialog;
    private EditText editText;
    private Button button, local;
    private TextView textView;
    private String audioPath = "/sdcard/sing/今天你又迟到了.mp3";
    private SingEvaluateManger singEvaluateManger;
    private String wavPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (PermissionUtils.requestPermission(this, PermissionUtils.REQUEST_STORAGE)) {
            initData();
        }
        if (PermissionUtils.requestPermission(this, PermissionUtils.REQUEST_RECORD)) {
            initSingEntity();
        }

    }

    private void initData() {
        File file = new File(audioPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = getResources().getAssets().open("audio/今天你又迟到了.mp3");
            int readNum = 1024 * 1024;
            byte[] bytes = new byte[readNum];
            while ((readNum = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, readNum);
            }
            fileOutputStream.flush();
            inputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edit);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        local = (Button) findViewById(R.id.local);
        button.setOnClickListener(this);
        local.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在加载...");
    }

    private void initSingEntity() {
        singEvaluateManger = SingEvaluateManger.getInstance();
        singEvaluateManger.initSingEngine(this, resultListener);
        mProgressDialog.show();
    }

    private boolean running;
    private boolean playing;
    private SingEvaluateManger.OnEvaluateListener resultListener = new SingEvaluateManger.OnEvaluateListener() {
        @Override
        public void onEvaluateReady() {
            Log.i(TAG, "onEvaluateReady: ");
            mProgressDialog.dismiss();
            setText("准备就绪");
        }

        @Override
        public void onBegin() {
            setText("开始录音");
        }

        @Override
        public void onEvaluateSuccess(SingEvaluateBean.ResultBean resultBean) {
            Log.i(TAG, "onEvaluateSuccess: ");
            setText("得分:" + resultBean.getOverall());
        }

        @Override
        public void onEvaluateError(String msg) {
            mProgressDialog.dismiss();
            Log.i(TAG, "onEvaluateError: ");
            setText("评估失败");
        }

        @Override
        public void onRecording(int volume) {

        }

        @Override
        public void onRecordComplete() {
            Log.i(TAG, "onRecordComplete: ");
            setText("录音完成");
            startWidthPCM();
        }
    };

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button:
                if (!singEvaluateManger.isRunning()) {
                    start();
                } else {
                    stop();
                }
                break;
            case R.id.local:
                startWidthPCM();
                break;
        }

    }

    private void startWidthPCM() {
        setText("开始转换");
        LameConvertToMp3AsyncTask lameConvertToMp3AsyncTask = new LameConvertToMp3AsyncTask(new LameConvertToMp3AsyncTask.OnConvertListener() {
            @Override
            public void onConvertSuccess() {
                setText("完成");
                Log.i(TAG, "onConvertSuccess: ");
            }

            @Override
            public void onConvertProgress(int progress) {
//                setText(progress + "%");
                Log.i(TAG, "onConvertProgress: " + progress);
            }

            @Override
            public void onConvertError() {
                setText("错误");
                Log.i(TAG, "onConvertError: ");
            }
        });
        lameConvertToMp3AsyncTask.execute(wavPath, wavPath.replace(".wav", ".mp3"));
    }

    int index;

    private void start() {
        button.setText("停止录音");
        String content = editText.getText().toString().trim();
        wavPath = "/sdcard/sing/" + System.currentTimeMillis() + ".wav";
        singEvaluateManger.start(content, SingEvaluateManger.TYPE_CHINESE, wavPath);
    }

    private void stop() {
        button.setText("开始录音");
        singEvaluateManger.stop();
    }

    private void cancel() {
        singEvaluateManger.cancel();
    }


    private void playBack() {
        singEvaluateManger.playBack();

    }

    private void stopPlayBack() {
        singEvaluateManger.stopPlayBack();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtils.REQUEST_RECORD == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initSingEntity();
            }
        } else if (PermissionUtils.REQUEST_STORAGE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initData();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setText(String text) {
        String s = textView.getText().toString();
        String hhh = text + "\n" + s;
        textView.setText(hhh);
    }


}
