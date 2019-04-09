package com.springboot.shiroo.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="v_user")
public class VUser implements Serializable {
  @Id
  private Integer rolePermissionId;
  private Integer roleId;
  private String roleName;
  private String rnote;
  private Integer permissionId;
  private String permissionName;
  private String pnote;
}
