package com.wujiaqi.aopdemo.convert;

import com.wujiaqi.aopdemo.aop.OrderRecordDo;
import com.wujiaqi.aopdemo.pojo.UpdateOrderEntity;

/**
 * @author wujiaqi
 */
public class UpdateOrderConvert implements ParamConvert<UpdateOrderEntity> {
    @Override
    public OrderRecordDo convert(UpdateOrderEntity updateOrderEntity) {
        OrderRecordDo od = new OrderRecordDo();
        od.setOrderId(updateOrderEntity.getOrderId());
        od.setDesc(updateOrderEntity.getDesc());
        return od;
    }
}
