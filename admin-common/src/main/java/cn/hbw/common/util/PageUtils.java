package cn.hbw.common.util;

import cn.hutool.core.util.PageUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName：cn.hbw.common.util.PageUtils
 * Description：分页工具类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/11/10 14:32
 **/
public class PageUtils extends PageUtil {
    /**
     * 构造集合分页
     * @param page
     * @param size
     * @param objects
     * @return
     */
    public static List toPage(int page,int size,List objects){
        int fromIndex = (page - 1) * size;
        int toIndex = page * size;
        int listSize = objects.size();
        if (fromIndex>listSize){
            return new ArrayList();
        }
        else if (toIndex >= listSize){
            return objects.subList(fromIndex,listSize);
        }else {
            return objects.subList(fromIndex,toIndex);
        }
    }

    /**
     * 自定义分页
     */
    public static Map<String,Object> toPage(Object object, Object totalElements) {
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",object);
        map.put("totalElements",totalElements);
        return map;
    }
}
