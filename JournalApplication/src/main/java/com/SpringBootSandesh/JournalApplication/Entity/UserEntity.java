package com.SpringBootSandesh.JournalApplication.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Builder
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    //To create multiple journal lists with a single user
    @OneToMany(cascade = CascadeType.ALL)
            @JoinColumn(name="fk_usersid")
    List<JournalEntity> journalEntities = new ArrayList<>();


}

