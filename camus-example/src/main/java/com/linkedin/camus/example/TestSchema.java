package com.linkedin.camus.example;

import com.linkedin.camus.etl.kafka.CamusJob;
import com.linkedin.camus.etl.kafka.coders.KafkaAvroMessageDecoder;
import com.linkedin.camus.etl.kafka.coders.MessageDecoderFactory;
import com.linkedin.camus.example.schemaregistry.DummySchemaRegistry;
import com.linkedin.camus.schemaregistry.MemorySchemaRegistry;
import com.linkedin.camus.schemaregistry.SchemaDetails;
import org.apache.avro.Schema;
import org.apache.hadoop.mapreduce.JobContext;

public class TestSchema {

    public static void main(String args[]) {
        DummySchemaRegistry r = new DummySchemaRegistry();
        SchemaDetails sd = r.getLatestSchemaByTopic("validated_localpoint_events_avro_topic");
        System.out.println(sd.toString());
        Schema msr = r.getSchemaByID("validated_localpoint_events_avro_topic", "1");
        System.out.println(msr.toString());

        System.out.println("done...");
    }
}