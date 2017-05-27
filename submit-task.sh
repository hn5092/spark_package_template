#!/usr/bin/env bash
./spark-submit --master yarn --driver-memory 5G --num-executors 10 spring4spark-0.0.1-SNAPSHOT.jar  --class com.pajk.bigdata.spring4spark.Spring4sparkApplication
