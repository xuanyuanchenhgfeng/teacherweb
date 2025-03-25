package com.main.web.bean;

import com.alibaba.fastjson.JSON;

public class Result {
    private String code;
    private String msg;
    private Object data;

    public Result() {
    }
    private Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public String success(String msg,String data) {

        return JSON.toJSONString(new Result("200", msg, data));
    }
    public String success(String msg) {

        return JSON.toJSONString(new Result("200", msg, null));
    }
    public String error(String msg) {

        return JSON.toJSONString(new Result("400", msg, null));
    }



    /**
     * 获取
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "Result{code = " + code + ", msg = " + msg + ", data = " + data + "}";
    }
}
