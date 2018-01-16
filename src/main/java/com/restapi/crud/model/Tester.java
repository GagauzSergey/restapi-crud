package com.restapi.crud.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Repository
@Data
@NoArgsConstructor(force = true)

public class Tester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private TesterPosition position;

    @Column(name = "salary")
    private int salary;

    public Tester(String firstname, String lastname, TesterPosition testerPosition, int salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = testerPosition;
        this.salary = salary;
    }
}
