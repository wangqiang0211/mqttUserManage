package com.cdzic.mqttUserManage.dao.repo.sys;

import com.cdzic.mqttUserManage.domain.entity.shiro.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysRoleRepo extends JpaRepository<SysRole,Long> {
    SysRole findFirstByRoleName(String roleName);
}
