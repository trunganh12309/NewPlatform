package com.example.service.impl;

import com.example.converter.UserConverter;
import com.example.dto.MyUserDetail;
import com.example.dto.UserDTO;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserName(s);
        UserDTO userDTO = userConverter.convertToDto(userEntity);
        if(userDTO==null){
            throw new UsernameNotFoundException("Username not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity role: userEntity.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        MyUserDetail myUserDetail = new MyUserDetail(s,userEntity.getPassword(),true,true,true,true,authorities);
        BeanUtils.copyProperties(userDTO, myUserDetail);
        return myUserDetail;
    }
}
