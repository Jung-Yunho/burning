package com.burning.services;

import com.burning.domain.user.UserRepository;
import com.burning.web.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Transactional
    public Long signUp(UserRequestDto userResponseDto) {
        return userRepository.save(userResponseDto.toEntity()).getId();
    }

}