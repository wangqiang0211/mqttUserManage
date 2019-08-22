package com.cdzic.mqttUserManage.service.impl;

import com.cdzic.mqttUserManage.dao.MqttUserRhRepo;
import com.cdzic.mqttUserManage.dao.mapper.MqttUserRhMapper;
import com.cdzic.mqttUserManage.domain.MqttUser;
import com.cdzic.mqttUserManage.domain.MqttUserRh;
import com.cdzic.mqttUserManage.domain.vo.MqttUserRhSVO;
import com.cdzic.mqttUserManage.domain.vo.MqttUserSVO;
import com.cdzic.mqttUserManage.service.MqttUserRhService;
import com.cdzic.mqttUserManage.service.MqttUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @creator yaotao
 * @date 2019/5/10 15:23
 * @describe:
 */
@Service
@Transactional
public class MqttUserRhServiceImpl implements MqttUserRhService{
    @Autowired
    private MqttUserRhRepo mqttUserRhRepo;
    @Autowired
    private MqttUserRhMapper mqttUserRhMapper;

    @Override
    public List<MqttUserRhSVO> query(Integer appkey) {
        List<MqttUserRh> mqttUserRhList;
        if (null==appkey){
            mqttUserRhList=mqttUserRhRepo.findAll();
        }else {
            mqttUserRhList=mqttUserRhRepo.findByAppkey(appkey);
        }
        List<MqttUserRhSVO> mqttUserRhSVOList=new ArrayList<>();
        for (int i = 0; i < mqttUserRhList.size(); i++) {
            MqttUserRhSVO mqttUserRhSVO=new MqttUserRhSVO();
            BeanUtils.copyProperties(mqttUserRhList.get(i),mqttUserRhSVO);
            mqttUserRhSVO.setCreateDate(mqttUserRhList.get(i).getCreateDate()+"");
            mqttUserRhSVOList.add(mqttUserRhSVO);
        }
        return mqttUserRhSVOList;
    }

    @Override
    public List<MqttUserSVO> queryRhUsers(String id) {
        MqttUserRh mqttUserRh = mqttUserRhRepo.findById(id).get();
        List<MqttUserSVO> mqttUserSVOList=new ArrayList<>();
        if (null!=mqttUserRh){
            List<MqttUser> mqttUsers = mqttUserRh.getMqttUsers();
            for (int i = 0; i < mqttUsers.size(); i++) {
                MqttUserSVO mqttUserSVO=new MqttUserSVO();
                BeanUtils.copyProperties(mqttUsers.get(i),mqttUserSVO);
                mqttUserSVO.setDeviceSecret(mqttUsers.get(i).getDeviceSecretDecode());
                mqttUserSVO.setCreateDate(mqttUsers.get(i).getCreateDate()+"");
                mqttUserSVOList.add(mqttUserSVO);
            }
        }
        return mqttUserSVOList;
    }

    @Override
    public List<MqttUserRh> findByCondition(String searchKey, String searchValue) {
        Integer appkey = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("appkey".equals(searchKey)) {
                if (!"".equals(searchValue)) {
                    appkey = Integer.parseInt(searchValue);
                }
            }
        }
        return mqttUserRhMapper.findByCondition(appkey);
    }
}
