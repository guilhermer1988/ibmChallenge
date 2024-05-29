package com.ibmchallengejava.ibmchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="Client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Integer age;

    @Setter
    @Getter
    private String address;

    @Setter
    @Getter
    private String email;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("client")
    private Account account;

    public Client() {
    }

    public Client(Long id, String name, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Account getAccount() {
        return account;
    }
}
