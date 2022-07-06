package com.burning.services;

import com.burning.domain.user.Role;
import com.burning.domain.user.User;
import com.burning.domain.user.UserRepository;
import com.burning.web.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginID)throws UsernameNotFoundException{
        User findUser = userRepository.findByLoginID(loginID);
        List<GrantedAuthority> roles = new ArrayList<>();
        if(findUser.getLoginID().equals("administrator")){
            roles.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            roles.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new org.springframework.security.core.userdetails.User(findUser.getLoginID(),findUser.getPwd1(),roles);
    }




    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Transactional
    public Long signUp(UserRequestDto userResponseDto) throws NoSuchAlgorithmException {
        userResponseDto.setPwd1(encrypt(userResponseDto.getPwd1()));
        return userRepository.save(userResponseDto.toEntity()).getId();
    }

    public static String encrypt(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }
}