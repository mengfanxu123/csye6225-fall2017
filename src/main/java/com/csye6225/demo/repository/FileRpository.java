package com.csye6225.demo.repository;

import com.csye6225.demo.pojo.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRpository extends CrudRepository<File,String> {
}
