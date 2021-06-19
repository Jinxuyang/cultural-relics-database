package com.fehead.culturalrelicsdatabase.core.response;

import lombok.Data;

/**
 * @author: Zero
 * @Date: 2021/4/2 - 19:57
 * @since: jdk 1.8
 */
@Data
public class CommonReturnType {
    private String status;

    private Object data;

    private String message;
    /**
     * 成功的返回结果
     * @param result
     * @return
     */
    public static CommonReturnType success(Object result,String message) {
        return CommonReturnType.create(result,"success",message);
    }

    /**
     * 没有描述的成功返回结果
     * @param result
     * @return
     */
    public static CommonReturnType success(Object result) {
        return CommonReturnType.create(result,"success","");
    }
    /**
     * 失败的返回结果
     * @param result
     * @return
     */
    public static CommonReturnType fail(Object result,String message) {
        return CommonReturnType.create(result,"fail", message);
    }

    /**
     * 没有描述信息的失败返回结果
     * @param result
     * @return
     */
    public static CommonReturnType fail(Object result) {
        return CommonReturnType.create(result,"fail", "");
    }

    /**
     * 兼容之前的代码
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result,String message) {
        return CommonReturnType.create(result,"success", message);
    }

    /**
     * 没有描述信息返回
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result,"success", "");
    }

    /**
     * 返回数据
     * @param result
     * @param status
     * @return
     */
    public static CommonReturnType create(Object result, String status,String message) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        type.setMessage(message);
        return type;
    }

}
