package com.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 快递名称
 *
 * @author hao
 */
@Entity
@Table(name = "tb_post_name")
public class PostName extends BaseEntity {
    private String name;//快递的名称
    private String simpleName;//简称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }
}
