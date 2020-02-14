package com.yanzord.cloud.tema10.totalizerservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yanzord.cloud.tema10.totalizerservice.exception.GithubAccessException;
import com.yanzord.cloud.tema10.totalizerservice.exception.GithubInvalidUserException;
import com.yanzord.cloud.tema10.totalizerservice.exception.TwitterAccessException;
import com.yanzord.cloud.tema10.totalizerservice.exception.TwitterInvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class TotalizerService {
    private static final String GITHUB_SERVICE_URL = "http://localhost:9090/github/";
    private static final String TWITTER_SERVICE_URL = "http://localhost:8282/twitter/";

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultUserGithubRepositories")
    public Integer getUserGithubRepositories(String userName) {
        try {
            return restTemplate.getForObject(GITHUB_SERVICE_URL + userName, Integer.class);
        } catch (ResourceAccessException e) {
            throw new GithubAccessException();
        } catch (HttpClientErrorException e) {
            throw new GithubInvalidUserException();
        }
    }

    @HystrixCommand(fallbackMethod = "defaultUserTweets")
    public Integer getTweetsByUser(String userName) {
        try {
            return restTemplate.getForObject(TWITTER_SERVICE_URL + userName, Integer.class);
        } catch (ResourceAccessException e) {
            throw new TwitterAccessException();
        } catch (HttpClientErrorException e) {
            throw new TwitterInvalidUserException();
        }
    }

    public Integer defaultUserGithubRepositories(String user) {
        return 0;
    }

    public Integer defaultUserTweets(String user) {
        return 0;
    }
}
