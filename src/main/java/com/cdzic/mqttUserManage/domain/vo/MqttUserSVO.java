package com.cdzic.mqttUserManage.domain.vo;

/**
 * @creator yaotao
 * @date 2019/5/10 14:15
 * @describe:mqttUser注册成功返回
 */
public class MqttUserSVO {
    private int deviceName;
    private int appkey;
    private String deviceSecret;
    private String deviceNick;
    private String createDate;

    public MqttUserSVO() {

    }

    public MqttUserSVO(int deviceName, int appkey, String deviceSecret, String deviceNick, String createDate) {
        this.deviceName = deviceName;
        this.appkey = appkey;
        this.deviceSecret = deviceSecret;
        this.deviceNick = deviceNick;
        this.createDate = createDate;
    }

    public int getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(int deviceName) {
        this.deviceName = deviceName;
    }

    public int getAppkey() {
        return appkey;
    }

    public void setAppkey(int appkey) {
        this.appkey = appkey;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    public String getDeviceNick() {
        return deviceNick;
    }

    public void setDeviceNick(String deviceNick) {
        this.deviceNick = deviceNick;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
