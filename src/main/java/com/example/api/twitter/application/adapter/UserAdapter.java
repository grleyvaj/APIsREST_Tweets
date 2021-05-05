package com.example.api.twitter.application.adapter;

import com.example.api.twitter.domain.entity.UserEntity;
import com.example.api.twitter.interfaces.model.UserModel;
import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class UserAdapter {

    public UserModel mapTo(UserEntity userEntity) {
        return UserModel.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }
}
