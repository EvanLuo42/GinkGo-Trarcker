package com.phakel.ginkgo.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.phakel.ginkgo.dto.UserDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "user_db")
@Data
@NoArgsConstructor
public class User extends PanacheEntityBase {
    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(length = 40, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    public UserDTO to() {
        var userDTO = new UserDTO();
        userDTO.setUsername(this.getUsername());
        userDTO.setEmail(this.getEmail());
        return userDTO;
    }

    public void setPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
