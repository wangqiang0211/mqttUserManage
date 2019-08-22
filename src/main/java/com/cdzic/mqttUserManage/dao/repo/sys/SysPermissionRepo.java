package com.cdzic.mqttUserManage.dao.repo.sys;

import com.cdzic.mqttUserManage.domain.entity.shiro.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SysPermissionRepo extends JpaRepository<SysPermission,Long> {
    SysPermission findFirstByPermission(String permission);

    @Modifying
    @Query("update  SysPermission set permission=?1,description=?2 where id=?3")
    int updatePermissionAndDescription(String permission, String description, long id);


}
