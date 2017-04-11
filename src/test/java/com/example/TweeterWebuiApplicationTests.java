package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TweeterWebuiApplicationTests {
	@LocalServerPort
	int port;
	WebClient webClient;

	@Before
	public void init() {
		webClient = new WebClient();
		DefaultCredentialsProvider userCredentials = new DefaultCredentialsProvider();
		userCredentials.addCredentials("user", "password");
		webClient.setCredentialsProvider(userCredentials);
	}

	@After
	public void close() throws Exception {
		webClient.close();
	}

	@Test
	public void contextLoads() throws Exception {
		HtmlPage page = webClient.getPage("http://localhost:" + port);
		System.out.println(page.asXml());
	}

}
