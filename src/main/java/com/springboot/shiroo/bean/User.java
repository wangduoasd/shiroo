package com.springboot.shiroo.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String password;
  private String perms;



}
