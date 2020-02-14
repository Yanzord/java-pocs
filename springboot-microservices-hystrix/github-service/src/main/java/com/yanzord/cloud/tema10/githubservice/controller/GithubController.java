package com.yanzord.cloud.tema10.githubservice.controller;

import com.yanzord.cloud.tema10.githubservice.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/github")
public class GithubController {

    @Autowired
    private GithubService githubService;

    @RequestMapping("/{user}")
    @ResponseBody
    private Integer getUserRepositories(@PathVariable(value = "user") String user) {
        try {
            return githubService.getUserGithubRepositories(user);
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Github user not found", e);
        }
    }
}
