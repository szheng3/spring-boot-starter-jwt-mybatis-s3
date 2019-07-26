package com.digitalsoftware.accounting.response.Controller;

import com.digitalsoftware.accounting.domain.models.UserModel;
import com.digitalsoftware.accounting.response.Response;
import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class CurrentUserResponse extends Response {

    private UserModel userModel;

    public CurrentUserResponse(UserModel userModel) {
        this.userModel = userModel;
    }


    public static final class CurrentUserResponseBuilder {
        protected int status = 1;
        private UserModel userModel;

        private CurrentUserResponseBuilder() {
        }

        public static CurrentUserResponseBuilder builder() {
            return new CurrentUserResponseBuilder();
        }

        public CurrentUserResponseBuilder withStatus(int status) {
            this.status = status;
            return this;
        }

        public CurrentUserResponseBuilder withUserModel(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        public Mono<CurrentUserResponse> build() {
            CurrentUserResponse currentUserResponse = new CurrentUserResponse(userModel);
            currentUserResponse.status = this.status;
            return Mono.just(currentUserResponse);
        }
    }
}
