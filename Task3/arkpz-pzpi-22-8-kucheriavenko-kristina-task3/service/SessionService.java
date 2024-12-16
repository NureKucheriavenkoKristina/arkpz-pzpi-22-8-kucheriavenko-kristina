package com.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.model.User;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionService {

    private final ConcurrentHashMap<Long, User> activeSessions = new ConcurrentHashMap<>();

    public void startSession(User user) {
        activeSessions.put(user.getUserID(), user);
    }

    public void endSession(Long userId) {
        activeSessions.remove(userId);
    }
}
