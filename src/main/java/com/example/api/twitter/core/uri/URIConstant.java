package com.example.api.twitter.core.uri;

import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 * Class to define all api's uri
 */
@Component
public final class URIConstant {

    public static final String ENTITY_API = "/twitter";
    public static final String API_VERSION = "/v1";

    public static final String URI_TWEETS = "/tweets";
    public static final String ENTITY_TWEETS = "Tweet";

    public static final String URI_HASHTAGS = "/hashtags";
}