package com.shoppingApp.orderservice.service;

import com.shoppingApp.orderservice.dto.OrderLineItemsDto;
import com.shoppingApp.orderservice.dto.OrderRequest;
import com.shoppingApp.orderservice.model.Order;
import com.shoppingApp.orderservice.model.OrderLineItems;
import com.shoppingApp.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        orderRequest.getOrderLineItemsList().stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
                .toList();
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCOde());

        return orderLineItems;
    }
}