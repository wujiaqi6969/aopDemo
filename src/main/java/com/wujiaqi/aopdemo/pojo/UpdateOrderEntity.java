package com.wujiaqi.aopdemo.pojo;

import java.io.Serializable;

/**
 * @author wujiaqi
 */
public class UpdateOrderEntity implements Serializable {


    private Long orderId;

    private String desc;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
