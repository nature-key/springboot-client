package com.springboot.client;

import com.google.gson.Gson;
import com.springboot.client.loadList.LoadBalance;
import com.springboot.client.response.Response;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class ZKclient {

    public static void main(String[] args) throws Exception {

        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.0.103:2181", new RetryOneTime(1000));
        client.start();
        client.blockUntilConnected();
        ServiceDiscovery serviceDiscoveryBuilder = ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/product").build();
        Collection<ServiceInstance> list = serviceDiscoveryBuilder.queryForInstances("product");

        List<String> sevice = new ArrayList<>();


        list.forEach((it) -> {
            System.out.println(it.getAddress());
            System.out.println(it.getPort());
            sevice.add("http://"+it.getAddress()+":"+it.getPort());
        });
        LoadBalance load = new LoadBalance(sevice);
        RestTemplate restTemplate = new RestTemplate();
//        String res = restTemplate.getForObject("http://" + it.getAddress() + ":" + it.getPort() + "/product/get/2", String.class);
        String res = restTemplate.getForObject(load.choose()+ "/product/get/2", String.class);
        System.out.println(res);
        Response response = new Gson().fromJson(res, Response.class);
        System.out.println(response.getMessage());
        System.out.println(response.getData());
        System.out.println(response.getStatus());
////		SpringApplication.run(ClientApplication.class, args);


    }


}
