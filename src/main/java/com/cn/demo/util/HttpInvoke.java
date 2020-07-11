package com.cn.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.demo.model.OaUser;
import com.cn.demo.model.Role;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class HttpInvoke {

    private static Logger logger = Logger.getLogger("HttpInvoke");

    public static void main(String[] args){


        Map map = new HashMap<String,Object>();
        map.put("userName","ick_xy");
        map.put("userId",UUID.randomUUID().toString());
        Role role = new Role();
        OaUser oaUser = new OaUser();

        System.out.println(oaUser.toString());
        String json = "";
        json = JSON.toJSONString(map);
        Object result = getRep("8080","getUser",json);
        logger.info(JSON.toJSONString(result));

    }



    public static <T>T getRep(String ports,String methodName,String json){
        Object obj = null;
        try {
        String url = "http://localhost:"+ports+"/"+methodName;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        JSONObject jsonObject = JSON.parseObject(json);
        Set <String >set = jsonObject.keySet();
        for(String key : set){
            NameValuePair nameValuePair = new BasicNameValuePair(key, (String) jsonObject.get(key));
            list.add(nameValuePair);
        }
            StringEntity stringEntity = new StringEntity(json);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(stringEntity);
            CloseableHttpResponse response = client.execute(post);
            String jsonstr =  EntityUtils.toString(response.getEntity(),"utf-8");
            if(!jsonstr.isEmpty()){
                obj = JSON.parseObject(jsonstr);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)obj;
    }

}
