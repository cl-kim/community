package com.chaelin.community.service;

import com.chaelin.community.domain.Role;
import com.chaelin.community.domain.entity.User;
import com.chaelin.community.dto.UserDTO;
import com.chaelin.community.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Transactional
    public Long joinUser(UserDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(dtoToEntity(userDTO)).getUserId();
    }

    @Override
    public List<User> getList() {
        List<User> userList = userRepository.findAll();
        return userList;

    }

    @Override
    public UserDTO getUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            User entity = user.get();
            return entityToDto(entity);
        }
        return null;
    }

    @Override
    public boolean isDuplicate(String id) {
        if (userRepository.findByEmail(id) == null)
            return false;
        return true;
    }

    @Override
    public void modifyUser(UserDTO userDTO) {
        //업데이트 하는 항목은 이름이나 비밀번호
        Optional<User> result = userRepository.findById(userDTO.getUserId());

        if (result.isPresent()) {

            User entity = result.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            entity.changeUserName(userDTO.getUserName());
            entity.changePassword(userDTO.getPassword());

            userRepository.save(entity);

        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userEntityWrapper = userRepository.findByEmail(email);
        User user = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
