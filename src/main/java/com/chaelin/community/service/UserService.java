package com.chaelin.community.service;

import com.chaelin.community.domain.entity.User;
import com.chaelin.community.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Long joinUser(UserDTO userDTO);
    List<User> getList();
    UserDTO getUser(String email);
    boolean isDuplicate(String id);
    void modifyUser(UserDTO userDTO);
    void deleteUser(Long userId);

    default User dtoToEntity(UserDTO dto) {
        User entity = User.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .postcode(dto.getPostcode())
                .address(dto.getAddress())
                .detailAddress(dto.getDetailAddress())
                .build();
        return entity;
    }

    default UserDTO entityToDto(User entity){

        UserDTO dto  = UserDTO.builder()
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .postcode(entity.getPostcode())
                .address(entity.getAddress())
                .detailAddress(entity.getDetailAddress())
                .build();

        return dto;
    }
}
