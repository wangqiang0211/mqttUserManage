package com.cdzic.mqttUserManage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @creator yaotao
 * @date 2019/5/10 13:57
 * @describe:
 */
@Entity
public class MqttUser implements Serializable {

    private static final long serialVersionUID = -7969179550277093856L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deviceName;//自增id 搜索条件1
    private Integer appkey;// 搜索条件2
    private String deviceSecret;//sha256后的值
    private String deviceSecretDecode;//设备散列值
    private Integer isSuperuser;//0：普通用户 1：超级用户 默认为0
    private String deviceNick;//设备名称 搜索条件3
    private Integer isEnable;//启用/停用 0 正常  1 异常
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;//创建时间
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "mqtt_user_rh_id")
    private MqttUserRh mqttUserRh;//关联表

    public MqttUser() {
    }

    public MqttUser(Integer appkey, String deviceSecret, String deviceSecretDecode, Integer isSuperuser, String deviceNick, Integer isEnable, Date createDate, MqttUserRh mqttUserRh) {
        this.appkey = appkey;
        this.deviceSecret = deviceSecret;
        this.deviceSecretDecode = deviceSecretDecode;
        this.isSuperuser = isSuperuser;
        this.deviceNick = deviceNick;
        this.isEnable = isEnable;
        this.createDate = createDate;
        this.mqttUserRh = mqttUserRh;
    }

    public Integer getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(Integer deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getAppkey() {
        return appkey;
    }

    public void setAppkey(Integer appkey) {
        this.appkey = appkey;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    public String getDeviceSecretDecode() {
        return deviceSecretDecode;
    }

    public void setDeviceSecretDecode(String deviceSecretDecode) {
        this.deviceSecretDecode = deviceSecretDecode;
    }

    public Integer getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(Integer isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    public String getDeviceNick() {
        return deviceNick;
    }

    public void setDeviceNick(String deviceNick) {
        this.deviceNick = deviceNick;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public MqttUserRh getMqttUserRh() {
        return mqttUserRh;
    }

    public void setMqttUserRh(MqttUserRh mqttUserRh) {
        this.mqttUserRh = mqttUserRh;
    }

    @Override
    public String toString() {
        return "MqttUser{" +
                "deviceName=" + deviceName +
                ", appkey=" + appkey +
                ", deviceSecret='" + deviceSecret + '\'' +
                ", deviceSecretDecode='" + deviceSecretDecode + '\'' +
                ", isSuperuser=" + isSuperuser +
                ", deviceNick='" + deviceNick + '\'' +
                ", isEnable=" + isEnable +
                ", createDate=" + createDate +
                ", mqttUserRh=" + mqttUserRh +
                '}';
    }
}
