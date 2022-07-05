package com.burning.domain.user;

import com.burning.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Entity
@DynamicInsert
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "login_id")
    @NotNull
    private String loginID;
    @Column(name = "password")
    @NotNull
    private String pwd1;
    private String pwd2;
    @Column(nullable = false)
    @NotNull
    private String name;
    @Column(name = "nickname")
    @NotNull
    private String nickName;
    @Column
    private String phone;
    @Column
    @NotNull
    private String email;
    @Column
    private String addr1;
    @Column
    private String addr2;
    @Column
    private String status;

    @Builder
    public User(Long id, String loginID, String pwd1, String name, String nickName, String phone, String email, String addr1, String addr2, String status) {
        this.id = id;
        this.loginID = loginID;
        this.pwd1 = pwd1;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.status = status;
    }
}