package com.cdzic.mqttUserManage.dao;

import com.cdzic.mqttUserManage.domain.MqttUserRh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @creator yaotao
 * @date 2019/5/10 14:06
 * @describe:
 */
public interface MqttUserRhRepo extends JpaRepository<MqttUserRh,String> {

    List<MqttUserRh> findByAppkey(int appkey);
    @Modifying
    @Query("update MqttUserRh set rNum=(rNum-1) where appkey=?1")
    void updateRNum(Integer appkey);
}
