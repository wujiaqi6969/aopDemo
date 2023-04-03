package com.wujiaqi.aopdemo.convert;

import com.wujiaqi.aopdemo.aop.OrderRecordDo;
import com.wujiaqi.aopdemo.pojo.SaveOrderEntity;

/**
 * @author wujiaqi
 */
public class SaveOrderConvert implements ParamConvert<SaveOrderEntity> {

    @Override
    public OrderRecordDo convert(SaveOrderEntity saveOrderEntity) {
        OrderRecordDo orderRecordDo = new OrderRecordDo();
        orderRecordDo.setOrderId(saveOrderEntity.getId());
        orderRecordDo.setDesc(saveOrderEntity.getDesc());
        return orderRecordDo;
    }
}
