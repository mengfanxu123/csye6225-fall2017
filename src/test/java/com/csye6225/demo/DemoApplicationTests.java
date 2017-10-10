package com.csye6225.demo;
/*
 *  Shuangshuang Xu  001239511 xu.shua@husky.neu.edu
 *  Mengfei Zhang    001115548 zhang.mengf@husky.neu.edu
 *  Mengfan Xu       001238833 xu.mengf@husky.neu.edu
 *  YeHui Rong       001957596 rong.ye@husky.neu.edu
 */


import com.csye6225.demo.pojo.User;
import com.csye6225.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Test
    public void showAll() {

        List<User> userList=(List<User>)userRepository.findAll();
        for (User u:userList) {
            System.out.println(u.getEmail());
        }

  }

}
