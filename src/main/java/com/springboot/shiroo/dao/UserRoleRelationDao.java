package com.springboot.shiroo.dao;

import com.springboot.shiroo.bean.UserRoleRelation;
import com.springboot.shiroo.bean.VUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangduo
 * @date 2019/4/8 - 14:13
 */
public interface UserRoleRelationDao extends JpaRepository<UserRoleRelation,Integer> {

    List<UserRoleRelation> findAllByUserId(Integer userId);
}
