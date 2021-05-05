package com.example.api.twitter.domain.factory;

import com.example.api.twitter.domain.entity.HashtagPtnEntity;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class HashtagFactory {

    public static HashtagPtnEntity mapTo(HashtagEntity hashtagEntity) {
        return HashtagPtnEntity.builder()
                .id(hashtagEntity.getText())
                .count(1)
                .build();
    }
}
