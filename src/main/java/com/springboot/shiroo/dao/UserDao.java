package com.springboot.shiroo.dao;

import com.springboot.shiroo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangduo
 * @date 2019/4/8 - 11:11
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByName(String name);
}
