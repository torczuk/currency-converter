package com.github.torczuk.infrastructure.utils;

import com.github.torczuk.domain.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static Long getCurrentUserId() {
        Long userId = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }

    public static boolean checkIfCurrentUser(Long userId) {
        return userId == null ? false: userId.equals(getCurrentUserId());
    }
}
