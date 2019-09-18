package com.zyq.exception;

/**
 * 作者：张翼麒
 * 自定义异常类
 */
public class Error extends Exception{
   private String exception;

    public Error(String exception) {
        this.exception = exception;
    }

    public Error() {
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
