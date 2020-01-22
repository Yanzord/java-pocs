package com.yanzord.cloud.tema10.totalizerservice.controller;

import com.yanzord.cloud.tema10.totalizerservice.service.TotalizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/totalizer")
public class TotalizerController {

    @Autowired
    private TotalizerService totalizerService;

    @RequestMapping("/repos/{githubUser}/tweets/{twitterUser}")
    @ResponseBody
    private Map<String, Integer> getUserData(@PathVariable(value = "githubUser") String githubUser, @PathVariable(value = "twitterUser") String twitterUser) {
        Map<String, Integer> usersData = new HashMap<>();
        usersData.put(githubUser, totalizerService.getUserGithubRepositories(githubUser));
        usersData.put(twitterUser, totalizerService.getTweetsByUser(twitterUser));

        return usersData;
    }

    @RequestMapping("/repos/{githubUser}")
    @ResponseBody
    private Map<String, Integer> getUserGithubData(@PathVariable(value = "githubUser") String githubUser) {
        Map<String, Integer> userData = new HashMap<>();
        userData.put(githubUser, totalizerService.getUserGithubRepositories(githubUser));

        return userData;
    }

    @RequestMapping("/tweets/{twitterUser}")
    @ResponseBody
    private Map<String, Integer> getUserTwitterData(@PathVariable(value = "twitterUser") String twitterUser) {
        Map<String, Integer> userData = new HashMap<>();
        userData.put(twitterUser, totalizerService.getTweetsByUser(twitterUser));

        return userData;
    }
}
