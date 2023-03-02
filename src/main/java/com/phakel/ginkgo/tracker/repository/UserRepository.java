package com.phakel.ginkgo.tracker.repository;

import com.phakel.ginkgo.tracker.dto.UserDto;
import com.phakel.ginkgo.tracker.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> findByUsernameOptional(String username) {
        return find("username", username).firstResultOptional();
    }

    public Optional<UserDto> findByUsernameOptionalToDto(String username) {
        return find("username", username).project(UserDto.class).firstResultOptional();
    }

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public UserDto findByUsernameToDto(String username) {
        return find("username", username).project(UserDto.class).firstResult();
    }

    public Optional<UserDto> findByUserIdOptionalToDto(String userId) {
        return find("id", userId).project(UserDto.class).firstResultOptional();
    }

    public Optional<User> findByUserIdOptional(String userId) {
        return find("id", userId).firstResultOptional();
    }

    public boolean isUserExistByUsername(String username) {
        return findByUsernameOptional(username).isPresent();
    }

}
