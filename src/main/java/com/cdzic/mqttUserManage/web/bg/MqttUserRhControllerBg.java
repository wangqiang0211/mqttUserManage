package com.cdzic.mqttUserManage.web.bg;

import com.cdzic.mqttUserManage.domain.MqttUserRh;
import com.cdzic.mqttUserManage.domain.resp.RespObjSqlItem;
import com.cdzic.mqttUserManage.domain.resp.ResponseObj;
import com.cdzic.mqttUserManage.domain.vo.MqttUserSVO;
import com.cdzic.mqttUserManage.service.MqttUserRhService;
import com.cdzic.mqttUserManage.service.MqttUserService;
import com.cdzic.mqttUserManage.web.MqttUserController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Date 2019/05/20 下午 01:32
 * @Author 靳东
 * @ClassName MqttUserRhControllerBg
 */
@RestController
@RequestMapping("/bg")
public class MqttUserRhControllerBg {
    private static final Logger LOGGER = LogManager.getLogger(MqttUserController.class);
    @Autowired
    private MqttUserService mqttUserService;
    @Autowired
    private MqttUserRhService mqttUserRhService;

    /**
     * 设备列表
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @ApiOperation(value = "批量设备列表", notes = "批量设备列表")
    @GetMapping("/mqtt-user-rh/list")
    public ResponseObj commonUserQuery(@RequestParam int page,
                                       @RequestParam int limit,
                                       @RequestParam(required = false, value = "searchKey") String searchKey,
                                       @RequestParam(required = false, value = "searchValue") String searchValue) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<MqttUserRh> list = mqttUserRhService.findByCondition(searchKey, searchValue);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }


    /**
     * 批量添加mqttUser
     *
     * @param num 大于0且小于等于100
     * @return
     */
    @ApiOperation(value = "批量添加设备", notes = "批量添加设备")
    @GetMapping("/batch/add/user")
    public ResponseObj batchAddMqttUser(@RequestParam(required = false, value = "num") Integer num) {
        if (!"".equals(num) && null != num) {
            if (num > 100 || num <= 0) {
                return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "注册数量应当大于0且小于等于100！", new RespObjSqlItem(null, null));
            }
            try {
                int appkey = 201961;
                Page<Object> startPage = PageHelper.startPage(1, 100);
                List<MqttUserSVO> mqttUserRSs = mqttUserService.batchAddMqttUser(num, appkey);
                return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "设备添加成功！", new RespObjSqlItem(startPage.getTotal(), mqttUserRSs));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(e.getMessage());
                return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "设备添加失败！", new RespObjSqlItem(null, null));
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error(e.getMessage());
                return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "设备添加失败！", new RespObjSqlItem(null, null));
            }
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "", new RespObjSqlItem(null, null));
    }


}
