package com.chaelin.community.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long memberId;
    private String email;
    private String password;
    private String userName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
