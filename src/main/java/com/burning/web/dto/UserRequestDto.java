package com.burning.web.dto;

import com.burning.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "로그인 아이디는 필수 입력 값입니다.")
    @Size(min = 8, max = 16, message = "로그인아이디는 8자 이상 16자 이하로 입력해주세요.")
    private String loginID;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    private String pwd1;
    private String pwd2;
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, max = 8, message = "이름은 2자 이상 8자 이하로 입력해주세요.")
    private String name;
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickName;
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    @Size(max = 16, message = "휴대폰 번호는 16자 이하로 입력해주세요.")
    private String phone;
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @Size(min = 4, max = 30, message = "이메일 4자 이상 30자 이하로 입력해주세요.")
    private String email;
    private String addr1;
    private String addr2;
    private String status;

    @Builder
    public UserRequestDto(User user) {
        this.loginID = user.getLoginID();
        this.pwd1 = user.getPwd1();
        this.pwd2 = user.getPwd2();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.addr1 = user.getAddr1();
        this.addr2 = user.getAddr2();
        this.status = user.getStatus();
    }

    public User toEntity() {
        return User.builder()
                .loginID(loginID)
                .pwd1(pwd1)
                .name(name)
                .nickName(nickName)
                .phone(phone)
                .email(email)
                .addr1(addr1)
                .addr2(addr2)
                .status(status)
                .build();
    }
}