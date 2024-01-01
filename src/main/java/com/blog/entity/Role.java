package com.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60, nullable = false, columnDefinition = "VARCHAR(60) DEFAULT 'DEFAULT_ROLE'")
    private String name;



    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    // Constructors, getters, setters, etc.
}

