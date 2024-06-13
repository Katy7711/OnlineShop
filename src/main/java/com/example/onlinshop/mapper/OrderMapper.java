package com.example.onlinshop.mapper;

import com.example.onlinshop.dto.OrderDto;
import com.example.onlinshop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper extends WebMapper<OrderDto, Order> {

  OrderDto toOrderDto(Order entity);
  Order orderDtoToEntity (OrderDto dto);
}
