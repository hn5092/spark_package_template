package com.pajk.bigdata.spring4spark.controller;

import com.pajk.bigdata.SparkProcess;
import org.apache.spark.sql.SparkSession;
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

  @RequestMapping("/spark")
  public String spark() {
    sparkProcess.runPi();
    return "ok!";
  }
  @RequestMapping("/sparkSql")
  public String sparkCount() {
    sparkProcess.runSql();
    return "ok!";
  }
}
