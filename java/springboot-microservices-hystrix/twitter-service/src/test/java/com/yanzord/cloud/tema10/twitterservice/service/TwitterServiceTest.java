package com.yanzord.cloud.tema10.twitterservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.TwitterException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class TwitterServiceTest {

	@Spy
	private TwitterService twitterService;

	@Test
	public void shouldReturnNumberOfTweetsByUserTest() throws TwitterException {
		Mockito.doReturn(2).when(twitterService).getUserTweets("yan");

		Integer expected = 2;
		Integer actual = twitterService.getUserTweets("yan");

		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnResponseStatusExceptionWhenUserIsInvalidTest() throws TwitterException {
		doThrow(ResponseStatusException.class)
				.when(twitterService)
				.getUserTweets("invalidUser");

		Exception exception = assertThrows(ResponseStatusException.class, () -> {
			twitterService.getUserTweets("invalidUser");
		});

		assertNotNull(exception);
	}
}
