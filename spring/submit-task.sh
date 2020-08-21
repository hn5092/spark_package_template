#!/usr/bin/env bash
spark-submit --master yarn --driver-memory 5G --num-executors 10   --class org.springframework.boot.loader.JarLauncher /tmp/demo-0.0.1-SNAPSHOT.jar