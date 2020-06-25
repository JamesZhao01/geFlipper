package server.data;

import com.example.components.Status;
import com.example.components.StatusUpdate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;

class StatusUpdateSerializer extends StdSerializer<StatusUpdate> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    StatusUpdateSerializer() {
        this(null);
    }

    StatusUpdateSerializer(Class<StatusUpdate> t) {
        super(t);
    }
    @Override
    public void serialize(StatusUpdate statusUpdate, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        ObjectNode node = OBJECT_MAPPER.readValue(JsonFormat.printer().includingDefaultValueFields().print(statusUpdate), ObjectNode.class);

        jsonGenerator.writeObject(node);
    }
}
