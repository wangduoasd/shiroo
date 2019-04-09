package com.springboot.shiroo.service;

import com.springboot.shiroo.bean.VUser;
import com.springboot.shiroo.dao.VUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangduo
 * @date 2019/4/8 - 15:34
 */
@Service
public class ShiroService {
    @Autowired
    private VUserDao vuserDao;
    public Map<String, String> loadFilterChainDefinitions() {
        Map<String, String> filterMap = new LinkedHashMap<String, String> ();
        filterMap.put ("/*", "anon");
        List<VUser> vUserList=vuserDao.findAll ();
        for(VUser vUser:vUserList){
            filterMap.put (vUser.getPermissionName (),"role["+vUser.getRoleName ()+"]");
        }
        filterMap.put ("/hello", "anon");
        filterMap.put ("/login", "anon");
        filterMap.put ("/test", "anon");
        return filterMap;
    }
}
