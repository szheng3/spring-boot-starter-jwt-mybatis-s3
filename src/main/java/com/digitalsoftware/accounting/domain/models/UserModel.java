package com.digitalsoftware.accounting.domain.models;

import com.digitalsoftware.accounting.domain.builder.Builder;
import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.emun.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Integer id;

    private String avatar;

    private String phone;


    private String nickname;

    private List<RoleType> role;

    private String details;


//    public UserModel(User user, Account account) {
//        this.id = user.getId();
//        this.avatar = user.getAvatar();
//        this.phone = user.getPhone();
//        this.nickname = user.getNickname();
//        this.role = account.getRoleTypes();
//
//    }

    public static UserModelBuilder getBuilder() {
        return new UserModelBuilder();
    }

    public static class UserModelBuilder implements Builder<Mono<UserModel>> {


        private Integer id;

        private String avatar;

        private String phone;


        private String nickname;

        private List<RoleType> role;

        private String details;


        public UserModelBuilder withUser(Mono<User> user) {

            user.subscribe(user1 -> {
                this.id = user1.getId();
                this.avatar = user1.getAvatar();
                this.phone = user1.getPhone();
                this.nickname = user1.getNickname();
                this.details = user1.getDetails();
            });


            return this;
        }

        public UserModelBuilder withAccount(Account account) {
            if (id == null) {
                this.id = account.getId();
            }
            if (avatar == null) {
                this.avatar = account.getAvatar();
            }
            if (phone == null) {
                this.phone = account.getUsername();
            }
            if (nickname == null) {
                this.nickname = account.getNickname();
            }
            if (details == null) {
                this.details = account.getDetails();
            }

            if (role == null) {
                this.role = account.getRoleTypes();
            }

            return this;
        }


        @Override
        public Mono<UserModel> build() {
            return Mono.just(new UserModel(id, avatar, phone, nickname, role, details));
        }
    }
}
