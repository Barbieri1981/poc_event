package com.poc.security.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_EVENT")
public class UserEvent {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "user_seq")
    @Column(name = "ID_USER", nullable = false)
    private long id;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_NAME")
    private String userName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROL",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROL")
    )
    private List<Rol> roles;
}

