package com.digitalsoftware.accounting.domain.builder;

import com.digitalsoftware.accounting.domain.generated.User;

public final class UserBuilder {
    private Integer id;
    private String avatar;
    private String phone;
    private String password;
    private String nickname;
    private String details;

    private UserBuilder() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public UserBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserBuilder withDetails(String details) {
        this.details = details;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setAvatar(avatar);
        user.setPhone(phone);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setDetails(details);
        return user;
    }
}
