package com.burning.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginID(String loginID);

    boolean existsByNickName(String nickName);

    boolean existsByEmail(String email);

    User findByLoginID(String loginID);
}