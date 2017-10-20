package com.csye6225.demo.repository;

import com.csye6225.demo.pojo.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRpository extends CrudRepository<Attachment,String> {
}
