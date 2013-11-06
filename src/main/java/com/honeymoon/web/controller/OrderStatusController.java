package com.honeymoon.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honeymoon.core.services.OrderService;
import com.honeymoon.events.orders.OrderDetailsEvent;
import com.honeymoon.events.orders.OrderStatusEvent;
import com.honeymoon.events.orders.RequestOrderDetailsEvent;
import com.honeymoon.events.orders.RequestOrderStatusEvent;
import com.honeymoon.web.domain.OrderStatus;

@Controller
@RequestMapping("/order/{orderId}")
public class OrderStatusController {

	private static final Logger LOG = LoggerFactory
			.getLogger(OrderStatusController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public String orderStatus(@ModelAttribute("orderStatus") OrderStatus orderStatus) {
		LOG.debug("Get order status for order id {} customer {}", orderStatus.getOrderId(), orderStatus.getName());
		return "/order";
	}

	@ModelAttribute("orderStatus")
	private OrderStatus getOrderStatus(@PathVariable("orderId") String orderId) {
		OrderDetailsEvent orderDetailsEvent = orderService.requestOrderDetails(new RequestOrderDetailsEvent(orderId));
		OrderStatusEvent orderStatusEvent = orderService.requestOrderStatus(new RequestOrderStatusEvent(orderId));
		OrderStatus status = new OrderStatus();
		status.setName(orderDetailsEvent.getOrderDetails().getName());
		status.setOrderId(orderId);
		status.setStatus(orderStatusEvent.getOrderStatus().getStatus());
		return status;
	}
}
