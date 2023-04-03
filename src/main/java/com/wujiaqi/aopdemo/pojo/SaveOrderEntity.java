package com.wujiaqi.aopdemo.pojo;

import java.io.Serializable;

/**
 * @author wujiaqi
 */
public class SaveOrderEntity implements Serializable {

    private Long id;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
