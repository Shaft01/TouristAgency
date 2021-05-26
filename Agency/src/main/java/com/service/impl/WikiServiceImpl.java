package com.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.service.WikiService;

@Service
public class WikiServiceImpl implements WikiService {
	WebClient client=WebClient.create("http://localhost:8082");
	@Override
	public String getInfo(String title) {
		return client.get().uri(uriBuilder -> uriBuilder.path("/api/wiki")
				.queryParam("title", title)
				.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		
	}

}
