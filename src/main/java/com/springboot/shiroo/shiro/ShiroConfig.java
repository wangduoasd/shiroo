package com.springboot.shiroo.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.springboot.shiroo.bean.VUser;
import com.springboot.shiroo.dao.VUserDao;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangduo
 * @date 2018/8/14 - 16:30
 */
@Configuration
//@AutoConfigureAfter(ShiroLifecycleBeanPostProcessorConfig.class)
public class ShiroConfig {
    @Autowired
    private VUserDao vuserDao;
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean ();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager (securityManager);
        //添加shiro内置过滤器
        /**
         * shiro内置过滤器，可以实现权限相关的拦截器
         *     常用的过滤器：
         *         anon：无需认证（登陆）可以访问
         *         authc：必须认证才可以访问
         *         user：如果使用rememberMe的功能可以直接访问
         *         perms：该资源必须得到资源的权限才可以访问
         *         roles：该资源必需得到权限的角色权限才可以访问
         */
      //  Map<String, String> filterMap = new LinkedHashMap<String, String> ();
        // filterMap.put("/add","authc");
        //filterMap.put("/user/*","authc");
        //顺序很重要
//        filterMap.put ("/*", "anon");
//        List<VUser> vUserList=vuserDao.findAll ();
//        for(VUser vUser:vUserList){
//            filterMap.put (vUser.getPermissionName (),"role["+vUser.getRoleName ()+"]");
//        }
//        filterMap.put ("/hello", "anon");
//        filterMap.put ("/login", "anon");
//        filterMap.put ("/test", "anon");
//        filterMap.put ("/add", "perms[user:add]");
//        filterMap.put ("/update", "perms[user:update]");
        //filterMap.put ("/*", "authc");

        Map<String, String> filterMap = new LinkedHashMap<String, String> ();
        List<VUser> vUserList=vuserDao.findAll ();
        System.out.println (vUserList.size ());
        for(VUser vUser:vUserList){
            filterMap.put (vUser.getPermissionName (),"roles["+vUser.getRoleName()+"]");
        }
        filterMap.put ("/login", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改拦截后的跳转页面
        shiroFilterFactoryBean.setLoginUrl ("/tologin");
        shiroFilterFactoryBean.setUnauthorizedUrl ("/auth");
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager ();
        /**
         * 关联realm
         */
        securityManager.setRealm (userRealm);
        return securityManager;
    }


    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm ();
    }

    /**
     * 配置shiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean(name="shiroDialect")
    public ShiroDialect getShiroDialet() {
        return new ShiroDialect ();
    }
}



