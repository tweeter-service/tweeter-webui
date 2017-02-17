package com.example;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class TweeterController {
	private final RestTemplate restTemplate;
	private final URI tweeterApiUri;

	public TweeterController(RestTemplate restTemplate,
			@Value("${tweeter.api.uri}") URI tweeterApiUri) {
		this.restTemplate = restTemplate;
		this.tweeterApiUri = tweeterApiUri;
	}

	@GetMapping("/")
	String timelines(Model model) {
		RequestEntity<?> request = RequestEntity.get(UriComponentsBuilder
				.fromUri(tweeterApiUri).pathSegment("timelines").build().toUri()).build();
		List<Tweet> tweets = restTemplate
				.exchange(request, new ParameterizedTypeReference<List<Tweet>>() {
				}).getBody();
		model.addAttribute("tweets", tweets);
		return "tweets";
	}

	@GetMapping("/tweets")
	String tweets(Model model) {
		RequestEntity<?> request = RequestEntity.get(UriComponentsBuilder
				.fromUri(tweeterApiUri).pathSegment("tweets").build().toUri()).build();
		List<Tweet> tweets = restTemplate
				.exchange(request, new ParameterizedTypeReference<List<Tweet>>() {
				}).getBody();
		model.addAttribute("tweets", tweets);
		return "tweets";
	}

	@PostMapping("/tweets")
	String tweets(@RequestParam String text) {
		RequestEntity<?> request = RequestEntity.post(UriComponentsBuilder
				.fromUri(tweeterApiUri).pathSegment("tweets").build().toUri())
				.body(Collections.singletonMap("text", text));
		restTemplate.exchange(request, Void.class);
		return "redirect:/";
	}

	public static class Tweet {
		public String tweetId;
		public String username;
		public String text;
		public Date createdAt;
	}
}
