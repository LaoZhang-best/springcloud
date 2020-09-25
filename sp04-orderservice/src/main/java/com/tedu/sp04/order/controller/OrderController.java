package com.tedu.sp04.order.controller;

import java.util.Arrays;
import java.util.List;

import com.tedu.sp04.order.feignclient.ItemFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.OrderService;
import com.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private ItemFeignService itemFeignService;

	@GetMapping("/getItem/{orderId}")
	public JsonResult<List<Item>> getItemByOrder(@PathVariable String orderId){
		return itemFeignService.getItems(orderId);
	}


	@GetMapping("/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId) {
		log.info("get order, id="+orderId);
		
		Order order = orderService.getOrder(orderId);
		return JsonResult.ok(order);
	}
	
	@GetMapping("/")
	public JsonResult addOrder() {
		//模拟post提交的数据
		Order order = new Order();
		order.setId("123abc");
		order.setUser(new User(7,null,null));
		order.setItems(Arrays.asList(new Item[] {
				new Item(1,"aaa",2),
				new Item(2,"bbb",1),
				new Item(3,"ccc",3),
				new Item(4,"ddd",1),
				new Item(5,"eee",5),
		}));
		orderService.addOrder(order);
		return JsonResult.ok();
	}
}
