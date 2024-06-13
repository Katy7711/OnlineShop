package com.example.onlinshop.dto;

import static com.example.onlinshop.Constant.EMAIL_REGEXP;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
  @NotBlank
  @Size(min = 2)
  private String name;
  @Email(regexp = EMAIL_REGEXP)
  @Schema(example = "user@user.ru")
  private String email;

}
