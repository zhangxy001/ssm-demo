import com.alibaba.fastjson.JSON;
import com.cn.demo.model.Result;

public class InvokeUtil {

    private static Result result = null;

    public static void main(String args[]){
        /**
         * 实例化的方式
         */
        result = new Result();
        System.out.println(result);
        try {
           result = Result.class.newInstance();
            System.out.println(result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        result = JSON.parseObject("{}",Result.class);


    }
}
