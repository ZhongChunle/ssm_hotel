package com.zcl.utils;

import java.util.UUID;

/**
 * 项目名称：ssm_hotel
 * 描述：加密的盐
 *
 * @author zhong
 * @date 2022-05-17 16:54
 */
public class UUIDUtils {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
