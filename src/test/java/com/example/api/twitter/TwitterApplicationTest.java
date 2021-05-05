package com.example.api.twitter;

import com.example.api.twitter.application.service.TweetService;
import com.example.api.twitter.domain.entity.TweetEntity;
import com.example.api.twitter.interfaces.model.TweetModel;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TwitterApplication.class,
        webEnvironment = DEFINED_PORT
)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TwitterApplicationTest extends AbstractTest {

    private final static String uri_tweets = "http://127.0.0.1:8085/twitter/v1/tweets";
    private final static String uri_tweets_tweetId = "http://127.0.0.1:8085/twitter/v1/tweets/";
    private final static String uri_valid_tweets_usertId = "http://127.0.0.1:8085/twitter/v1/tweets/valid/";
    private final static String uri_hashtags = "http://127.0.0.1:8085/twitter/v1/hashtags";

    @Test
    void contextLoads() {
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Autowired
    private TweetService tweetService;

    @Test
    @DisplayName("Test get Tweets")
    public void test01_getTweets() {
        List<TweetEntity> tweets = getTweets();
        assertNotNull(tweets);
    }

    @Test
    @DisplayName("Test get Tweets")
    public void test02_getTweets() throws Exception {
        // When: is stored tweets in redis
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri_tweets))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
    }

    @Test
    @DisplayName("Test get Tweet by tweet identifier")
    public void test03_getTweetsById() throws Exception {
        // Given: tweet identifier
        String tweetId = String.valueOf(getTweets().get(0).getId());
        System.out.println("tweetId: " + tweetId);

        // When: is stored tweets in redis
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri_tweets_tweetId + tweetId))
                //.accept(MediaType.APPLICATION_JSON_VALUE))
                //.header("Authorization", token))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // Then: return tweet by ids
        String content = mvcResult.getResponse().getContentAsString();
        //TweetModel tweetById = super.mapFromJson(content, TweetModel.class);
        assertNotNull(content);
        System.out.println("Content: " + content);
    }

    @Test
    @DisplayName("Test get Tweet by tweet identifier")
    public void test04_getTweetsById() throws Exception {
        // Given: tweet identifier
        long tweetId = (getTweets().get(0).getId());
        System.out.println("tweetId: " + tweetId);

        // When: is stored tweets in redis
        TweetModel tweet = tweetService.getTweet(tweetId);
        assertNotNull(tweet);

        // Then: return tweet by this identifier
        assertEquals(String.valueOf(tweetId), tweet.getId());
    }

    @Test
    @DisplayName("Test validate tweet identifier")
    public void test05_validateTweetsById() throws Exception {
        // Given: tweet identifier
        long tweetId = (getTweets().get(0).getId());
        System.out.println("tweetId: " + tweetId);

        // Then: The tweet is not validated
        TweetModel tweet = tweetService.getTweet(tweetId);
        tweet.setValidation(false);
        assertFalse(tweet.isValidation());

        // Given: this tweet is validate
        tweetService.validateTweetById(tweetId);

        // Then: The tweet is validated
        TweetModel tweetGet = tweetService.getTweet(tweetId);
        assertTrue(tweetGet.isValidation());
    }

    @Test
    @DisplayName("Test valid tweets by user identifier")
    public void test06_getAllValidateTweetsByUserId() throws Exception {
        // Given: the tweet has been validate
        long tweetId = (getTweets().get(0).getId());
        tweetService.validateTweetById(tweetId);
        TweetModel validTweet = tweetService.getTweet(tweetId);

        // Then: the user has valid tweets
        long userId = validTweet.getUser().getId();
        System.out.println("userId: " + userId);

        List<TweetModel> tweet = tweetService.getValidTweets(userId);
        assertTrue(tweet.size() > 0);
    }

    @Test
    @DisplayName("Test get hashtags by n classificarion")
    public void test07_getTweetsById() throws Exception {
        String nClassification = "10";
        // When: is get hashtags
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri_hashtags)
                .param("hashtags", nClassification))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // Then: return n hashtag most used
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
        // assertEquals(nClassification, collectionModel.getHashtagsModels().size());
        System.out.println("Content: " + content);
    }
}
