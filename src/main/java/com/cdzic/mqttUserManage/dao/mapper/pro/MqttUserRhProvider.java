package com.cdzic.mqttUserManage.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Date 2019/05/20 下午 01:46
 * @Author 靳东
 * @ClassName MqttUserRhProvider
 */
public class MqttUserRhProvider {

    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.appkey,a.r_num as rNum,a.create_date as createDate");
                FROM("mqtt_user_rh as a");
                if (null != map.get("appkey")) {
                    WHERE("a.appkey = #{appkey}");
                }
                ORDER_BY("create_date desc");
            }
        }.toString();
    }

}
