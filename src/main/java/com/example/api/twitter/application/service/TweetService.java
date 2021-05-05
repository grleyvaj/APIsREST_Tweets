package com.example.api.twitter.application.service;

import com.example.api.twitter.application.adapter.HashtagsAdapter;
import com.example.api.twitter.application.adapter.TwitterAdapter;
import com.example.api.twitter.application.shared.ResourceNotFoundException;
import com.example.api.twitter.core.uri.URIConstant;
import com.example.api.twitter.domain.entity.HashtagPtnEntity;
import com.example.api.twitter.domain.entity.TweetEntity;
import com.example.api.twitter.domain.repository.HashtagRepository;
import com.example.api.twitter.domain.repository.TweetRepository;
import com.example.api.twitter.interfaces.model.HashtagsModel;
import com.example.api.twitter.interfaces.model.TweetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
public class TweetService {

    private static final String TWEET = URIConstant.ENTITY_TWEETS;

    private final TwitterAdapter twitterAdapter;
    private final TweetRepository tweetRepository;
    private final HashtagsAdapter hashtagsAdapter;
    private final HashtagRepository hashtagRepository;

    @Autowired
    public TweetService(TwitterAdapter twitterAdapter,
                        TweetRepository tweetRepository,
                        HashtagsAdapter hashtagsAdapter,
                        HashtagRepository hashtagRepository) {
        this.twitterAdapter = twitterAdapter;
        this.tweetRepository = tweetRepository;
        this.hashtagsAdapter = hashtagsAdapter;
        this.hashtagRepository = hashtagRepository;
    }

    public TweetModel getTweet(long tweetId) {
        TweetEntity tweetById = tweetRepository.findOne(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException(TWEET, "id", tweetId));

        return twitterAdapter.mapTo(tweetById);
    }

    public Page<TweetModel> getTweets(Pageable pageable) {
        return tweetRepository.findAll(pageable).map(twitterAdapter::mapTo);
    }

    public void validateTweetById(long tweetId) {
        TweetEntity tweetById = tweetRepository.findOne(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException(TWEET, "id", tweetId));

        tweetById.setValidation(true);
        tweetRepository.save(tweetById);
    }

    public List<TweetModel> getValidTweets(long userId) {
        return tweetRepository.findAll().stream()
                .filter(tweetEntity -> tweetEntity.getUserId() == userId && tweetEntity.isValidation())
                .map(twitterAdapter::mapTo).collect(Collectors.toList());
    }

    public List<HashtagsModel> getMostUsedHashtags(int classification) {
        List<HashtagPtnEntity> all = hashtagRepository.findAll(); // OrderBy count DESC
        all.sort(Comparator.comparing(HashtagPtnEntity::getCount)
                .reversed()
                .thenComparing(Comparator.comparing(HashtagPtnEntity::getCount)
                        .reversed()));

        classification = Math.min(all.size(), classification);
        List<HashtagPtnEntity> mostUsedHashtags = all.subList(0, classification); // Get only the most used by classification
        return mostUsedHashtags.stream().map(hashtagsAdapter::mapTo).collect(Collectors.toList());
    }
}
