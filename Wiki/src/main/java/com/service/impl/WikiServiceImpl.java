package com.service.impl;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import com.service.WikiService;

@Service

public class WikiServiceImpl implements WikiService{

	@Override
	public String getInfo(String title) {
		WebClient client=WebClient.create("https://en.wikipedia.org/w/api.php");
		String response= client.get().uri(uriBuilder -> uriBuilder
				.queryParam("action", "query")
				.queryParam("titles", title)
				.queryParam("prop", "extracts")
				.queryParam("format","json")
				.queryParam("exintro", "1")
				.build()).retrieve().bodyToMono(String.class).block();
		String resenje="";
			try {
				JSONObject json=new JSONObject(response);
				JSONObject json2= json.getJSONObject("query").getJSONObject("pages");
				int i=json2.toString().indexOf(":");
				String s=json2.toString().substring(i+1);
				json=new JSONObject(s);
				
				 resenje= json.get("extract").toString().replaceAll("<[^>]*>", "").trim();

			
			} catch (JSONException e) {
				
				e.printStackTrace();
			}

		return resenje;
	}

}
