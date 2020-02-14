package com.yanzord.cloud.tema10.githubservice.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubService {
    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultUserGithubRepositories")
    public Integer getUserGithubRepositories(String user) {
        String userRepos = restTemplate.getForObject(GITHUB_API_URL + user + "/repos", String.class);
        JsonArray parsedUserRepos = JsonParser.parseString(userRepos).getAsJsonArray();

        return parsedUserRepos.size();
    }

    public Integer defaultUserGithubRepositories(String user) {
        return 0;
    }
}
