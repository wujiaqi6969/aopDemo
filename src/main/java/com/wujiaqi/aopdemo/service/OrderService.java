package com.wujiaqi.aopdemo.service;

import com.wujiaqi.aopdemo.aop.OrderOperateLog;
import com.wujiaqi.aopdemo.aop.timeout.EnableTimeout;
import com.wujiaqi.aopdemo.convert.SaveOrderConvert;
import com.wujiaqi.aopdemo.convert.UpdateOrderConvert;
import com.wujiaqi.aopdemo.pojo.SaveOrderEntity;
import com.wujiaqi.aopdemo.pojo.UpdateOrderEntity;
import com.wujiaqi.aopdemo.pojo.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wujiaqi
 */
@Component
public class OrderService {

    @OrderOperateLog(desc = "保存订单", convert = SaveOrderConvert.class)
    public Boolean saveOrder(SaveOrderEntity order) {
        System.out.println("save order : " + order.getId());
        return true;
    }

    @OrderOperateLog(desc = "修改订单", convert = UpdateOrderConvert.class)
    public Boolean updateOrder(UpdateOrderEntity order) {
        System.out.println("update order : " + order.getOrderId());
        return true;
    }


    @EnableTimeout(value = 3, apiName = "annotation", timeUnit = TimeUnit.SECONDS, fallbackMethodName = "fallbackMethod")
    public Object annotation(User param) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("正在执行业务逻辑annotation->" + param);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        return "success";
    }


    public Object fallbackMethod(User param) {
        Map<String, String> result = new HashMap<>(2);
        result.put("code", "-1");
        result.put("msg", "请求超时,请稍后重试~param->" + param.toString());
        return result;
    }


}
