package com.springboot.shiroo.service;

import com.springboot.shiroo.bean.User;
import com.springboot.shiroo.bean.UserRoleRelation;
import com.springboot.shiroo.bean.VUser;
import com.springboot.shiroo.dao.UserDao;
import com.springboot.shiroo.dao.UserRoleRelationDao;
import com.springboot.shiroo.dao.VUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangduo
 * @date 2018/8/15 - 15:34
 */
@Service
public class UserService {
    @Autowired
   // public UserMapper userMapper;
    public UserDao userDao;
    @Autowired
    public UserRoleRelationDao userRoleRelationDao;
    @Autowired
    public VUserDao vUserDao;
    /**
     * 按照员工id查询员工
     * @param
     * @return
     */
    public User getemp(String name) {
        // TODO Auto-generated method stub
        //User user= userMapper.selectByPrimaryKey(name);
        User user= userDao.findByName (name);
        return user;
    }
    public List<VUser> getVuser(Integer userId) {
        List<UserRoleRelation> userRoleRelations=userRoleRelationDao.findAllByUserId (userId);
        List<VUser> vUserList=new ArrayList<> ();
        for (UserRoleRelation u:userRoleRelations){
            vUserList.addAll (vUserDao.findAllByRoleId (u.getRoleId ()));
        }
        return vUserList;
    }

}
