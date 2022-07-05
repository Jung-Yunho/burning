package com.burning.domain.user;

import com.burning.web.dto.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLoginID(String loginID);

    boolean existsByNickName(String nickName);

    boolean existsByEmail(String email);

    Optional<User> findByLoginID(String loginID);


}