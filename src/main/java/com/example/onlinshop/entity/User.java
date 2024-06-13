package com.example.onlinshop.entity;

import static com.example.onlinshop.Constant.EMAIL_REGEXP;

import com.example.onlinshop.dto.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

  private ReentrantLock LOCK = new ReentrantLock();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @JsonView({View.UserSummary.class, View.UserDetails.class})
  private String name;
  @Email(regexp = EMAIL_REGEXP)
  @Schema(example = "user@user.ru")
  @JsonView({View.UserSummary.class, View.UserDetails.class})
  private String email;
  @JsonView({View.UserDetails.class})
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Order> orderList;

}
