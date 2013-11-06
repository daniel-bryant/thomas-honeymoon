package com.honeymoon.core.services;

import com.honeymoon.core.domain.Order;
import com.honeymoon.events.orders.*;
import com.honeymoon.persistence.services.OrderPersistenceService;

public class OrderEventHandler implements OrderService {

  private OrderPersistenceService ordersPersistenceService;

  public OrderEventHandler(OrderPersistenceService ordersPersistenceService) {
    this.ordersPersistenceService = ordersPersistenceService;
  }

  @Override
  public OrderCreatedEvent createOrder(CreateOrderEvent createOrderEvent) {

    //TODO, add validation of menu items
    //TODO, add order total calculation
    //TODO, add order time estimate calculation
	//TODO  Think transaction boundary. Order and OrderStatus should be atomic
    //OrderCreatedEvent event = ordersPersistenceService.createOrder(createOrderEvent);

    //TODO, where should this go?
    /*OrderStatusEvent orderStatusEvent = ordersPersistenceService.setOrderStatus(
            new SetOrderStatusEvent(event.getNewOrderKey(), new OrderStatusDetails(event.getNewOrderKey(),
            UUID.randomUUID().toString(), new Date(), "Order Created")));*/

    //return event;
	return ordersPersistenceService.createOrder(createOrderEvent);
  }

  @Override
  public AllOrdersEvent requestAllOrders(RequestAllOrdersEvent requestAllCurrentOrdersEvent) {
    return ordersPersistenceService.requestAllOrders(requestAllCurrentOrdersEvent);
  }

  @Override
  public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent) {
    return ordersPersistenceService.requestOrderDetails(requestOrderDetailsEvent);
  }

  @Override
  public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
    return ordersPersistenceService.setOrderPayment(setOrderPaymentEvent);
  }

  @Override
  public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent) {

    OrderDetailsEvent orderDetailsEvent = ordersPersistenceService.requestOrderDetails(new RequestOrderDetailsEvent(deleteOrderEvent.getKey()));

    if (!orderDetailsEvent.isEntityFound()) {
      return OrderDeletedEvent.notFound(deleteOrderEvent.getKey());
    }

    Order order = Order.fromOrderDetails(orderDetailsEvent.getOrderDetails());

    if (!order.canBeDeleted()) {
      return OrderDeletedEvent.deletionForbidden(deleteOrderEvent.getKey(), order.toOrderDetails());
    }

    ordersPersistenceService.deleteOrder(deleteOrderEvent);

    return new OrderDeletedEvent(deleteOrderEvent.getKey(), order.toOrderDetails());
  }

  @Override
  public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderDetailsEvent) {
    return ordersPersistenceService.requestOrderStatus(requestOrderDetailsEvent);
  }
}
