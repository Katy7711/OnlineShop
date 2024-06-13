package com.example.onlinshop.controller;

import com.example.onlinshop.dto.OrderDto;
import com.example.onlinshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {
  private static final Logger log = LoggerFactory.getLogger(OrderController.class);
  private final OrderService orderService;

  @PostMapping("/order/add")
  public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
    log.info("Request for add order");
    return ResponseEntity.ok(orderService.addOrder(orderDto));
  }

}
