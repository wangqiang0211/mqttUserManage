package com.cdzic.mqttUserManage.web.bg;

import com.cdzic.mqttUserManage.domain.MqttUser;
import com.cdzic.mqttUserManage.domain.resp.RespObjSqlItem;
import com.cdzic.mqttUserManage.domain.resp.ResponseObj;
import com.cdzic.mqttUserManage.service.MqttUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019/05/18 下午 04:13
 * @Author 靳东
 * @ClassName MqttUserControllerBg
 */
@RestController
@RequestMapping("/bg")
public class MqttUserControllerBg {
    private static final Logger LOGGER = LogManager.getLogger(MqttUserControllerBg.class);

    @Autowired
    private MqttUserService mqttUserService;


    /**
     * 设备列表
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @ApiOperation(value = "批量设备注册后的单个设备列表", notes = "批量设备注册后的单个设备列表")
    @GetMapping("/mqtt-user/list")
    public ResponseObj mqttUserQuery(@RequestParam int page,
                                       @RequestParam int limit,
                                       @RequestParam(required = false, value = "searchKey") String searchKey,
                                       @RequestParam(required = false, value = "searchValue") String searchValue) {

        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<MqttUser> list = mqttUserService.findByCondition(searchKey, searchValue);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }

    /**
     * 单次注册的设备列表详情
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @ApiOperation(value = "单次注册的设备列表详情", notes = "单次注册的设备列表详情")
    @GetMapping("/mqtt-user/num")
    public ResponseObj mqttUserQueryNum(@RequestParam int page,
                                       @RequestParam int limit,
                                       @RequestParam(required = false, value = "searchKey") String searchKey,
                                       @RequestParam(required = false, value = "searchValue") String searchValue,
                                       @RequestParam String id) {

        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<MqttUser> list = mqttUserService.findByCondition1(searchKey, searchValue,id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }

    /**
     * 根据mqttUser deviceName(id)删除
     * @param deviceName
     * @return
     */
    @ApiOperation(value = "根据mqttUser deviceName(id)删除", notes = "根据mqttUser deviceName(id)删除")
    @PostMapping("/mqtt-user/delete")
    public ResponseObj mqttUserDelete(@RequestParam Integer deviceName){
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        try {
            mqttUserService.delete(deviceName);
        }catch (Exception e){
            LOGGER.error("删除失败！原因：{}"+e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
        }
        return responseObj;
    }

    /**
     * 修改mqttUser
     * @param deviceName
     * @param appkey
     * @param deviceSecret
     * @param deviceNick
     * @param isEnable
     * @return
     */
    @ApiOperation(value = "修改mqttUser", notes = "修改mqttUser")
    @PostMapping("/mqtt-user/update")
    public ResponseObj mqttUserUpdate(@RequestParam Integer deviceName,
                                      @RequestParam Integer appkey,
                                      @RequestParam String deviceSecret,
                                      @RequestParam String deviceNick,
                                      @RequestParam Integer isEnable){

        mqttUserService.updateMqttUserById(deviceName,appkey,deviceSecret,deviceNick,isEnable);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS,"修改成功",null);
    }

}
