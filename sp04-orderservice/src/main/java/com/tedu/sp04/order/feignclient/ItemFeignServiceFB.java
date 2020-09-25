package com.tedu.sp04.order.feignclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;

@Component
public class ItemFeignServiceFB implements ItemFeignService {
	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		//模拟有缓存数据,向客户端发送缓存数据
		//50%概率发送模拟的缓存数据
		if (Math.random()<0.5) {
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(new Item(1, "缓存商品"+1, 1));
			list.add(new Item(2, "缓存商品"+2, 4));
			list.add(new Item(3, "缓存商品"+3, 2));
			list.add(new Item(4, "缓存商品"+4, 3));
			list.add(new Item(5, "缓存商品"+5, 1));
			return JsonResult.ok(list);
		}
		
		return JsonResult.err("无法获取订单的商品列表");
	}

	@Override
	public JsonResult decreaseNumber(List<Item> items) {
		return JsonResult.err("无法减少商品库存");
	}
}






