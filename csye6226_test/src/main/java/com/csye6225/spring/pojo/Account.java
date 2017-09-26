package com.csye6225.spring.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "account")
public class Account {
    private int id;
    private String password;
    private String email;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="incrementGenerator", strategy=GenerationType.IDENTITY)  
    @GenericGenerator(name="incrementGenerator", strategy="increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
