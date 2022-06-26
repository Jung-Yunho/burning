package com.burning.validator;

import com.burning.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckReConfirmPassword extends AbstractValidator<UserRequestDto> {
    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) {
        if (!errors.hasFieldErrors("pwd1") && !dto.getPwd1().equals(dto.getPwd2()))
            errors.rejectValue("pwd2", "비밀번호 재확인 오류", "비밀번호가 일치하지 않습니다.");
    }
}