package com.burning.web;

import com.burning.domain.message.Message;
import com.burning.services.UserService;
import com.burning.validator.CheckEmailValidator;
import com.burning.validator.CheckLoginIDValidator;
import com.burning.validator.CheckNickNameValidator;
import com.burning.validator.CheckReConfirmPassword;
import com.burning.web.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final CheckLoginIDValidator checkLoginIDValidator;
    private final CheckNickNameValidator checkNickNameValidator;
    private final CheckEmailValidator checkEmailValidator;
    private final CheckReConfirmPassword checkReConfirmPassword;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkLoginIDValidator);
        binder.addValidators(checkNickNameValidator);
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkReConfirmPassword);
    }

    @GetMapping("/signup")
    public String singUp(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid UserRequestDto userRequestDto, Errors errors, Model model) throws NoSuchAlgorithmException {
        if (errors.hasErrors()) {
            model.addAttribute("userRequestDto", userRequestDto);
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "user/signup";
        }

        userService.signUp(userRequestDto);
        model.addAttribute("data", new Message("회원가입이 완료되었습니다.", "/"));
        return "user/message";
    }

    /* ======================ldy ========================= */
    @GetMapping("/signin")
    public String signin(@AuthenticationPrincipal UserRequestDto userRequestDto, HttpServletRequest request)throws Exception{
        System.out.println("들어왔니");
        return "user/signin";
    }
    



    @GetMapping("/signout")
    public String signout()throws Exception{
        return "";
    }



}