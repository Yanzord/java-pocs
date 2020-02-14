package com.yanzord.cloud.tema10.githubservice.service;

import com.yanzord.cloud.tema10.githubservice.AppConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
class GithubServiceTest {

	@Spy
	private GithubService githubService;

	@Test
	public void shouldReturnNumberOfUserGithubRepositoriesTest() {
		Mockito.doReturn(2).when(githubService).getUserGithubRepositories("yan");

		Integer expected = 2;
		Integer actual = githubService.getUserGithubRepositories("yan");

		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnResponseStatusExceptionWhenUserIsInvalidTest() {
		doThrow(ResponseStatusException.class)
				.when(githubService)
				.getUserGithubRepositories("invalidUser");

		Exception exception = assertThrows(ResponseStatusException.class, () -> {
			githubService.getUserGithubRepositories("invalidUser");
		});

		assertNotNull(exception);
	}
}
