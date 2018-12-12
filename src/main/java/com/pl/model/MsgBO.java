package com.pl.model;

/**
 * 消息返回类
 */
public class MsgBO {
    private Object data;

    private Boolean successed;

    private String msg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccessed() {
        return successed;
    }

    public void setSuccessed(Boolean successed) {
        this.successed = successed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MsgBO{" +
                "data=" + data +
                ", successed=" + successed +
                ", msg='" + msg + '\'' +
                '}';
    }
}
