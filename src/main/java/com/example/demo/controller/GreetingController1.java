package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController1 {


	@Autowired
	com.example.demo.service.SampleService service;
	
	final String url = "http://localhost:8081/greeting";
	
	RestTemplate restTemplate;
	
	final String uri = "http://localhost:8081/greeting";
	
    @RequestMapping("/sample")
    public String greeting() throws InterruptedException {
    	//Thread.sleep(3000);
    	
    	String str = "";
    	Runtime runtime = Runtime.getRuntime();
    	
    	System.out.println("Free memory at start: " + runtime.freeMemory() + " bytes.");
    	
    	for(int i =0;i<10000;i++) {
    		
    		str = str.concat(service.readData());
    		
    	}
    	
    	
    	System.out.println("Free memory at end: " + runtime.freeMemory() + " bytes.");
    	
    	
    	
        return str;
    }
    
  /*  @RequestMapping("/greeting1")
    public String greeting1() {
    	
        // MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		
		//queryParams.add("", "");
    	
    	ResponseEntity<String> slotConfigurationResponse  = service.getFromService(url, null, null, new ParameterizedTypeReference<String>() {
		}, MediaType.APPLICATION_JSON);
         
         return null;
   }*/
}