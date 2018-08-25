package com.springboot.client.response;

import com.springboot.client.bean.Product;

public class Response {
    private Integer status;
    private String message;
    private Product data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public void setData(Product data) {
        this.data = data;
    }

    public Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(Integer status, String message, Product data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
