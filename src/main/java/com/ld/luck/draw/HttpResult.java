package com.ld.luck.draw;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HttpResult<T>  {

    private T result;
    private int code;
    private String message;
    private Boolean isSuccess = true;

    public HttpResult() {}

    public HttpResult(T t) {
        this.code = 0;
        this.isSuccess = true;
        this.result = t;
    }

    public HttpResult(Throwable e) {
        this.code = 250;
        this.isSuccess = false;
        this.message = e.getMessage();
    }

    public HttpResult<T> error (int code, String message) {
        this.code = code;
        this.isSuccess = false;
        this.message = message;
        return this;
    }

}
