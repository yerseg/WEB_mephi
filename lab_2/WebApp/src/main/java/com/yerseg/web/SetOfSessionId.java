package com.yerseg.web;

import java.util.HashSet;
import java.util.Set;

public class SetOfSessionId {
    private final Set<String> sessionIdSet;

    private SetOfSessionId() {
        sessionIdSet = new HashSet<>();
    }

    private static class SetOfSessionIdHolder {
        private final static SetOfSessionId instance = new SetOfSessionId();
    }

    public static SetOfSessionId getInstance() {
        return SetOfSessionIdHolder.instance;
    }

    public void putSessionId(String sessionId) {
        sessionIdSet.add(sessionId);
    }

    public boolean containsSessionId(String sessionId) {
        return sessionIdSet.contains(sessionId);
    }
}
