package com.yanzord.cloud.tema10.twitterservice.controller;

import com.yanzord.cloud.tema10.twitterservice.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @RequestMapping("/{user}")
    @ResponseBody
    private Integer getUserTweets(@PathVariable(value = "user") String user) {
        try {
            return twitterService.getUserTweets(user);
        } catch (TwitterException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Twitter user not found", e);
        }
    }
}
