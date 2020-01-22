package com.yanzord.cloud.tema10.totalizerservice.service;

import com.yanzord.cloud.tema10.totalizerservice.AppConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
class TotalizerServiceTest {

	@Spy
	private TotalizerService totalizerService;

	@Test
	public void shouldReturnNumberOfUserGithubRepositoriesTest() {
		Mockito.doReturn(2).when(totalizerService).getUserGithubRepositories("yan");

		Integer expected = 2;
		Integer actual = totalizerService.getUserGithubRepositories("yan");

		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnResourceAccessExceptionWhenGithubServiceIsDownTest() {
		doThrow(ResourceAccessException.class)
				.when(totalizerService)
				.getUserGithubRepositories("user");

		Exception exception = assertThrows(ResourceAccessException.class, () -> {
			totalizerService.getUserGithubRepositories("user");
		});

		assertNotNull(exception);
	}

	@Test
	public void shouldReturnHttpClientErrorExceptionWhenGithubUserIsInvalidTest() {
		doThrow(HttpClientErrorException.class)
				.when(totalizerService)
				.getUserGithubRepositories("invalidUser");

		Exception exception = assertThrows(HttpClientErrorException.class, () -> {
			totalizerService.getUserGithubRepositories("invalidUser");
		});

		assertNotNull(exception);
	}

	@Test
	public void shouldReturnNumberOfUserTweetsTest() {
		Mockito.doReturn(2).when(totalizerService).getTweetsByUser("yan");

		Integer expected = 2;
		Integer actual = totalizerService.getTweetsByUser("yan");

		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnResourceAccessExceptionWhenTwitterServiceIsDownTest() {
		doThrow(ResourceAccessException.class)
				.when(totalizerService)
				.getTweetsByUser("user");

		Exception exception = assertThrows(ResourceAccessException.class, () -> {
			totalizerService.getTweetsByUser("user");
		});

		assertNotNull(exception);
	}

	@Test
	public void shouldReturnHttpClientErrorExceptionWhenTwitterUserIsInvalidTest() {
		doThrow(HttpClientErrorException.class)
				.when(totalizerService)
				.getTweetsByUser("invalidUser");

		Exception exception = assertThrows(HttpClientErrorException.class, () -> {
			totalizerService.getTweetsByUser("invalidUser");
		});

		assertNotNull(exception);
	}
}
