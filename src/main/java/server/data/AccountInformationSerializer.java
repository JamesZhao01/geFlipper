package server.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

class AccountInformationSerializer extends StdSerializer<AccountInformation> {

    public AccountInformationSerializer() {
        this(null);
    }

    public AccountInformationSerializer(Class<AccountInformation> t) {
        super(t);
    }

    @Override
    public void serialize(AccountInformation accountInformation, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("processId", accountInformation.getProcessId());
        jsonGenerator.writeBooleanField("isLocked", accountInformation.isLocked());
        jsonGenerator.writeObjectField("statusUpdate", accountInformation.getStatusUpdate());
        jsonGenerator.writeEndObject();
    }
}
