package cn.hbw.common.util;

import io.netty.util.internal.ThrowableUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ClassName：cn.hbw.common.util.ThrowableUtils
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/11/9 19:58
 **/
public class ThrowableUtils {
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try(PrintWriter pw = new PrintWriter(sw))
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
