package com.cdzic.mqttUserManage.dao;

import com.cdzic.mqttUserManage.domain.MqttUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @creator yaotao
 * @date 2019/5/10 14:06
 * @describe:
 */
public interface MqttUserRepo extends JpaRepository<MqttUser, Integer> {
    MqttUser findTopByAppkeyAndDeviceNick(int appkey, String deviceNick);

    MqttUser findTopByAppkeyAndDeviceName(int appkey, int deviceName);

    @Modifying
    @Query("update MqttUser set appkey=?2,deviceSecret=?3,deviceNick=?4,isEnable=?5 where deviceName=?1")
    int updateMqttUserByDeviceName(Integer deviceName, Integer appkey, String deviceSecret, String deviceNick, Integer isEnable);
}
