package com.cdzic.mqttUserManage.dao.mapper.sys;

import com.cdzic.mqttUserManage.dao.mapper.sys.pro.SysPermissionProvider;
import com.cdzic.mqttUserManage.domain.entity.shiro.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/11/17 10:34
 * @describe:
 */
public interface SysPermissionMapper {
    @SelectProvider(type = SysPermissionProvider.class,method = "findByCondition")
    List<SysPermission> findByCondition(@Param("permission") String permission);

}
