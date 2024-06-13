package com.example.onlinshop.mapper;

import org.mapstruct.Mapper;
import com.example.onlinshop.dto.UserDto;
import com.example.onlinshop.entity.User;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper  (
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper extends WebMapper<UserDto, User> {

    UserDto toUserDto(User entity);
    User userDtoToEntity (UserDto dto);

}