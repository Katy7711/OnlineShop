package com.example.onlinshop.service.impl;

import com.example.onlinshop.Status;
import com.example.onlinshop.dto.OrderDto;
import com.example.onlinshop.entity.Order;
import com.example.onlinshop.entity.User;
import com.example.onlinshop.mapper.OrderMapper;
import com.example.onlinshop.repository.OrderRepository;
import com.example.onlinshop.repository.UserRepository;
import com.example.onlinshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  private final OrderMapper orderMapper;


  @Override
  public OrderDto addOrder(OrderDto orderDto) {
    log.info("Was invoked method for create order");
    User user = userRepository.findById(orderDto.getUserId()).orElseThrow();
    Order order = orderMapper.orderDtoToEntity(orderDto);
    order.setProductName(orderDto.getProductName());
    order.setSum(orderDto.getSum());
    order.setStatus(Status.PREPARE);
    order.setUser(user);
    log.info("order of user with id {} created", user.getId());
    orderRepository.save(order);
    return orderDto;
  }
}
