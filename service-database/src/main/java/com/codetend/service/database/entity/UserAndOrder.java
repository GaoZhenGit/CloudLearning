package com.codetend.service.database.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserAndOrder {
    public User user;
    public Order order;
}
