package com.bachar.recipes.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username")) for unique constraints
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{app.constraints.username.NotNull.message}")
    @Size(min = 4, message = "{app.constraints.Size.message}")
    @UniqueUsername(message = "{app.constraints.username.UniqueUsername.message}")
    private String username;

    @NotNull(message = "{app.constraints.displayName.NotNull.message}")
    @Size(min = 4, message = "{app.constraints.Size.message}")
    private String displayName;

    @NotNull(message = "{app.constraints.password.NotNull.message}")
    @Size(min = 8, message = "{app.constraints.Size.message}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{app.constraints.password.Pattern.message}")
    private String password;
}
