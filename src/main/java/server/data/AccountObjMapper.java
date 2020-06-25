package server.data;

import com.example.components.StatusUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public final class AccountObjMapper extends ObjectMapper {
    public static final AccountObjMapper MAPPER = new AccountObjMapper();

    private AccountObjMapper() {
        super();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(StatusUpdate.class, new StatusUpdateSerializer());
        simpleModule.addSerializer(AccountInformation.class, new AccountInformationSerializer());
        this.registerModule(simpleModule);
    }
}
