package org.huhu.spring.security.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.huhu.spring.security.demo.constant.EncryptionAlgorithm;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Enumerated(value = STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "owner", fetch = EAGER)
    private List<Authority> authorities;

}
