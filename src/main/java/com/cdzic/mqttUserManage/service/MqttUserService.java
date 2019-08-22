package com.cdzic.mqttUserManage.service;

import com.cdzic.mqttUserManage.domain.MqttUser;
import com.cdzic.mqttUserManage.domain.vo.MqttUserSVO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @creator yaotao
 * @date 2019/5/10 14:08
 * @describe:
 */
public interface MqttUserService {

    /**
     * 注册添加单个mqttUser
     * @param appkey
     * @param deviceNick
     * @return
     */
    MqttUserSVO addMqttUser(int appkey, String deviceNick) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 批量注册添加mqttUser
     * @param num
     * @param appkey
     * @return
     */
    List<MqttUserSVO> batchAddMqttUser(int num, int appkey) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 通过deviceName和appkey获取user
     * @param deviceName
     * @param appkey
     * @return
     */
    MqttUserSVO getMqttUser(Integer deviceName,Integer appkey);

    /**
     * 展示mqttUser
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<MqttUser> findByCondition(String searchKey, String searchValue);

    /**
     * 展示mqttUserRh
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<MqttUser> findByCondition1(String searchKey, String searchValue,String id);

    /**
     * 删除mqttUser
     * @param deviceName
     */
    void delete(Integer deviceName);

    /**
     * 修改mqttUser
     * @param deviceName
     * @param appkey
     * @param deviceSecret
     * @param deviceNick
     * @param isEnable
     * @return
     */
    int updateMqttUserById(Integer deviceName,Integer appkey,String deviceSecret,String deviceNick,Integer isEnable);
}
