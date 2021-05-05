package com.example.api.twitter.domain.factory;

import com.example.api.twitter.domain.entity.TweetEntity;
import org.springframework.stereotype.Component;
import twitter4j.Status;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class TweetFactory {

    public static TweetEntity mapTo(Status status, long locationId) {
        return TweetEntity.builder()
                .id(status.getId())
                .userId(status.getUser().getId())
                .text(status.getText())
                .locationId(status.getGeoLocation() != null ? locationId : null)
                .validation(false) // initialize in false
                .build();
    }
}
