package org.cookie.fx;

public class User1<T> {
    private String username;
    private String password;
    private T username1;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public T getUsername1() {
        return username1;
    }

    public void setUsername1(T username1) {
        this.username1 = username1;
    }

}
