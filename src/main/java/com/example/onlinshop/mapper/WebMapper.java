package com.example.onlinshop.mapper;


import java.util.Collection;
import java.util.List;

public interface WebMapper<D, E> {

  List<D> toDto(Collection<E> entity);

  List<E> toEntity(Collection<D> dto);

}