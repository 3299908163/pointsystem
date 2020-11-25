package cn.bdqn.pointsystem.utlis;

import java.util.HashMap;
import java.util.Map;

/**
 * json返回值封装
 * @author yajun
 * @create 2019/12/30
 */
public class ReturnResult {
    /**
     * 成功返回
     * @return
     */
    public static Map<String,Object> returnSuccess(Object msg,Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("Msg",msg);
        map.put("Code",200);
        map.put("Data",data);
        return map;
    }

    /**
     * 失败
     * @param msg
     * @param data
     * @param code
     * @return
     */
    public static Map<String,Object> returnFail(Object msg,Object data,Object code){
        Map<String,Object> map = new HashMap<>();
        map.put("Msg",msg);
        map.put("Code",code);
        map.put("Data",data);
        return map;
    }
}
