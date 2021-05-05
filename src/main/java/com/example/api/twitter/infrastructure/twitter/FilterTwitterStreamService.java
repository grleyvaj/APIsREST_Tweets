package com.example.api.twitter.infrastructure.twitter;

import com.example.api.twitter.domain.entity.HashtagPtnEntity;
import com.example.api.twitter.domain.entity.TweetEntity;
import com.example.api.twitter.domain.factory.HashtagFactory;
import com.example.api.twitter.domain.factory.LocationFactory;
import com.example.api.twitter.domain.factory.TweetFactory;
import com.example.api.twitter.domain.factory.UserFactory;
import com.example.api.twitter.domain.repository.HashtagRepository;
import com.example.api.twitter.domain.repository.LocationRepository;
import com.example.api.twitter.domain.repository.TweetRepository;
import com.example.api.twitter.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static com.example.api.twitter.domain.vo.IdGenerator.generateUniqueId;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
public class FilterTwitterStreamService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final LocationRepository locationRepository;

    private Twitter twitter;

    @Value(value = "query")
    private String query;

    public FilterTwitterStreamService(TweetRepository tweetRepository,
                                      UserRepository userRepository,
                                      HashtagRepository hashtagRepository,
                                      LocationRepository locationRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.hashtagRepository = hashtagRepository;
        this.locationRepository = locationRepository;
        twitter = TwitterFactory.getSingleton();
    }

    @PostConstruct
    public void init() {
        try {
            Query q = new Query(query);
            q.setCount(1000000);
            QueryResult search = twitter.search(q);
            List<Status> tweets = search.getTweets();
            addTweet(tweets);

            /*
            // Testing stored in redis
            tweets.forEach(status -> {
                //System.out.println("Status: " + status.toString());
                System.out.println("HashtagEntities: " + status.getHashtagEntities().toString());

                for (int i = 0; i < status.getHashtagEntities().length; i++) {
                    System.out.println("Text: " + status.getHashtagEntities()[i].getText());
                    System.out.println("Start: " + status.getHashtagEntities()[i].getStart());
                    System.out.println("End: " + status.getHashtagEntities()[i].getEnd());
                }

            });
            tweetRepository.findAll().forEach(tweetEntity -> {
                System.out.println("Tweet: " + tweetEntity.toString());
            });
            userRepository.findAll().forEach(userEntity -> {
                System.out.println("User: " + userEntity.toString());
            });
            locationRepository.findAll().forEach(locationEntity -> {
                System.out.println("Location: " + locationEntity.toString());
            });
            */
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public void addTweet(List<Status> tweets) {
        tweets.stream().forEach(tweet -> {
            long locationId = generateUniqueId();
            tweetRepository.save(TweetFactory.mapTo(tweet, locationId));
            userRepository.save(UserFactory.mapTo(tweet.getUser()));

            if (tweet.getGeoLocation() != null)
                locationRepository.save(LocationFactory.mapTo(tweet.getGeoLocation(), locationId));

            HashtagEntity[] hashtagEntities = tweet.getHashtagEntities();
            for (int i = 0; i < hashtagEntities.length; i++) {
                addHashtag(hashtagEntities[i]);
            }
        });
    }

    public void addHashtag(HashtagEntity hashtagEntity) {
        Optional<HashtagPtnEntity> one = hashtagRepository.findOne(hashtagEntity.getText());
        if (one.isPresent()) {
            HashtagPtnEntity hashtagUpdate = one.get();
            long count = hashtagUpdate.getCount();
            count++;
            hashtagUpdate.setCount(count);
            hashtagRepository.save(hashtagUpdate);
        } else {
            HashtagPtnEntity hashtagSaved = HashtagFactory.mapTo(hashtagEntity);
            hashtagRepository.save(hashtagSaved);
        }
    }

    public List<TweetEntity> getAll() {
        return tweetRepository.findAll();
    }
}
