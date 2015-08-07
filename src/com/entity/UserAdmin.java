package com.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 后台管理者用户信息
 *
 * @author hao
 */
@Entity
@Table(name = "tb_user_admin")
public class UserAdmin extends BaseEntity {
    private String userName;//用户名
    private String pwd;//密码
    private boolean superUser;//是否是超级用户

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean isSuper) {
        this.superUser = isSuper;
    }
}
