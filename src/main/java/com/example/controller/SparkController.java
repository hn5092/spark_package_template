package com.example.controller;

import com.example.SparkProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SparkController {
  private static final Logger log = LoggerFactory.getLogger(SparkController.class);
  @Autowired
  SparkProcess sparkProcess;

  @RequestMapping("/sql")
  public String sql(String sql) {
    return  sparkProcess.runSql(sql);
  }
}
