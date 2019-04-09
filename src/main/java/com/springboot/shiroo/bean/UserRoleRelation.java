package com.springboot.shiroo.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class UserRoleRelation implements Serializable {
  @Id
  @GeneratedValue
  private Integer userRoleId;
  private Integer userId;
  private Integer roleId;
}
