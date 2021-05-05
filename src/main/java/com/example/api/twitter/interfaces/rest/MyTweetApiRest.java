package com.example.api.twitter.interfaces.rest;

import com.example.api.twitter.application.service.TweetService;
import com.example.api.twitter.core.uri.URIConstant;
import com.example.api.twitter.interfaces.assembler.TweetModelAssembler;
import com.example.api.twitter.interfaces.model.HashtagsModel;
import com.example.api.twitter.interfaces.model.TweetModel;
import com.example.api.twitter.interfaces.shared.Documentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Gloria R. Leyva Jerez
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = MyTweetApiRest.ENTITY_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class MyTweetApiRest {

    protected static final String ENTITY_API = URIConstant.ENTITY_API + URIConstant.API_VERSION;
    protected static final String URI_TWEETS = URIConstant.URI_TWEETS;
    protected static final String URI_HASHTAGS = URIConstant.URI_HASHTAGS;

    private final TweetService tweetService;
    private final TweetModelAssembler tweetModelAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;

    @GetMapping(path = URI_TWEETS)
    @Operation(summary = Documentation.get_tweets_op_summary,
            description = Documentation.get_tweets_op_description,
            tags = "tweet-api", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = String.class)),
            responseCode = "200", description = Documentation.get_tweets_op_resp_description))
    public ResponseEntity<?> getTweets(@Parameter(description = Documentation.pageable_description, example = Documentation.pageable_request, required = false) Pageable pageable) {
        Page<TweetModel> tweets = tweetService.getTweets(pageable);
        PagedModel collModel = pagedResourcesAssembler.toModel(tweets, tweetModelAssembler);
        return ResponseEntity.ok(collModel);
    }

    @GetMapping(path = URI_TWEETS + "/{tweetId}")
    @Operation(summary = Documentation.get_tweet_by_id_op_summary,
            description = Documentation.get_tweet_by_id_op_description,
            tags = "tweet-api", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = TweetModel.class)),
            responseCode = "200", description = Documentation.get_tweet_by_id_op_resp_description))
    public HttpEntity<?> getTweetById(@PathVariable String tweetId) {
        TweetModel tweet = tweetService.getTweet(Long.parseLong(tweetId));
        RepresentationModel<TweetModel> tweetResource = tweetModelAssembler.toModel(tweet);
        return ResponseEntity.ok(tweetResource);
    }

    @GetMapping(path = URI_TWEETS + "/{tweetId}" + "/valid")
    @Operation(summary = Documentation.validate_tweet_by_id_op_summary,
            description = Documentation.validate_tweet_by_id_op_description,
            tags = "tweet-api", responses =
    @ApiResponse(responseCode = "200", description = Documentation.validate_tweet_by_id_op_resp_description))
    public void validateTweetById(@PathVariable String tweetId) {
        tweetService.validateTweetById(Long.parseLong(tweetId));
    }

    @GetMapping(path = URI_TWEETS + "/valid" + "/{userId}")
    @Operation(summary = Documentation.get_validate_tweet_by_user_op_summary,
            description = Documentation.get_validate_tweet_by_user_op_description,
            tags = "tweet-api", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = TweetModel.class)),
            responseCode = "200", description = Documentation.get_validate_tweet_by_user_op_resp_description))
    public ResponseEntity<?> getValidTweets(@PathVariable String userId) {
        List<TweetModel> validTweets = tweetService.getValidTweets(Long.parseLong(userId));
        return ResponseEntity.ok(validTweets);
    }

    @GetMapping(path = URI_HASHTAGS)
    @Operation(summary = Documentation.get_hashtags_op_summary,
            description = Documentation.get_hashtags_op_description,
            tags = "tweet-api", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = HashtagsModel.class)),
            responseCode = "200", description = Documentation.get_hashtags_op_resp_description))
    public ResponseEntity<?> getMostUsedHashtags(
            @Parameter(description = Documentation.get_hashtags_ptm_description, required = false, example = "10") @RequestParam(required = false) Integer hashtags) {

        int classification = hashtags == null ? 10 : hashtags;
        List<HashtagsModel> mostUsedHashtags = tweetService.getMostUsedHashtags(classification);
        return ResponseEntity.ok(mostUsedHashtags);
    }
}
