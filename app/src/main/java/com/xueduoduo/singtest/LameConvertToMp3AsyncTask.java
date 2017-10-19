package com.xueduoduo.singtest;

import android.os.AsyncTask;
import android.print.PrintAttributes;
import android.text.TextUtils;

import com.cloud.classroom.audiorecord.lame.SimpleLame;
import com.cloud.classroom.audiorecord.lame.SimpleLameCallBack;

import java.io.File;

/**
 * @author water_fairy
 * @email 995637517@qq.com
 * @date 2017/10/19
 * @Description:
 */

public class LameConvertToMp3AsyncTask extends AsyncTask<String, Integer, String> {
    private String wavPath, mp3Path;

    private OnConvertListener onConvertListener;

    public LameConvertToMp3AsyncTask(OnConvertListener onConvertListener) {
        this.onConvertListener = onConvertListener;
    }

    public LameConvertToMp3AsyncTask() {
    }

    /**
     * @param params 0:wavePath; 1:mp3Path
     * @return
     */
    @Override
    protected String doInBackground(String... params) {
        wavPath = params[0];
        mp3Path = params[1];
        File file = new File(wavPath);
        long length = 0;
        if (file.exists()) {
            length = file.length();
        } else {
            return null;
        }
        final long finalLength = length;
        SimpleLame.convertmp3(params[0], params[1], new SimpleLameCallBack() {
            @Override
            public void onProgress(int progress) {
                publishProgress((int) (progress / (float) finalLength * 100));
            }

            @Override
            public void onError(String message) {
                mp3Path = null;
            }
        });
        return mp3Path;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (onConvertListener != null)
            onConvertListener.onConvertProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String mp3Path) {
        super.onPostExecute(mp3Path);
        if (onConvertListener != null) {
            if (TextUtils.isEmpty(mp3Path)) {
                onConvertListener.onConvertError();
            } else {
                onConvertListener.onConvertSuccess();
            }
        }
    }

    public interface OnConvertListener {
        void onConvertSuccess();

        void onConvertProgress(int progress);

        void onConvertError();
    }


}
