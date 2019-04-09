package com.springboot.shiroo.shiro;

import com.springboot.shiroo.bean.User;
import com.springboot.shiroo.bean.VUser;
import com.springboot.shiroo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangduo
 * @date 2018/8/14 - 16:32
 */

public class UserRealm extends AuthorizingRealm{
    /**
     * 执行授权逻辑
     * @param arg0
     * @return
     */
    @Autowired
   private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo ();
        //添加资源的授权字符串
        //info.addStringPermission("user:add");
        //到数据库查询当前登录用户的授权字符串
        //获取当前用户
        Subject subject=SecurityUtils.getSubject ();
        User user=(User) subject.getPrincipal ();
        List<VUser> vUserList=userService.getVuser (user.getId ());
        Set<String> roles = new HashSet<String> ();
        Set<String> permissions = new HashSet<String>();
        for(VUser vUser:vUserList){
            roles.add (vUser.getRoleName ()) ;
            permissions.add (vUser.getPermissionName ()) ;
        }
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }
    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken  authenticationToken) throws AuthenticationException {

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        User user = userService.getemp (token.getUsername ());

        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出AuthenticationException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo (user,user.getPassword (),"");
    }

}
