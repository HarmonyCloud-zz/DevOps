package com.harmony.devops.repository.user;

import com.harmony.devops.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/13
 * @描述
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserName(String userName);

}
