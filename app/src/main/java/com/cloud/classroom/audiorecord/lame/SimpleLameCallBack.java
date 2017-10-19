package com.cloud.classroom.audiorecord.lame;

public interface SimpleLameCallBack {
    void onProgress(int progress);

    void onError(String message);
}
