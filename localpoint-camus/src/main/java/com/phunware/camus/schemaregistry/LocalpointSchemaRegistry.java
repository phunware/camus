package com.phunware.camus.schemaregistry;

import com.linkedin.camus.schemaregistry.MemorySchemaRegistry;
import com.phunware.messages.avro.LocalpointEventMsgAvro;
import org.apache.avro.Schema;

public class LocalpointSchemaRegistry extends MemorySchemaRegistry<Schema> {
    public LocalpointSchemaRegistry() {
        super();
        super.register("validated_localpoint_events_avro_topic", LocalpointEventMsgAvro.SCHEMA$);
    }
}
