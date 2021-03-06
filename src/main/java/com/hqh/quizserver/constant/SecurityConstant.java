package com.hqh.quizserver.constant;

public class SecurityConstant {

    // 5 days
    public static final long EXPIRATION_TIME = 432_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Authorization";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified.";
    public static final String GET_ARRAYS = "AMBITIOUS MEN";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal.";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page.";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page.";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {
            "/auth/login",
            "/auth/register",
            "/auth/resetPassword/**",
            "/swagger-ui/**",
    };

}
