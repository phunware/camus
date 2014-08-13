package com.linkedin.camus.example;

import com.linkedin.camus.etl.kafka.coders.KafkaAvroMessageEncoder;
import com.phunware.messages.avro.LocalpointEventMsgAvro;
import com.phunware.messages.avro.ValidatedLocalpointEventsAvroTopic;
import kafka.javaapi.producer.Producer;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.joda.time.DateTime;

import java.io.File;
import java.util.Properties;

public class AvroProducer {
    private static Producer<Integer, byte[]> producer;
    private final Properties props = new Properties();

    public AvroProducer() {
        props.put("metadata.broker.list", "192.168.86.10:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        producer = new Producer<Integer, byte[]>(new ProducerConfig(props));
    }

    public static void main(String[] args) throws Exception {
        //Schema schema = new Schema.Parser().parse(new File("ValidatedLocalpointEventAvroTopic.avsc"));
        Schema schema = LocalpointEventMsgAvro.SCHEMA$;

        //Using this schema, let's create some users.
        AvroProducer sp = new AvroProducer();
        String topic = "validated_localpoint_events_avro_topic";

        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("tenant_id", 1);
        user1.put("device_id", "asdfkads");
        user1.put("device_id_type", "IDTYPE");
        user1.put("device_encoding_type", "OPENUUID");
        user1.put("store_id", 1L);
        user1.put("geofence_id", 1L);
        long time = System.currentTimeMillis();
        user1.put("device_date_time", String.valueOf(time));
        user1.put("device_location_accuracy", "20.0");
        user1.put("device_location_latitude", "85.34");
        user1.put("device_location_longitude", "123.432");
        user1.put("request",null);
        user1.put("response",null);
        user1.put("requestDate",String.valueOf(time));
        user1.put("message_id",null);
        user1.put("push_notification_event_id", null);
        user1.put("callback_string",null);
        user1.put("campaign_id", null);
        user1.put("event_type", null);

        KafkaAvroMessageEncoder enc = new KafkaAvroMessageEncoder(topic, new Configuration());

        KeyedMessage<Integer, byte[]> data = new KeyedMessage<Integer, byte[]> (topic, enc.toBytes(user1));
        int i=0;
        while (i<100) {
            producer.send(data); i++;
        }
        producer.close();
    }
}
