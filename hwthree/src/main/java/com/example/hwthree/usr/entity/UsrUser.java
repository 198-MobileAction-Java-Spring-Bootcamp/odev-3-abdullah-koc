package com.example.hwthree.usr.entity;

import com.example.hwthree.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USR_USER")
@Getter
@Setter
public class UsrUser extends BaseEntity {

    @Id
    @SequenceGenerator(name = "UsrUser", sequenceName = "USR_USER_ID_SEQ")
    @GeneratedValue(generator = "UsrUser")
    private Long id;

    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 30, nullable = false)
    private String surname;

    @Column(name = "USERNAME", length = 30, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

}
