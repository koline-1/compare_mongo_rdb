package com.dbs.exprmnt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "test_tbl")
@Table(name = "test_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;
}
