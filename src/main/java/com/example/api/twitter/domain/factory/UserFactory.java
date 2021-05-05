package com.example.api.twitter.domain.factory;

import com.example.api.twitter.domain.entity.UserEntity;
import org.springframework.stereotype.Component;
import twitter4j.User;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class UserFactory {

    public static UserEntity mapTo(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
