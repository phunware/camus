package com.phunware.camus.decoder;

import com.linkedin.camus.coders.CamusWrapper;
import com.linkedin.camus.etl.kafka.coders.KafkaAvroMessageDecoder;
import com.phunware.messages.avro.LocalpointEventMsgAvro;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;

import java.io.IOException;

public class LocalpointKafkaAvroMessageDecoder extends KafkaAvroMessageDecoder {

    @Override
    public CamusWrapper<GenericData.Record> decode(byte[] payload) throws IOException {
        try {
            Schema schema = LocalpointEventMsgAvro.SCHEMA$;
            GenericDatumReader<GenericData.Record> reader = new GenericDatumReader<GenericData.Record>();

            reader.setSchema(schema);

            return new CamusWrapper<GenericData.Record>(
                reader.read(null, decoderFactory.binaryDecoder(payload, null))
            );

        } catch (IOException io) {
            throw new IOException(io);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
