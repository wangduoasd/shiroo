package com.springboot.shiroo.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class RolePermissionRelation implements Serializable {
  @Id
  @GeneratedValue
  private Integer rolePermissionId;
  private Integer roleId;
  private Integer permissionId;
}
