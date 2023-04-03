package com.wujiaqi.aopdemo;

import com.wujiaqi.aopdemo.pojo.SaveOrderEntity;
import com.wujiaqi.aopdemo.pojo.UpdateOrderEntity;
import com.wujiaqi.aopdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wujiaqi
 * @version 1.0
 */
@SpringBootApplication
public class AopDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Autowired
    private OrderService orderService;


    @Override
    public void run(String... args) throws Exception {
        SaveOrderEntity saveOrder = new SaveOrderEntity();
        saveOrder.setId(1L);
        orderService.saveOrder(saveOrder);

        UpdateOrderEntity updateOrder = new UpdateOrderEntity();
        updateOrder.setOrderId(2L);
        updateOrder.setDesc("修改订单号");
        orderService.updateOrder(updateOrder);
        System.out.println(">>>>>>>>>>AopDemoApplication is running<<<<<<<<<<");

    }
}
