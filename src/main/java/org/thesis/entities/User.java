package org.thesis.entities;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter @Setter @NoArgsConstructor
@UserDefinition
public class User {

    private static final String ADMIN = "admin";
    private static final String BASIC = "basic";

    @Id
    @Column(name = "USERNAME", nullable = false)
    @Username
    private String username;

    @Column(name = "PWD", nullable = false)
    @Password
    private String password;

    @Column(name = "LAST_SUCCESSFUL_LOGIN")
    private LocalDateTime lastSuccessfulLogin;

    @Column(name = "ROLES")
    @Roles
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ShoppingList> shoppingList = new ArrayList<>();

    public User  newUserPass() {
        this.setPassword(BcryptUtil.bcryptHash(this.getPassword()));
        return this;
    }

    @PrePersist
    @PreUpdate
    private void prePersist() {
        if (!ADMIN.equals(this.role)) {
            this.role = BASIC;
        }
        lastSuccessfulLogin = LocalDateTime.now();
    }
}
