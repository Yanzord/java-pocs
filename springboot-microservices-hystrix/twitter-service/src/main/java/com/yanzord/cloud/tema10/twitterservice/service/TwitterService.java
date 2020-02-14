package com.yanzord.cloud.tema10.twitterservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yanzord.cloud.tema10.twitterservice.Twitter4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

@Service
public class TwitterService {

    @Autowired
    private Twitter4j twitter4j;

    @HystrixCommand(fallbackMethod = "defaultUserTweets")
    public Integer getUserTweets(String user) throws TwitterException {
        return twitter4j.getTwitterInstance().showUser(user).getStatusesCount();
    }

    public Integer defaultUserTweets(String user) {
        return 0;
    }
}
