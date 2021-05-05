package com.example.api.twitter.application.adapter;

import com.example.api.twitter.domain.entity.LocationEntity;
import com.example.api.twitter.domain.entity.TweetEntity;
import com.example.api.twitter.domain.entity.UserEntity;
import com.example.api.twitter.domain.repository.LocationRepository;
import com.example.api.twitter.domain.repository.UserRepository;
import com.example.api.twitter.interfaces.model.TweetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class TwitterAdapter {

    private final UserAdapter userAdapter;
    private final UserRepository userRepository;

    private final LocationAdapter locationAdapter;
    private final LocationRepository locationRepository;

    @Autowired
    public TwitterAdapter(UserAdapter userAdapter,
                          UserRepository userRepository,
                          LocationAdapter locationAdapter,
                          LocationRepository locationRepository) {
        this.userAdapter = userAdapter;
        this.userRepository = userRepository;
        this.locationAdapter = locationAdapter;
        this.locationRepository = locationRepository;
    }

    public TweetModel mapTo(TweetEntity tweetEntity) {
        Optional<UserEntity> user = userRepository.findOne(tweetEntity.getUserId());
        Optional<LocationEntity> location = tweetEntity.getLocationId() != null
                ? locationRepository.findOne(tweetEntity.getLocationId())
                : Optional.empty();

        return TweetModel.builder()
                .id(String.valueOf(tweetEntity.getId()))
                .user(user.map(userAdapter::mapTo).orElse(null))
                .text(tweetEntity.getText())
                .location(location.isEmpty() ? null : locationAdapter.mapTo(location.get()))
                .validation(tweetEntity.isValidation())
                .build();
    }

    public TweetEntity mapTo(TweetModel tweetEntity) {
        return TweetEntity.builder()
                .text(tweetEntity.getText())
                //.location(tweetEntity.getLocalization())
                .validation(tweetEntity.isValidation())
                .build();
    }

}
