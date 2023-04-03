package com.wujiaqi.aopdemo.convert;

import com.wujiaqi.aopdemo.aop.OrderRecordDo;

/**
 * @author wujiaqi
 */
public interface ParamConvert<PARAM> {

    /**
     * 根据param 转换成通用OrderRecordDo
     *
     * @param param param
     * @return OrderRecordDo
     */
    OrderRecordDo convert(PARAM param);

}
