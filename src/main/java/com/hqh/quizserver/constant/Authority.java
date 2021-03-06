package com.hqh.quizserver.constant;

public class Authority {

    public static final String[] USER_AUTHORITIES = {
            "user:read",
            "user:update",

    };
    public static final String[] ADMIN_AUTHORITIES = {
            "user:create",
            "user:delete",
            "user:read",
            "user:update",
    };
    public static final String[] TEACHER_AUTHORITIES = {
            "user:create",
            "user:delete",
            "user:read",
            "user:update"
    };

}
