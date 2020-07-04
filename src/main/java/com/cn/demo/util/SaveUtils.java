package com.cn.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SaveUtils {

    /**
     * 持久化防值
     * @param req
     * @param info
     */
    public static void setMes(HttpServletRequest req, Map info){
        HttpSession session = req.getSession();
        session.setAttribute("info",info);
    }

    /**
     * 持久化取值
     * @param req
     * @return
     */
    public static Map getMes(HttpServletRequest req){
        Map map = new HashMap();
        HttpSession session = req.getSession();
        map = (Map) session.getAttribute("info");
        if(map == null||map.keySet().isEmpty()){
            return null;
        }
        return map;
    }

}
