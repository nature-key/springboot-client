package com.springboot.client;

import com.google.gson.Gson;
import com.springboot.client.response.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {
    static String BASE_URL = "http://localhost:8080";

    public static void main(String[] args) {
//		SpringApplication.run(ClientApplication.class, args);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(BASE_URL + "/product/get/2", String.class);
        System.out.println(res);
        Response response=new Gson().fromJson(res, Response.class);
        System.out.println(response.getMessage());
        System.out.println(response.getData());
        System.out.println(response.getStatus());

    }
}
