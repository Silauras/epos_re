package khai.edu.epos_re.security.filters;

public final class SecurityConstants {

    static final String AUTH_LOGIN_URL = "api/auth/login";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    public static final String PASSWORD_SALT = "KaPdSgVkYp3s6v9y$B&E";
    // JWT token defaults
    static final String TOKEN_HEADER = "Authorization";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String TOKEN_TYPE = "JWT";
    static final String TOKEN_ISSUER = "secure-api";
    static final String TOKEN_AUDIENCE = "secure-app";
    static final int EXPIRATION_TIME = 864000000;

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}

