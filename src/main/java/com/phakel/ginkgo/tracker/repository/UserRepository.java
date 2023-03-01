package com.phakel.ginkgo.tracker.repository;

import com.phakel.ginkgo.tracker.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> findByUsernameOptional(String username) {
        return find("username", username).firstResultOptional();
    }

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public Optional<User> findByUserIdOptional(String userId) {
        return find("id", userId).firstResultOptional();
    }

    public User findByUserId(String userId) {
        return find("id", userId).firstResult();
    }

    public boolean isUserExistByUsername(String username) {
        return findByUsernameOptional(username).isPresent();
    }

    public boolean isUserExistByUserId(String userId) {
        return findByUserIdOptional(userId).isPresent();
    }
}
