package com.example.demo.service;

import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SampleService {

	RestTemplate restTemplate;
	
	String path = "http://localhost:8081/greeting";
	
	@Cacheable(value="result")
	public String readData()
	{
		
      final String uri = "http://localhost:8081/greeting";

      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(uri, String.class);

      System.out.println(result);
      
      return result;
           
	
	}
	
	public <T> ResponseEntity<T> getFromService(String path,
			MultiValueMap<String, String> urlQueryParameters,
			Map<String, String> uriQueryParameters,
			ParameterizedTypeReference<T> typeRef, MediaType mediaType) {
		ResponseEntity<T> clientResponse;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", mediaType.toString());
		//headers.set(LoggingTransaction.TRANSACTION_ID,
			//	loggingTransaction.getId());
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path);
		UriComponents uri = null;
		if (null != urlQueryParameters) {
			builder.queryParams(urlQueryParameters);
		}

		if (null != uriQueryParameters) {
			uri = builder.buildAndExpand(uriQueryParameters);
		} else {
			uri = builder.build();
		}

		
		String url = uri.toUriString();

		
		
		// headers.set(LoggingTransaction.TRANSACTION_ID,
		// loggingTransaction.getId());
		HttpEntity<Object> http = new HttpEntity<>(headers);
		clientResponse = restTemplate.exchange(url, HttpMethod.GET, http,typeRef);

		return clientResponse;

	}
	
	
	
}