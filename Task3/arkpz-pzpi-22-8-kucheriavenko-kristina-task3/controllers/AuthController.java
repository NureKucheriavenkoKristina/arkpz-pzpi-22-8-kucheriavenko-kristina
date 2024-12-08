package com.BiologicalMaterialsSystem.controllers;

import com.BiologicalMaterialsSystem.AES256Encryption;
import com.BiologicalMaterialsSystem.model.User;
import com.BiologicalMaterialsSystem.service.SessionService;
import com.BiologicalMaterialsSystem.service.UserService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final SessionService sessionService;

    @PostMapping("/in")
    public ResponseEntity<String> login(@RequestBody User loginRequest) throws Exception {
        String username = loginRequest.getLogin();
        String password = loginRequest.getPassword();

        User user = userService.getUserByLogin(username);
        password = AES256Encryption.encrypt(password);

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(UNAUTHORIZED).body("Невірні облікові дані");
        }
        sessionService.startSession(user);

        return ResponseEntity.ok("Вхід успішний. Ваш ID: " + user.getUserID());
    }

    @PostMapping("/out")
    public ResponseEntity<String> logout(@RequestParam Long userId) {
        sessionService.endSession(userId);
        return ResponseEntity.ok("Вихід успішний.");
    }
}
