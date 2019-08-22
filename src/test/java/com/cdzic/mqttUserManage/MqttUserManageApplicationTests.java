package com.cdzic.mqttUserManage;

import com.cdzic.mqttUserManage.utils.CommonUtil;
import com.cdzic.mqttUserManage.utils.CryptoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MqttUserManageApplicationTests {

//    @Test
    public void contextLoads() {
        Bean1 bean1_1 = new Bean1("我是A");
        Bean1 bean1_2 = new Bean1("我是B");
        BeanUtils.copyProperties(bean1_1, bean1_2);//是A中的值付给B
        System.out.println(bean1_1.a);
        System.out.println(bean1_2.a);
        String subString = CommonUtil.get32UUID();
        String md5 = CryptoUtils.encodeMD5(subString);
        System.out.println(md5);//11e7338815cc4095a74007e0d0436e6b
    }


    public class Bean1 {
        public String a;

        public Bean1(String a) {
            this.a = a;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }


    }

    @Test
    public void TestBean1(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        System.out.println("打印uuid="+uuid);
        System.out.println("uuid的长度="+uuid.length());
        String mqttUserRhId = CommonUtil.get32UUID();
        System.out.println("打印mqttUserRhId="+mqttUserRhId);
        System.out.println("uuid的长度="+mqttUserRhId.length());
    }

}
