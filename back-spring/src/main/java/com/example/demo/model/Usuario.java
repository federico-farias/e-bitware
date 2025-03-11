package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table
public class Usuario {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Boolean active;

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

}
