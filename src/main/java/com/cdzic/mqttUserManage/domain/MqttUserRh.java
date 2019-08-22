package com.cdzic.mqttUserManage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @creator yaotao
 * @date 2019/5/10 13:51
 * @describe:
 */
@Entity
public class MqttUserRh implements Serializable{

    private static final long serialVersionUID = 1526320243783339572L;

    @Id
    private String id;//注册标记 UUID
    private Integer appkey;//注册appkey
    private Integer rNum;//注册数量
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;//注册时间
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "mqttUserRh")
    private List<MqttUser> mqttUsers;

    public MqttUserRh() {
    }

    public MqttUserRh(String id, Integer appkey, Integer rNum) {
        this.id = id;
        this.appkey = appkey;
        this.rNum = rNum;
    }

    public MqttUserRh(String id, Integer appkey, Integer rNum, Date createDate, List<MqttUser> mqttUsers) {
        this.id = id;
        this.appkey = appkey;
        this.rNum = rNum;
        this.createDate = createDate;
        this.mqttUsers = mqttUsers;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAppkey() {
        return appkey;
    }

    public void setAppkey(Integer appkey) {
        this.appkey = appkey;
    }

    public Integer getrNum() {
        return rNum;
    }

    public void setrNum(Integer rNum) {
        this.rNum = rNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<MqttUser> getMqttUsers() {
        return mqttUsers;
    }

    public void setMqttUsers(List<MqttUser> mqttUsers) {
        this.mqttUsers = mqttUsers;
    }
}
