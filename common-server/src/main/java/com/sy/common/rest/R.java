package com.sy.common.rest;

import lombok.Data;


@Data
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Object data;

    private R()
    {}
    public static R ok()
    {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R ok(Object data)
    {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setData(data);
        r.setMessage("成功");
        return r;
    }
    public static R error()
    {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public static R error(String msg)
    {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        r.setData(msg);
        return r;
    }
    public R success(Boolean success)
    {
        this.setSuccess(success);
        return this;
    }
    public R message(String message)
    {
        this.setMessage(message);
        return this;
    }
    public R code(Integer code)
    {
        this.setCode(code);
        return this;
    }


}
