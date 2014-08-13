package com.linkedin.camus.example.schemaregistry;

import com.phunware.messages.avro.LocalpointEventMsgAvro;
import com.phunware.messages.avro.ValidatedLocalpointEventsAvroTopic;
import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;

import com.linkedin.camus.example.records.DummyLog;
import com.linkedin.camus.example.records.DummyLog2;
import com.linkedin.camus.schemaregistry.MemorySchemaRegistry;

/**
 * This is a little dummy registry that just uses a memory-backed schema
 * registry to store two dummy Avro schemas. You can use this with
 * camus.properties
 */
public class DummySchemaRegistry extends MemorySchemaRegistry<Schema> {
    public DummySchemaRegistry() {
        super();
        super.register("validated_localpoint_events_avro_topic", LocalpointEventMsgAvro.SCHEMA$);
    }
    /*
	public DummySchemaRegistry(Configuration conf) {
		super();
		//super.register("DUMMY_LOG", DummyLog.SCHEMA$); //newBuilder().build().getSchema());
		//super.register("DUMMY_LOG_2", DummyLog2.newBuilder().build().getSchema());
        //super.register("VALIDATED_LOCALPOINT_EVENTS_AVRO_TOPIC", ValidatedLocalpointEventsAvroTopic.SCHEMA$); //newBuilder().build().getSchema());
        //super.register("VALIDATED_LOCALPOINT_EVENTS_AVRO_TOPIC", ValidatedLocalpointEventsAvroTopic.SCHEMA$);
        super.register("validated_localpoint_events_avro_topic", ValidatedLocalpointEventsAvroTopic.SCHEMA$);

	}
	*/
}
