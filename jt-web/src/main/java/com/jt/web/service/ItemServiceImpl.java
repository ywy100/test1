package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.service.HttpClientService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private HttpClientService httpClient;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Item findItemById(Long itemId) {
		String url="http://manage.jt.com/web/item/findItemById";
		Map<String, String > params = new HashMap<>();
		params.put("itemId", itemId+"");
		
		//通过http请求获取远程服务器的数据
		String  resultJSON = httpClient.doGet(url,params);
		Item item = null;
		try {
			item = objectMapper.readValue(resultJSON, Item.class);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return item;
	}
}
