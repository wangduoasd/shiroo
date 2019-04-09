package com.springboot.shiroo.dao;

import com.springboot.shiroo.bean.VUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangduo
 * @date 2019/4/8 - 12:45
 */
@Repository
public interface VUserDao extends JpaRepository<VUser,Integer> {
   List<VUser> findAllByRoleId(Integer roleId);
}

