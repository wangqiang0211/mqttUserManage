package com.cdzic.mqttUserManage.service.shiro.impl;

import com.cdzic.mqttUserManage.dao.repo.sys.SysRoleRepo;
import com.cdzic.mqttUserManage.domain.entity.shiro.SysRole;
import com.cdzic.mqttUserManage.service.shiro.AuthRolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class AuthRolerServiceImpl implements AuthRolerService {
    @Autowired
    private SysRoleRepo sysRoleDao;
    @Override
    public SysRole find() {
        SysRole roleName = sysRoleDao.findFirstByRoleName("角色");
        roleName.getPermissions().size();
        return roleName;
    }
}
