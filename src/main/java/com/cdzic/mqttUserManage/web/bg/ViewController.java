package com.cdzic.mqttUserManage.web.bg;

import com.cdzic.mqttUserManage.domain.entity.shiro.SysPermission;
import com.cdzic.mqttUserManage.domain.entity.shiro.SysRole;
import com.cdzic.mqttUserManage.domain.entity.shiro.SysUser;
import com.cdzic.mqttUserManage.domain.resp.ResponseObj;
import com.cdzic.mqttUserManage.service.shiro.SysPermissionService;
import com.cdzic.mqttUserManage.service.shiro.SysRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/bg")
public class ViewController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 登陆页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/login");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 主页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        modelAndView.setViewName("backstage/index");
        modelAndView.addObject("role", user.getRole().getRoleName());
        SysRole sysRole = user.getRole();
        List<SysPermission> permissionsT = sysRole.getPermissions();
        List<String> permissions=new ArrayList<>();
        if (null!=sysRole&&null!=permissionsT){
            for (SysPermission sysPermission : permissionsT) {
                permissions.add(sysPermission.getPermission());
            }
        }
        modelAndView.addObject("account", user.getAccount());
        modelAndView.addObject("path", request.getContextPath());
        modelAndView.addObject("permissions",permissions);
        return modelAndView;
    }

    /**
     * 欢迎页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/welcome"})
    public ModelAndView welcome(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/welcome");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 修改密码页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/tpl-password"})
    public ModelAndView tplPassword(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/fragment/tpl-password");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 消息
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/tpl-message"})
    public ModelAndView tplMessage(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/fragment/tpl-message");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 主题
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/tpl-theme"})
    public ModelAndView tplTheme(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/fragment/tpl-theme");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 权限
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-permission"})
    public ModelAndView sysPermission(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-permission");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 权限表单
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-permission-form"})
    public ModelAndView sysPermissionAdd(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-permission-form");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 角色
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-role"})
    public ModelAndView sysRole(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-role");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 角色表单
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-role-form"})
    public ModelAndView sysRoleForm(ModelAndView modelAndView, HttpServletRequest request, String roleName) {
        modelAndView.setViewName("backstage/system/sys-role-form");
        modelAndView.addObject("path", request.getContextPath());
        modelAndView.addObject("permissions", sysPermissionService.findAll());
        if (null != roleName) {
            modelAndView.addObject("viewData", sysRoleService.findByRoleName(roleName).getPermissions());
        }
        return modelAndView;
    }

    /**
     * 管理员
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-user"})
    public ModelAndView sysUser(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-user");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 管理员表单
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-user-form"})
    public ModelAndView sysUserForm(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-user-form");
        modelAndView.addObject("path", request.getContextPath());
        modelAndView.addObject("roles",sysRoleService.findAll());
//        modelAndView.addObject("seccondLevel",seccondLevelRepo.findAll());
        return modelAndView;
    }

    /**
     * mqttUser列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/mqtt-one")
    public ModelAndView mqttOne(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/mqtt-one");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 添加/修改 mqttUser
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/mqtt-one-from")
    public ModelAndView mqttOneFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/mqtt-one-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * mqttUserRh列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/mqtt-list")
    public ModelAndView mqttUserRh(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/mqtt-list");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * mqtt 批量注册页面
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/device-add")
    public ModelAndView mqttUserAdd(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/device-add");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 添加/修改 mqttUserRh
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/mqtt-list-from")
    public ModelAndView mqttUserRhFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/mqtt-list-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }



}
