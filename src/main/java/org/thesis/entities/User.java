package org.thesis.entities;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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

    @Column(name = "PASSWORD", nullable = false)
    @Password
    private String password;

    @Column(name = "LAST_SUCCESSFUL_LOGIN")
    private LocalDateTime lastSuccessfulLogin;

    @Column(name = "ROLE")
    @Roles
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ShoppingListByUser> shoppingListByUser = new ArrayList<>();

    public User newUser() {
        this.setPassword(BcryptUtil.bcryptHash(this.getPassword()));
        return this;
    }

    @PrePersist
    private void prePersist() {
        if (!ADMIN.equals(this.role)) {
            this.role = BASIC;
        }
    }
}
