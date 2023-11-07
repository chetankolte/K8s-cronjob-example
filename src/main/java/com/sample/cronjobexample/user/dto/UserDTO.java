package com.sample.cronjobexample.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    private String name;
    private Integer age;
}
