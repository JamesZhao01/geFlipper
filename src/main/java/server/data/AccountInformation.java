package server.data;

import com.example.components.StatusUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import server.tasks.Task;

import java.io.IOException;

public class AccountInformation {
    private StatusUpdate statusUpdate;
    private int processId;
    private boolean isLocked;
    private Task taskHandler;

    public AccountInformation(StatusUpdate statusUpdate, int processId, boolean isLocked) {
        this.statusUpdate = statusUpdate;
        this.processId = processId;
        this.isLocked = isLocked;
    }

    public Task getTaskHandler() {
        return taskHandler;
    }

    public void setTaskHandler(Task taskHandler) {
        this.taskHandler = taskHandler;
    }

    public StatusUpdate getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(StatusUpdate statusUpdate) {
        this.statusUpdate = statusUpdate;
        if(taskHandler != null)
            taskHandler.check(statusUpdate);

    }

    public ObjectNode getJacksonSerialization(ObjectMapper mapper) {
        ObjectNode node = mapper.convertValue(this, ObjectNode.class);
        try {
            node.set("statusUpdate", mapper.readValue(JsonFormat.printer().print(statusUpdate), ObjectNode.class));
            return node;
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }


}

