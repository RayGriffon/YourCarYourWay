package fr.openclassrooms.YourCarYourWay.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "t_user_usr", uniqueConstraints = @UniqueConstraint(name = "UK_usr_email", columnNames = "usr_email"))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "usr_name")
    private String name;

    @Column(name = "usr_password")
    private String password; // stocke le hash (ok pour POC)

    @Email
    @NotBlank
    @Column(name = "usr_email", nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "usr_createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "usr_updatedAt")
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
