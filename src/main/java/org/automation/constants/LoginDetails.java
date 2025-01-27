package org.automation.constants;

public enum LoginDetails {

    LOGIN_USER("Admin", "admin123"),
    INVALID_USER("User", "123786"),
    EMPTY_USER("empty_USER");

    private final String username;
    private String password;

    LoginDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    LoginDetails(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
