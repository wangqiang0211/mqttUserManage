package com.cdzic.mqttUserManage.service.impl;

import com.cdzic.mqttUserManage.dao.MqttUserRepo;
import com.cdzic.mqttUserManage.dao.MqttUserRhRepo;
import com.cdzic.mqttUserManage.dao.mapper.MqttUserMapper;
import com.cdzic.mqttUserManage.domain.MqttUser;
import com.cdzic.mqttUserManage.domain.MqttUserRh;
import com.cdzic.mqttUserManage.domain.vo.MqttUserSVO;
import com.cdzic.mqttUserManage.service.MqttUserService;
import com.cdzic.mqttUserManage.utils.CommonUtil;
import com.cdzic.mqttUserManage.utils.CryptoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @creator yaotao
 * @date 2019/5/10 14:08
 * @describe:
 */
@Service
@Transactional
public class MqttUserServiceImpl implements MqttUserService {
    @Autowired
    private MqttUserRepo mqttUserRepo;
    @Autowired
    private MqttUserRhRepo mqttUserRhRepo;
    @Autowired
    private MqttUserMapper mqttUserMapper;

    @Override
    public MqttUserSVO addMqttUser(int appkey, String deviceNick) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //先查看是否有该设备
        MqttUser mqttUser = mqttUserRepo.findTopByAppkeyAndDeviceNick(appkey, deviceNick);
        //没有则注册新设备  有则直接返回设备
        if (null == mqttUser) {
            String noncestr = CommonUtil.createNoncestr(16);
            mqttUser = mqttUserRepo.save(new MqttUser(appkey, CryptoUtils.encodeSha256(noncestr), noncestr, 0, deviceNick, 1, new Date(), null));
        }
        MqttUserSVO mqttUserRS = new MqttUserSVO();
        BeanUtils.copyProperties(mqttUser, mqttUserRS);
        mqttUserRS.setDeviceSecret(mqttUser.getDeviceSecretDecode());
        mqttUserRS.setCreateDate(mqttUser.getCreateDate()+"");
        return mqttUserRS;
    }

    @Override
    public List<MqttUserSVO> batchAddMqttUser(int num, int appkey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String mqttUserRhId = CommonUtil.get32UUID();
        MqttUserRh mqttUserRh = new MqttUserRh(mqttUserRhId, appkey, num);
        List<MqttUser> mqttUsers = new ArrayList<>();
//       创建mqttUser
        for (int i = 0; i < num; i++) {
            String noncestr = CommonUtil.createNoncestr(16);
            mqttUsers.add(new MqttUser(appkey, CryptoUtils.encodeSha256(noncestr), noncestr, 0, mqttUserRhId + i, 1, new Date(), mqttUserRh));
        }
        mqttUserRh.setMqttUsers(mqttUsers);
        mqttUserRh.setCreateDate(new Date());
        mqttUserRh = mqttUserRhRepo.save(mqttUserRh);//保存到数据库
        int size = mqttUserRh.getMqttUsers().size();
        List<MqttUserSVO> mqttUserRSs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MqttUserSVO mqttUserRS = new MqttUserSVO();
            BeanUtils.copyProperties(mqttUserRh.getMqttUsers().get(i), mqttUserRS);
            mqttUserRS.setDeviceSecret(mqttUserRh.getMqttUsers().get(i).getDeviceSecretDecode());
            mqttUserRS.setCreateDate(mqttUserRh.getMqttUsers().get(i).getCreateDate()+"");
            mqttUserRSs.add(mqttUserRS);
        }
        return mqttUserRSs;
    }

    @Override
    public MqttUserSVO getMqttUser(Integer deviceName, Integer appkey) {
        MqttUser mqttUser = mqttUserRepo.findTopByAppkeyAndDeviceName(appkey, deviceName);
        MqttUserSVO mqttUserSVO = new MqttUserSVO();
        BeanUtils.copyProperties(mqttUser, mqttUserSVO);//BeanUtils.copyProperties("转换后的类", "要转换的类"); 是A中的值付给B
        mqttUserSVO.setDeviceSecret(mqttUser.getDeviceSecretDecode());
        mqttUserSVO.setCreateDate(mqttUser.getCreateDate()+"");
        return mqttUserSVO;
    }

    @Override
    public List<MqttUser> findByCondition(String searchKey, String searchValue) {
        Integer deviceName = null,appkey = null;
        String deviceNick = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("deviceName".equals(searchKey)) {
                if (!"".equals(searchValue)) {
                    deviceName = Integer.parseInt(searchValue);
                }
            }else if ("appkey".equals(searchKey)) {
                if (!"".equals(searchValue)) {
                    appkey = Integer.parseInt(searchValue);
                }
            }
        }
        if (null != searchKey && !"".equals(searchKey)) {
            if ("deviceNick".equals(searchKey)) {
                deviceNick = searchValue;
            }
        }
        return mqttUserMapper.findByCondition(deviceName,appkey,deviceNick);
    }

    @Override
    public List<MqttUser> findByCondition1(String searchKey, String searchValue, String id) {
        Integer deviceName = null,appkey = null;
        String deviceNick = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("deviceName".equals(searchKey)) {
                if (!"".equals(searchValue)) {
                    deviceName = Integer.parseInt(searchValue);
                }
            }else if ("appkey".equals(searchKey)) {
                if (!"".equals(searchValue)) {
                    appkey = Integer.parseInt(searchValue);
                }
            }
        }
        if (null != searchKey && !"".equals(searchKey)) {
            if ("deviceNick".equals(searchKey)) {
                deviceNick = searchValue;
            }
        }
        return mqttUserMapper.findByCondition1(deviceName,appkey,deviceNick,id);
    }

    @Override
    public void delete(Integer deviceName) {
        MqttUser mqttUser = mqttUserRepo.findById(deviceName).get();
        mqttUserRepo.deleteById(deviceName);
        mqttUserRhRepo.updateRNum(mqttUser.getAppkey());
    }

    @Override
    public int updateMqttUserById(Integer deviceName, Integer appkey, String deviceSecret, String deviceNick, Integer isEnable) {
        return mqttUserRepo.updateMqttUserByDeviceName(deviceName,appkey,deviceSecret,deviceNick,isEnable);
    }


}
