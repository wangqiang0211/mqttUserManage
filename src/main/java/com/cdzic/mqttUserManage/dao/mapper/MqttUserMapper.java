package com.cdzic.mqttUserManage.dao.mapper;

import com.cdzic.mqttUserManage.dao.mapper.pro.MqttUserProvider;
import com.cdzic.mqttUserManage.domain.MqttUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/05/20 上午 11:10
 * @Author 靳东
 * @ClassName MqttUserMapper
 */
public interface MqttUserMapper {

    @SelectProvider(type = MqttUserProvider.class, method = "findByCondition")
    List<MqttUser> findByCondition(@Param("deviceName") Integer deviceName, @Param("appkey") Integer appkey, @Param("deviceNick") String deviceNick);

    @SelectProvider(type = MqttUserProvider.class, method = "findByCondition1")
    List<MqttUser> findByCondition1(@Param("deviceName") Integer deviceName, @Param("appkey") Integer appkey, @Param("deviceNick") String deviceNick, @Param("id") String id);
}
