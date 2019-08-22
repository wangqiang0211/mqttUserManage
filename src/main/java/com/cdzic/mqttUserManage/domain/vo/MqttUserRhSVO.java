package com.cdzic.mqttUserManage.domain.vo;

/**
 * @creator yaotao
 * @date 2019/5/10 15:26
 * @describe:
 */
public class MqttUserRhSVO {
    private String id;//注册标记 UUID
    private Integer appkey;//注册appkey
    private Integer rNum;//注册数量
    private String createDate;//注册时间

    public MqttUserRhSVO() {
    }

    public MqttUserRhSVO(String id, Integer appkey, Integer rNum, String createDate) {
        this.id = id;
        this.appkey = appkey;
        this.rNum = rNum;
        this.createDate = createDate;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
