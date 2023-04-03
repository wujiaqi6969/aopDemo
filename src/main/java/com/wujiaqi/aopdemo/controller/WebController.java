package com.wujiaqi.aopdemo.controller;

import com.wujiaqi.aopdemo.pojo.User;
import com.wujiaqi.aopdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wujiaqi
 */
@RestController
@RequestMapping("test")
public class WebController {

    @Autowired
    private OrderService orderService;

    @PostMapping("timeout")
    public Object timeout(@RequestBody User param) {
        Object annotation = orderService.annotation(param);
        System.out.println("annotation = " + annotation);
        return annotation;
    }

}
