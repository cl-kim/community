package com.chaelin.community.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String email;
    private String password;
    private String userName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String postcode;
    private String address;
    private String detailAddress;

}
