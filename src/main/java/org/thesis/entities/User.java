package org.thesis.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name="USER",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"UUID"})
)
public class User {
    @Id
    Long username;
    UUID uuid;
    String password;
    LocalDateTime lastSuccessfulLogin;


}
