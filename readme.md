微服务


    RestTemplate restTemplate = new RestTemplate();
    String res = restTemplate.getForObject(BASE_URL + "/product/get/2", String.class);
    System.out.println(res);
    Response response=new Gson().fromJson(res, Response.class);
    System.out.println(response.getMessage());
    System.out.println(response.getData());
    System.out.println(response.getStatus());