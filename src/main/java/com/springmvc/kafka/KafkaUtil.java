package com.springmvc.kafka;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
public class KafkaUtil {
	
	
	private final static Logger log = LoggerFactory.getLogger(KafkaUtil.class);
	
	
	public static Properties getKafkaConsumerProp( boolean isStringValue) {
        Properties consumerProp = new Properties();
        consumerProp.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.121:9092");
        consumerProp.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        consumerProp.put(ConsumerConfig.GROUP_ID_CONFIG, "black");
        consumerProp.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        consumerProp.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        consumerProp.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        consumerProp.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        consumerProp.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProp.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                isStringValue ? StringDeserializer.class : ByteArrayDeserializer.class);
        consumerProp.put("request.timeout.ms", 60000);
        return consumerProp;
    }
	
	public static Iterator<ConsumerRecord<String, String>> test(KafkaConsumer<String, String> kafkaConsumer,List<String> topicList){
		
		
		
		

		try {
			
//			while (true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(5000);
			log.info("记录的数量是："+records.count());
			Iterator<ConsumerRecord<String, String>> iterator=records.iterator();
			if (iterator.hasNext()) {
				return iterator;
			}
				/*for (ConsumerRecord<String, String> consumerRecord : records) {
					String value = consumerRecord.value();
					//value 就是report的json
					System.out.println("value:" + value );
					reportAll= new Gson().fromJson(value, Report.class);
				}*/
//			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
//			kafkaConsumer.close();
//			topicList.clear();
//			reportAll.serverInfosMap.clear();
//			reportAll.containerInfosMap.clear();
//			reportAll=null;
		}
		return null;
	}

	
	public static Properties getKafkaProducerProp(boolean isStringValue) {
        Properties producerProp = new Properties();
        producerProp.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.121:9092");
        producerProp.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 100000000);
        producerProp.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProp.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                isStringValue ? StringSerializer.class : ByteArraySerializer.class);
        producerProp.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 100000000);
        producerProp.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 60000);
        producerProp.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        return producerProp;
    }
	
	public static void main(String[] args) {
		 KafkaProducer<String, String> reportProducer = new KafkaProducer<>(getKafkaProducerProp(true));
		 String reportAll="";
		 String s1="{\"age\":18,\"alarm_device\":\"1000045$1$0$0\","
					+ "\"alarm_snapshot\":\"\\\\192.168.1.130\\face_pic\\capture\\1000027$1$0$0\\1765219a-64aa-48ed-a808-35f595ceca7e.jpg\","
					+ "\"alarm_time\":\"20181018T164829\",\"alarm_type\":0,\"face_id\":\"32108819999999993\",\"gender\":\"u5973\",\"name\":\"u738bu83b9\"}";
		 String s2="{\"age\":20,\"alarm_device\":\"1000046$1$0$0\","
				 + "\"alarm_snapshot\":\"\\\\192.168.1.130\\face_pic\\capture\\1000027$1$0$0\\1765219a-64aa-48ed-a808-35f595ceca7e.jpg\","
				 + "\"alarm_time\":\"20181018T164829\",\"alarm_type\":0,\"face_id\":\"32108819999999993\",\"gender\":\"u5973\",\"name\":\"u738bu83b9\"}";
		 String s3="{\"age\":22,\"alarm_device\":\"1000047$1$0$0\","
				 + "\"alarm_snapshot\":\"\\\\192.168.1.130\\face_pic\\capture\\1000027$1$0$0\\74eb1604-d4b5-408e-ac1a-f6ac0e97748a.jpg\","
				 + "\"alarm_time\":\"20181018T164829\",\"alarm_type\":0,\"face_id\":\"32108819999999993\",\"gender\":\"u5973\",\"name\":\"u738bu83b9\"}";
		 String s4="{\"age\":13,\"alarm_device\":\"1000048$1$0$0\","
				 + "\"alarm_snapshot\":\"\\\\192.168.1.130\\face_pic\\capture\\1000027$1$0$0\\4dfad9a7-d4e6-48da-8457-4963f5c04e9c.jpg\","
				 + "\"alarm_time\":\"20181018T164829\",\"alarm_type\":0,\"face_id\":\"32108819999999993\",\"gender\":\"u5973\",\"name\":\"u738bu83b9\"}";
		 String s5="{\"age\":145,\"alarm_device\":\"1000050$1$0$0\","
				 + "\"alarm_snapshot\":\"\\\\192.168.1.130\\face_pic\\capture\\1000027$1$0$0\\135523dd-ce07-4bf8-8802-b8aa225cb41c.jpg\","
				 + "\"alarm_time\":\"20181018T164829\",\"alarm_type\":0,\"face_id\":\"32108819999999993\",\"gender\":\"u5973\",\"name\":\"u738bu83b9\"}";
		 String s6="{\"age\":15,\"alarm_device\":\"1000051$1$0$0\","
				 + "\"alarm_snapshot\":\"\\\\192.168.1.130\\face_pic\\capture\\1000027$1$0$0\\1765219a-64aa-48ed-a808-35f595ceca7e.jpg\","
				 + "\"alarm_time\":\"20181018T164829\",\"alarm_type\":0,\"face_id\":\"32108819999999993\",\"gender\":\"u5973\",\"name\":\"u738bu83b9\"}";
		 
		 reportProducer.send(new ProducerRecord<>("PicTest1237", "all", new Gson().toJson(reportAll)));
	}
}
