/*package com.springmvc.kafka;

import java.util.concurrent.atomic.AtomicReference;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaSingleton {
	*//** 利用AtomicReference *//*
	 private static final AtomicReference<KafkaConsumer<String, String> > INSTANCE = new AtomicReference<KafkaConsumer<String, String> >();
	 *//**
	  * 私有化
	  *//*
	 private KafkaSingleton(){
	 }
	 *//**
	  * 用CAS确保线程安全
	  *//*
	 public static final KafkaConsumer<String, String>  getInstance(){
	  for (;;) {
		  KafkaConsumer<String, String>  current = INSTANCE.get();
	            if (current != null) {
	                return current;
	            }
	            current = new KafkaSingleton();
	            if (INSTANCE.compareAndSet(null, current)) {
	                return current;
	            }
	        }
	 }

}
*/