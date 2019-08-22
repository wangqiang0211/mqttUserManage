package com.cdzic.mqttUserManage.web;

import com.cdzic.mqttUserManage.domain.resp.RespObjSqlItem;
import com.cdzic.mqttUserManage.domain.resp.ResponseObj;
import com.cdzic.mqttUserManage.domain.vo.MqttUserRhSVO;
import com.cdzic.mqttUserManage.domain.vo.MqttUserSVO;
import com.cdzic.mqttUserManage.service.MqttUserRhService;
import com.cdzic.mqttUserManage.service.MqttUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @creator yaotao
 * @date 2019/5/10 14:09
 * @describe:
 */
@ApiIgnore
@RestController
public class MqttUserController {
    private static final Logger LOGGER = LogManager.getLogger(MqttUserController.class);
    @Autowired
    private MqttUserService mqttUserService;
    @Autowired
    private MqttUserRhService mqttUserRhService;


    /**
     * 添加单个mqttUser
     *
     * @param appkey
     * @param deviceNick
     * @return
     */
    @PostMapping("/add/user")
    public ResponseObj addMqttUser(@RequestParam int appkey,
                                   @RequestParam String deviceNick) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_FAIL);
        try {
            MqttUserSVO mqttUserRS = mqttUserService.addMqttUser(appkey, deviceNick);
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_SUCCESS);
            responseObj.setRespObj(mqttUserRS);
        } catch (UnsupportedEncodingException e) {//当请求特定的加密算法但在环境中不可用时抛出此异常。
            responseObj.setErrorMsg(e.getMessage());
            LOGGER.error(e.getMessage());
        } catch (NoSuchAlgorithmException e) {//字符编码不支持。
            responseObj.setErrorMsg(e.getMessage());
            LOGGER.error(e.getMessage());
        }
        return responseObj;
    }

    /**
     * 批量添加mqttUser
     *
     * @param num    大于0且小于等于100
//     * @param appkey
     * @return
     */
//    @GetMapping("/batch/add/user")
//    public ResponseObj batchAddMqttUser(@RequestParam(required = false, value = "num") Integer num) {
////        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_FAIL);
//        if (!"".equals(num) && null != num) {
//            if (num > 100 || num <= 0) {
////            responseObj.setErrorMsg("注册数量应当大于0且小于等于100！");
//                return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "注册数量应当大于0且小于等于100！", new RespObjSqlItem(null, null));
//            }
//            try {
//                int appkey = 201961;
//                Page<Object> startPage = PageHelper.startPage(1, 100);
//                List<MqttUserSVO> mqttUserRSs = mqttUserService.batchAddMqttUser(num, appkey);
////            responseObj.setErrorCode(ResponseObj.ERROR_CODE_SUCCESS);
////            responseObj.setRespObj(mqttUserRSs);
//                return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "设备添加成功！", new RespObjSqlItem(startPage.getTotal(), mqttUserRSs));
//            } catch (UnsupportedEncodingException e) {
////            responseObj.setErrorMsg(e.getMessage());
//                LOGGER.error(e.getMessage());
//                return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "设备添加失败！", new RespObjSqlItem(null, null));
//            } catch (NoSuchAlgorithmException e) {
////            responseObj.setErrorMsg(e.getMessage());
//                LOGGER.error(e.getMessage());
//                return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "设备添加失败！", new RespObjSqlItem(null, null));
//            }
////        return responseObj;
//        }
//        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "", new RespObjSqlItem(null, null));
//    }

    /**
     * 查询批量注册记录
     *
     * @param appkey
     * @return
     */
    @GetMapping("/query/rh")
    public ResponseObj queryRh(Integer appkey) {
        List<MqttUserRhSVO> mqttUserRhSVOList = mqttUserRhService.query(appkey);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, mqttUserRhSVOList);
    }

    /**
     * 查询批量注册的user
     *
     * @param id
     * @return
     */
    @GetMapping("/query/rh/users")
    public ResponseObj querRhUsers(@RequestParam String id) {
        List<MqttUserSVO> mqttUserSVOS = mqttUserRhService.queryRhUsers(id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, mqttUserSVOS);
    }

    /**
     * 查询user详情
     *
     * @param deviceName
     * @param appkey
     * @return
     */
    @GetMapping("/get/user")
    public ResponseObj getUser(@RequestParam Integer deviceName,
                               @RequestParam Integer appkey) {
        MqttUserSVO mqttUser = mqttUserService.getMqttUser(deviceName, appkey);
        if (null == mqttUser) {
            return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "设备不存在！");
        } else {
            return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS,"", mqttUser);
        }
    }


}
