package com.yjy.cbcollection_server.dto;

public class SearchStrategyNameReponse {
    private boolean exist;
    private String msg;

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
