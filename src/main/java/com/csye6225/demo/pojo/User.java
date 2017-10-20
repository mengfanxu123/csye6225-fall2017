package com.csye6225.demo.pojo;

/*
 *  Shuangshuang Xu  001239511 xu.shua@husky.neu.edu
 *  Mengfei Zhang    001115548 zhang.mengf@husky.neu.edu
 *  Mengfan Xu       001238833 xu.mengf@husky.neu.edu
 *  YeHui Rong       001957596 rong.ye@husky.neu.edu
 */

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "incrementGenerator",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="incrementGenerator",strategy = "increment")
    private long id;
    private String email;
    private String password;

    @OneToMany(cascade = {CascadeType.ALL},fetch =FetchType.LAZY,mappedBy = "user")
    @JsonManagedReference
    private Set<Task> task;

    public User(){

    }


    public long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }
}
