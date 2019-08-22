package com.cdzic.mqttUserManage.dao.mapper;

import com.cdzic.mqttUserManage.dao.mapper.pro.MqttUserRhProvider;
import com.cdzic.mqttUserManage.domain.MqttUserRh;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/05/20 下午 01:44
 * @Author 靳东
 * @ClassName MqttUserRhMapper
 */
public interface MqttUserRhMapper {

    @SelectProvider(type = MqttUserRhProvider.class, method = "findByCondition")
    List<MqttUserRh> findByCondition(@Param("appkey") Integer appkey);
}
