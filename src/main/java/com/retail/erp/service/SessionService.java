package com.retail.erp.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class SessionService {
	
	
	private final Map<String, Long> sessionMap = new ConcurrentHashMap<>();
    private final long TIMEOUT = 20 * 60 * 1000;

    public void updateSession(String token) {
        sessionMap.put(token, System.currentTimeMillis());
    }

    public boolean isActive(String token) {
        Long lastUsed = sessionMap.get(token);
        if (lastUsed == null || System.currentTimeMillis() - lastUsed > TIMEOUT) {
            sessionMap.remove(token);
            return false;
        }
        updateSession(token); // refresh timestamp
        return true;
    }

    public void invalidate(String token) {
        sessionMap.remove(token);
    }
}
