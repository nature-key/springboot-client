微服务


    RestTemplate restTemplate = new RestTemplate();
    String res = restTemplate.getForObject(BASE_URL + "/product/get/2", String.class);
    System.out.println(res);
    Response response=new Gson().fromJson(res, Response.class);
    System.out.println(response.getMessage());
    System.out.println(response.getData());
    System.out.println(response.getStatus());
    
    
注册

   服务端注册
   
     CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress,new RetryOneTime(1000));
     client.start();
     client.blockUntilConnected();

     ServiceInstance instance = ServiceInstance.builder().name("product").address("localhost").port(8082).build();
     ServiceDiscovery serviceDiscoveryBuilder = ServiceDiscoveryBuilder.builder(Object.class ).client(client).basePath("/product").build();

     serviceDiscoveryBuilder.registerService(instance);
     serviceDiscoveryBuilder.start();
     System.out.println("server success");  
     
   客户端获取
   
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