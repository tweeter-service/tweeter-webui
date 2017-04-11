package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(workOffline = true, ids = "com.example:tweeter-api:+:stubs:8082")
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
		Document doc = Jsoup.parse(page.asXml());
		Elements elements = doc.select("h3.uk-panel-title");
		assertThat(elements).hasSize(4);
		assertThat(elements.get(0).text()).isEqualTo("tweet1");
		assertThat(elements.get(0).nextElementSibling().text()).isEqualTo("user");
		assertThat(elements.get(1).text()).isEqualTo("tweet2");
		assertThat(elements.get(1).nextElementSibling().text()).isEqualTo("user");
		assertThat(elements.get(2).text()).isEqualTo("tweet3");
		assertThat(elements.get(2).nextElementSibling().text()).isEqualTo("foo");
		assertThat(elements.get(3).text()).isEqualTo("tweet4");
		assertThat(elements.get(3).nextElementSibling().text()).isEqualTo("user");
	}

}
