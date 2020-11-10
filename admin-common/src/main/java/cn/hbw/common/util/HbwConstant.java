package cn.hbw.common.util;

/**
 * @ClassName Constant
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
public class HbwConstant {

    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";

    public static final String ADMIN = "管理员";


    /**
     * 常用接口
     */
    public static class Url {
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
