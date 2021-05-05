package com.example.api.twitter.application.adapter;

import com.example.api.twitter.domain.entity.HashtagPtnEntity;
import com.example.api.twitter.interfaces.model.HashtagsModel;
import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class HashtagsAdapter {

    public HashtagsModel mapTo(HashtagPtnEntity hashtagPtnEntity) {
        return HashtagsModel.builder()
                .hashtags(hashtagPtnEntity.getId())
                .count(hashtagPtnEntity.getCount())
                .build();
    }
}
