package com.csye6225.demo.repository;

/*
 *  Shuangshuang Xu  001239511 xu.shua@husky.neu.edu
 *  Mengfei Zhang    001115548 zhang.mengf@husky.neu.edu
 *  Mengfan Xu       001238833 xu.mengf@husky.neu.edu
 *  YeHui Rong       001957596 rong.ye@husky.neu.edu
 */

import com.csye6225.demo.pojo.User;
import  org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{

}
