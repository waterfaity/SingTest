package com.xueduoduo.singtest;

import java.util.List;

/**
 * @author water_fairy
 * @email 995637517@qq.com
 * @date 2017/10/19
 * @Description:
 */

public class SingEvaluateBean {

    /**
     * recordId : 11e7b49269f6d83c9ea5t137b20158d
     * tokenId : 59e83ed1332793000003b930
     * applicationId : t137
     * audioUrl : http://trial.files.cloud.ssapi.cn:8080/t137/11e7b49269f6d83c9ea5t137b20158d
     * dtLastResponse : 2017-10-19 13:57:42:13
     * params : {"app":{"timestamp":"1508392657","sig":"d5bbc30de5dd2b4bde96ea9935983cdcdef10a00","applicationId":"t137","userId":"guest","clientId":""},"request":{"coreType":"cn.sent.score","tokenId":"59e83ed1332793000003b930","refText":"你好","rank":100,"attachAudioUrl":1},"audio":{"sampleRate":16000,"channel":1,"sampleBytes":2,"audioType":"ogg"}}
     * eof : 1
     * refText : 你好
     * result : {"useref":1,"version":"0.0.80.2017.8.31.13:38:15","rank":100,"res":"chn.snt.fb.0.1","tone":95,"integrity":100,"forceout":0,"pron":50,"delaytime":35,"textmode":0,"systime":2602,"pretime":0,"usehookw":0,"overall":50,"phn":42,"info":{"volume":488,"clip":0,"snr":0,"tipId":10006},"accuracy":43,"details":[{"fluency":96,"pron":49,"dur":120,"phn":43,"phone":[{"char":"n","score":85},{"char":"i","score":0}],"tone":3,"chn_char":"你","overall":49,"start":390,"confidence":[0,27,17,18,38],"char":"ni","tonescore":89,"end":510,"score":49},{"fluency":85,"pron":52,"dur":90,"phn":44,"phone":[{"char":"h","score":87},{"char":"ao","score":0}],"tone":4,"chn_char":"好","overall":52,"start":2040,"confidence":[0,0,2,32,66],"char":"hao","tonescore":100,"end":2130,"score":52}],"wavetime":2550,"fluency":{"pause":0,"overall":22,"speed":706}}
     */

    private String recordId;//音频文件的唯一标识
    private String tokenId;//用户请求的唯一标识
    private String applicationId;//授权账号，即用户的 Appkey
    private String audioUrl;//音频url
    private String dtLastResponse;//云端响应的时间点
    private ParamsBean params;//回显客户端传入的评测请求参数
    private int eof;
    private String refText;//传入的评测文本
    private ResultBean result;//评测结果

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getDtLastResponse() {
        return dtLastResponse;
    }

    public void setDtLastResponse(String dtLastResponse) {
        this.dtLastResponse = dtLastResponse;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getEof() {
        return eof;
    }

    public void setEof(int eof) {
        this.eof = eof;
    }

    public String getRefText() {
        return refText;
    }

    public void setRefText(String refText) {
        this.refText = refText;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ParamsBean {
        /**
         * app : {"timestamp":"1508392657","sig":"d5bbc30de5dd2b4bde96ea9935983cdcdef10a00","applicationId":"t137","userId":"guest","clientId":""}
         * request : {"coreType":"cn.sent.score","tokenId":"59e83ed1332793000003b930","refText":"你好","rank":100,"attachAudioUrl":1}
         * audio : {"sampleRate":16000,"channel":1,"sampleBytes":2,"audioType":"ogg"}
         */

        private AppBean app;
        private RequestBean request;
        private AudioBean audio;

        public AppBean getApp() {
            return app;
        }

        public void setApp(AppBean app) {
            this.app = app;
        }

        public RequestBean getRequest() {
            return request;
        }

        public void setRequest(RequestBean request) {
            this.request = request;
        }

        public AudioBean getAudio() {
            return audio;
        }

        public void setAudio(AudioBean audio) {
            this.audio = audio;
        }

        public static class AppBean {
            /**
             * timestamp : 1508392657
             * sig : d5bbc30de5dd2b4bde96ea9935983cdcdef10a00
             * applicationId : t137
             * userId : guest
             * clientId :
             */

            private String timestamp;
            private String sig;
            private String applicationId;
            private String userId;
            private String clientId;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getSig() {
                return sig;
            }

            public void setSig(String sig) {
                this.sig = sig;
            }

            public String getApplicationId() {
                return applicationId;
            }

            public void setApplicationId(String applicationId) {
                this.applicationId = applicationId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getClientId() {
                return clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }
        }

        public static class RequestBean {
            /**
             * coreType : cn.sent.score
             * tokenId : 59e83ed1332793000003b930
             * refText : 你好
             * rank : 100
             * attachAudioUrl : 1
             */

            private String coreType;
            private String tokenId;
            private String refText;
            private int rank;
            private int attachAudioUrl;

            public String getCoreType() {
                return coreType;
            }

            public void setCoreType(String coreType) {
                this.coreType = coreType;
            }

            public String getTokenId() {
                return tokenId;
            }

            public void setTokenId(String tokenId) {
                this.tokenId = tokenId;
            }

            public String getRefText() {
                return refText;
            }

            public void setRefText(String refText) {
                this.refText = refText;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public int getAttachAudioUrl() {
                return attachAudioUrl;
            }

            public void setAttachAudioUrl(int attachAudioUrl) {
                this.attachAudioUrl = attachAudioUrl;
            }
        }

        public static class AudioBean {
            /**
             * sampleRate : 16000
             * channel : 1
             * sampleBytes : 2
             * audioType : ogg
             */

            private int sampleRate;
            private int channel;
            private int sampleBytes;
            private String audioType;

            public int getSampleRate() {
                return sampleRate;
            }

            public void setSampleRate(int sampleRate) {
                this.sampleRate = sampleRate;
            }

            public int getChannel() {
                return channel;
            }

            public void setChannel(int channel) {
                this.channel = channel;
            }

            public int getSampleBytes() {
                return sampleBytes;
            }

            public void setSampleBytes(int sampleBytes) {
                this.sampleBytes = sampleBytes;
            }

            public String getAudioType() {
                return audioType;
            }

            public void setAudioType(String audioType) {
                this.audioType = audioType;
            }
        }
    }

    public static class ResultBean {
        /**
         * useref : 1
         * version : 0.0.80.2017.8.31.13:38:15
         * rank : 100
         * res : chn.snt.fb.0.1
         * tone : 95
         * integrity : 100
         * forceout : 0
         * pron : 50
         * delaytime : 35
         * textmode : 0
         * systime : 2602
         * pretime : 0
         * usehookw : 0
         * overall : 50
         * phn : 42
         * info : {"volume":488,"clip":0,"snr":0,"tipId":10006}
         * accuracy : 43
         * details : [{"fluency":96,"pron":49,"dur":120,"phn":43,"phone":[{"char":"n","score":85},{"char":"i","score":0}],"tone":3,"chn_char":"你","overall":49,"start":390,"confidence":[0,27,17,18,38],"char":"ni","tonescore":89,"end":510,"score":49},{"fluency":85,"pron":52,"dur":90,"phn":44,"phone":[{"char":"h","score":87},{"char":"ao","score":0}],"tone":4,"chn_char":"好","overall":52,"start":2040,"confidence":[0,0,2,32,66],"char":"hao","tonescore":100,"end":2130,"score":52}]
         * wavetime : 2550
         * fluency : {"pause":0,"overall":22,"speed":706}
         */

        private int useref;
        private String version;//引擎的版本，包含发布时间
        private int rank;//评分制度
        private String res;//评测本题型时，使用的资源名称
        private int tone;
        private int integrity;
        private int forceout;
        private int pron;//发音分
        private int delaytime;//云端从 feed 音频结束到获取结果  的耗时，单位 ms
        private int textmode;
        private int systime;//评测整个过程的总耗时，单位 ms
        private int pretime;//云端调用 Start 接口本身耗时，单 位ms
        private int usehookw;
        private int overall;//总分
        private int phn;
        private InfoBean info;
        private int accuracy;
        private int wavetime;//音频时长，单位 ms
        private FluencyBean fluency;
        private List<DetailsBean> details;//详情

        public int getUseref() {
            return useref;
        }

        public void setUseref(int useref) {
            this.useref = useref;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }

        public int getTone() {
            return tone;
        }

        public void setTone(int tone) {
            this.tone = tone;
        }

        public int getIntegrity() {
            return integrity;
        }

        public void setIntegrity(int integrity) {
            this.integrity = integrity;
        }

        public int getForceout() {
            return forceout;
        }

        public void setForceout(int forceout) {
            this.forceout = forceout;
        }

        public int getPron() {
            return pron;
        }

        public void setPron(int pron) {
            this.pron = pron;
        }

        public int getDelaytime() {
            return delaytime;
        }

        public void setDelaytime(int delaytime) {
            this.delaytime = delaytime;
        }

        public int getTextmode() {
            return textmode;
        }

        public void setTextmode(int textmode) {
            this.textmode = textmode;
        }

        public int getSystime() {
            return systime;
        }

        public void setSystime(int systime) {
            this.systime = systime;
        }

        public int getPretime() {
            return pretime;
        }

        public void setPretime(int pretime) {
            this.pretime = pretime;
        }

        public int getUsehookw() {
            return usehookw;
        }

        public void setUsehookw(int usehookw) {
            this.usehookw = usehookw;
        }

        public int getOverall() {
            return overall;
        }

        public void setOverall(int overall) {
            this.overall = overall;
        }

        public int getPhn() {
            return phn;
        }

        public void setPhn(int phn) {
            this.phn = phn;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(int accuracy) {
            this.accuracy = accuracy;
        }

        public int getWavetime() {
            return wavetime;
        }

        public void setWavetime(int wavetime) {
            this.wavetime = wavetime;
        }

        public FluencyBean getFluency() {
            return fluency;
        }

        public void setFluency(FluencyBean fluency) {
            this.fluency = fluency;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class InfoBean {
            /**
             * volume : 488
             * clip : 0
             * snr : 0
             * tipId : 10006
             */

            private int volume;
            private int clip;
            private float snr;
            private int tipId;

            public int getVolume() {
                return volume;
            }

            public void setVolume(int volume) {
                this.volume = volume;
            }

            public int getClip() {
                return clip;
            }

            public void setClip(int clip) {
                this.clip = clip;
            }

            public float getSnr() {
                return snr;
            }

            public void setSnr(float snr) {
                this.snr = snr;
            }

            public int getTipId() {
                return tipId;
            }

            public void setTipId(int tipId) {
                this.tipId = tipId;
            }
        }

        public static class FluencyBean {
            /**
             * pause : 0
             * overall : 22
             * speed : 706
             */

            private int pause;
            private int overall;
            private int speed;

            public int getPause() {
                return pause;
            }

            public void setPause(int pause) {
                this.pause = pause;
            }

            public int getOverall() {
                return overall;
            }

            public void setOverall(int overall) {
                this.overall = overall;
            }

            public int getSpeed() {
                return speed;
            }

            public void setSpeed(int speed) {
                this.speed = speed;
            }
        }

        public static class DetailsBean {
            /**
             * fluency : 96
             * pron : 49
             * dur : 120
             * phn : 43
             * phone : [{"char":"n","score":85},{"char":"i","score":0}]
             * tone : 3
             * chn_char : 你
             * overall : 49
             * start : 390
             * confidence : [0,27,17,18,38]
             * char : ni
             * tonescore : 89
             * end : 510
             * score : 49
             */

            private int fluency;
            private int pron;
            private int dur;//单词发音时间，单位 ms
            private int phn;
            private int tone;
            private String chn_char;
            private int overall;
            private int start;//单词在音频中的起始时间,单位 ms
            @com.google.gson.annotations.SerializedName("char")
            private String charX;//规整后的单词文本
            private int tonescore;
            private int end;//单词在音频中的结束时间，单位 ms
            private int score;//单词得分
            private List<PhoneBean> phone;//音素级评分结果
            private List<Integer> confidence;

            public int getFluency() {
                return fluency;
            }

            public void setFluency(int fluency) {
                this.fluency = fluency;
            }

            public int getPron() {
                return pron;
            }

            public void setPron(int pron) {
                this.pron = pron;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public int getPhn() {
                return phn;
            }

            public void setPhn(int phn) {
                this.phn = phn;
            }

            public int getTone() {
                return tone;
            }

            public void setTone(int tone) {
                this.tone = tone;
            }

            public String getChn_char() {
                return chn_char;
            }

            public void setChn_char(String chn_char) {
                this.chn_char = chn_char;
            }

            public int getOverall() {
                return overall;
            }

            public void setOverall(int overall) {
                this.overall = overall;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public String getCharX() {
                return charX;
            }

            public void setCharX(String charX) {
                this.charX = charX;
            }

            public int getTonescore() {
                return tonescore;
            }

            public void setTonescore(int tonescore) {
                this.tonescore = tonescore;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public List<PhoneBean> getPhone() {
                return phone;
            }

            public void setPhone(List<PhoneBean> phone) {
                this.phone = phone;
            }

            public List<Integer> getConfidence() {
                return confidence;
            }

            public void setConfidence(List<Integer> confidence) {
                this.confidence = confidence;
            }

            public static class PhoneBean {
                /**
                 * char : n
                 * score : 85
                 */

                @com.google.gson.annotations.SerializedName("char")
                private String charX;//音素
                private int score;//音素得分(0-100)

                public String getCharX() {
                    return charX;
                }

                public void setCharX(String charX) {
                    this.charX = charX;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }
            }
        }
    }
}
