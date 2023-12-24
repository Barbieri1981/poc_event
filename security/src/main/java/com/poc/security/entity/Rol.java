package com.poc.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROL")
public class Rol {

    @Id
    @SequenceGenerator(name = "rol_seq", sequenceName = "rol_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rol_seq")
    @Column(name = "ID_ROL", nullable = false)
    private Long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<UserEvent> userEvents;
}

