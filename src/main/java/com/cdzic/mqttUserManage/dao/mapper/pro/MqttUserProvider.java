package com.cdzic.mqttUserManage.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Date 2019/05/20 上午 11:11
 * @Author 靳东
 * @ClassName MqttUserProvider
 */
public class MqttUserProvider {

    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.device_name as deviceName,a.appkey,a.device_secret as deviceSecret,a.device_nick as deviceNick,a.is_enable as isEnable,a.create_date as createDate");
                FROM("mqtt_user as a");
                if (null != map.get("deviceName")) {
                    WHERE("a.device_name = #{deviceName}");
                }
                if (null != map.get("appkey")) {
                    WHERE("a.appkey = #{appkey}");
                }
                if (null!=map.get("deviceNick")){
                    WHERE("a.device_nick like concat('%',#{deviceNick},'%')");
                }
                ORDER_BY("create_date desc");
            }
        }.toString();
    }

    public String findByCondition1(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.device_name as deviceName,a.appkey,a.device_secret_decode as deviceSecretDecode," +
                        "a.device_nick as deviceNick,a.is_enable as isEnable,a.mqtt_user_rh_id as mqttUserRhId,a.create_date as createDate");
                FROM("mqtt_user as a");
                if (null != map.get("deviceName")) {
                    WHERE("a.device_name = #{deviceName}");
                }
                if (null != map.get("appkey")) {
                    WHERE("a.appkey = #{appkey}");
                }
                if (null!=map.get("deviceNick")){
                    WHERE("a.device_nick like concat('%',#{deviceNick},'%')");
                }
                if (null!=map.get("id")){
                    WHERE("mqtt_user_rh_id like concat('%',#{id},'%')");
                }
                ORDER_BY("create_date desc");
            }
        }.toString();
    }
}
