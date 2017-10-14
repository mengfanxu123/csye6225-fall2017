package com.csye6225.demo.repository;

import com.csye6225.demo.pojo.Task;
import org.springframework.data.repository.CrudRepository;

public interface FileRpository extends CrudRepository<Task,String> {
}
