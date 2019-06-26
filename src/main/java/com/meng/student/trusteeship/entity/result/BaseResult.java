package com.meng.student.trusteeship.entity.result;

/**
 * @author fengqigui
 * @description 统一结果返回类
 * @date 2018/03/13 11:20
 */
public class BaseResult {

    private static final BaseResult SUCCESS_RESULT = new BaseResult();

    /**
     * 调用是否成功
     */
    private boolean success;
    /**
     * 错误消息
     */
    private String message;

    private Object data;

    private BaseResult() {
        success = true;
    }

    private BaseResult(Object data) {
        this();
        this.data = data;
    }

    public BaseResult(Exception e) {
        success = false;
        message = e.getMessage();
    }

    public static BaseResult getResult() {
        return SUCCESS_RESULT;
    }

    public static BaseResult getResult(Object data) {
        return new BaseResult(data);
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}