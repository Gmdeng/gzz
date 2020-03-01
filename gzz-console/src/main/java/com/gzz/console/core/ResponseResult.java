package com.gzz.console.core;

/**
 * 响应结果
 */
public class ResponseResult {
    private int code;
    private String message;
    private Object data;
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public Object getData() {
        return data;
    }

    private ResponseResult(int code, String message){
        this.code = code;
        this.message = message;
    }

    public static ResponseResult ERROR(){
        return new ResponseResult(9999,"Error");
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(-1,"Fail");
    }
    public static ResponseResult SUCCESS(){
        return new ResponseResult(0,"Success");
    }
    public ResponseResult message(String message) {
        this.message = message;
        return this;
    }
    public ResponseResult data(Object data) {
        this.data = data;
        return this;
    }
}
