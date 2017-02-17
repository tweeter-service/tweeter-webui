package com.example;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

@Component
public class TweeterPrincipalExtractor implements PrincipalExtractor {

	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		return new TweeterUser(map.get("name").toString(),
				map.get("firstName").toString(), map.get("lastName").toString(),
				map.get("email").toString());
	}
}
