package com.cdzic.mqttUserManage.service;

import com.cdzic.mqttUserManage.domain.MqttUserRh;
import com.cdzic.mqttUserManage.domain.vo.MqttUserRhSVO;
import com.cdzic.mqttUserManage.domain.vo.MqttUserSVO;

import java.util.List;

/**
 * @creator yaotao
 * @date 2019/5/10 15:23
 * @describe:
 */
public interface MqttUserRhService {

    /**
     * 查询批量注册记录
     * @param appkey
     * @return
     */
    List<MqttUserRhSVO> query(Integer appkey);

    /**
     * 查询批量注册的user
     * @param id
     * @return
     */
    List<MqttUserSVO> queryRhUsers(String id);

    /**
     * 展示mqttUserRh
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<MqttUserRh> findByCondition(String searchKey, String searchValue);


}
