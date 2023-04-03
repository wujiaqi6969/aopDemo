package com.wujiaqi.aopdemo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wujiaqi
 */
@Data
@Accessors(chain = true)
public class User {
    private String username;
    private Integer age;
}
