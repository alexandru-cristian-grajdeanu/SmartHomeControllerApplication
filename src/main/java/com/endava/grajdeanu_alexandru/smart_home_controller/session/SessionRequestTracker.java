package com.endava.grajdeanu_alexandru.smart_home_controller.session;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
@Getter
public class SessionRequestTracker {
    private int requestCount = 0;
    private final String sessionId = UUID.randomUUID().toString();

    public void incrementRequestCount() {
        requestCount++;
    }
}
