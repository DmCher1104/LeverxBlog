package com.leverx.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 3, message = "at least 3 characters")
    @NotEmpty(message = "username is required field")
    @Column(name = "username")
    private String username;
    @Size(min = 3, message = "at least 3 characters")
    @NotEmpty(message = "lastName is required field")
    @Column(name = "lastName")
    private String lastName;
    @Size(min = 3, message = "at least 3 characters")
    @NotEmpty(message = "password is required field")
    @Column(name = "password")
    private String password;

    @Pattern(regexp ="([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})" ,
            message = "use this template XXX@YYY.zzz")
    @NotEmpty(message = "email is required field")
    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "activationCode")
    private String activationCode;

    @Column(name = "created_at")
    private String createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
