package com.example.onlinshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderDto {

  @NotBlank
  private String productName;
  @NotBlank
  private  int sum;
  @NotBlank
  private long userId;

}
