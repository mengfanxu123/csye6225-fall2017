package com.csye6225.demo;
/*
 *  Shuangshuang Xu  001239511 xu.shua@husky.neu.edu
 *  Mengfei Zhang    001115548 zhang.mengf@husky.neu.edu
 *  Mengfan Xu       001238833 xu.mengf@husky.neu.edu
 *  YeHui Rong       001957596 rong.ye@husky.neu.edu
 */




import com.csye6225.demo.pojo.User;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;



public class DemoApplicationTests {

    @Test
    public void showAll() {

        boolean flag = false;
        User newuser = new User();
        newuser.setEmail("123");

        List<User> userList = new ArrayList<>();
        User newuser1 = new User();
        newuser1.setEmail("123");
        newuser1.setPassword("123");
        userList.add(newuser1);

        User newuser2 = new User();
        newuser2.setEmail("1233");
        newuser2.setPassword("1233");
        userList.add(newuser2);



        for (User a : userList) {
            if (a.getEmail().equals(newuser.getEmail())) {
                flag = true;
                break;
            }
        }
        assertEquals(true,flag);
    }
}
