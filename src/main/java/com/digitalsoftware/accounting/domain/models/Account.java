package com.digitalsoftware.accounting.domain.models;

import com.digitalsoftware.accounting.domain.generated.Role;
import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.emun.domain.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Account extends JdkSerializationRedisSerializer implements UserDetails {


    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String details;
    private List<RoleType> roleTypes;

    private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;

    public Account() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public Account(Integer id, String avatar, String username, String password, String nickname, List<RoleType> roleTypes, String details) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.roleTypes = roleTypes;
        this.avatar = avatar;
        this.details = details;


        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void grantAuthority(RoleType authority) {
        if (roleTypes == null) roleTypes = new ArrayList<>();
        roleTypes.add(authority);
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roleTypes.forEach(roleType -> authorities.add(new SimpleGrantedAuthority(roleType.toString())));
        return authorities;
    }

    public List<RoleType> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(List<RoleType> roleTypes) {
        this.roleTypes = roleTypes;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String getUsername() {
        return username;
    }

    public static AccountBuilder getBuilder() {

        return new AccountBuilder();

    }

    public static class AccountBuilder {

        private Integer id;
        private String username;
        private String password;
        private String nickname;
        private String avatar;
        private String details;
        private List<RoleType> roleTypes;

        public AccountBuilder withOAuth2AuthenticationDetails(OAuth2AuthenticationDetails oAuth2AuthenticationDetails) {

            Map<String, Object> decodedDetails = (Map<String, Object>) oAuth2AuthenticationDetails.getDecodedDetails();
            id = (Integer) decodedDetails.get("id");
            username = (String) decodedDetails.get("user_name");
            ArrayList authorities = (ArrayList) decodedDetails.get("authorities");
            roleTypes = (List<RoleType>) authorities.stream().map(o -> RoleType.valueOf((String) o)).collect(Collectors.toList());
            return this;

        }

        public AccountBuilder withUser(User user) {
            if (id == null)
                id = user.getId();
            if (username == null)
                username = user.getPhone();
            if (nickname == null)
                nickname = user.getNickname();
            if (avatar == null)
                avatar = user.getAvatar();
            if (details == null)
                details = user.getDetails();
            if (password == null)
                password = user.getPassword();
            return this;
        }

        public Account build() {

            return new Account(id, avatar, username, password, nickname, roleTypes, details);
        }

        public AccountBuilder withRole(List<Role> roleTypes) {
            this.roleTypes = roleTypes.stream().map(Role::getRole).collect(Collectors.toList());
            return this;
        }

        public AccountBuilder withUserRole(UserRole userRole) {
            this.roleTypes = userRole.getRoleTypes();

            return withUser(userRole.getUser());
        }
    }


}
