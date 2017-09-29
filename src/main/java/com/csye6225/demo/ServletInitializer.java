package com.csye6225.demo;

/*
 *  Shuangshuang Xu  001239511 xu.shua@husky.neu.edu
 *  Mengfei Zhang    001115548 zhang.mengf@husky.neu.edu
 *  Mengfan Xu       001238833 xu.mengf@husky.neu.edu
 *  YeHui Rong       001957596 rong.ye@husky.neu.edu
 */

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

}
