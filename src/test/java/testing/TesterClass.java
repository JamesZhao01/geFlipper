package testing;

import com.example.components.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.junit.Test;
import server.data.AccountIdentifier;
import server.data.AccountInformation;
import server.data.AccountObjMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TesterClass {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Box box = Box.newBuilder().setStatus(Box.BoxStatus.PENDING_BUY).setItem(Item.newBuilder().setId(1).setName("asdf").build()).setBoxNum(2).setAmountRemaining(2).setAmountSpent(2).setAmountTotal(3).setAmountTraded(3).build();
        String str = JsonFormat.printer().print(box);
        System.out.println(str);
    }

    @Test
    public void testObjectMapping() throws InvalidProtocolBufferException {

        Map<AccountIdentifier, AccountInformation> entries = new HashMap<AccountIdentifier, AccountInformation>();
        AccountIdentifier dummyAccountIdentifier = new AccountIdentifier(123, "asdf");
        Account dummyAcount = Account.newBuilder().setName("asdf").setId(123).build();
        Item dummyItem = Item.newBuilder().setName("dummy item").setId(1336).build();
        InventoryItem dummyInventoryItem = InventoryItem.newBuilder().setItem(dummyItem).setCount(11).build();
        Inventory dummyInventory = Inventory.newBuilder().addItems(dummyInventoryItem).build();

        Box.BoxStatus dummyBoxStatus = Box.BoxStatus.EMPTY;
        Item dummyItem2 = Item.newBuilder().setId(1111).setName("stupid").build();
        Box dummyBox = Box.newBuilder().setBoxNum(22).setAmountRemaining(0).setAmountSpent(0).setAmountTotal(0).setAmountTraded(0)
                .setItem(dummyItem2).setItemPrice(333333).setStatus(dummyBoxStatus).build();

        int processId = 1111;
        boolean processing = false;

        StatusUpdate statusUpdate = StatusUpdate.newBuilder().setInv(dummyInventory).setProcessId(processId).setProcessing(false)
                .setAccount(dummyAcount).addBox(dummyBox).build();

        AccountInformation accountInformation = new AccountInformation(statusUpdate, 123, true);
//        Set<Map.Entry<AccountIdentifier, AccountInformation>> entries =mainServer.fetchAll();
        entries.put(dummyAccountIdentifier, accountInformation);
        Set<Map.Entry<AccountIdentifier, AccountInformation>> dataEntries = entries.entrySet();

        JsonNode info = AccountObjMapper.MAPPER.valueToTree(accountInformation);
        JsonNode iden = AccountObjMapper.MAPPER.valueToTree(dummyAccountIdentifier);
        ArrayNode arrayNode = AccountObjMapper.MAPPER.createArrayNode();
        ObjectNode objNode = AccountObjMapper.MAPPER.createObjectNode();
        objNode.putPOJO("iden", iden);
        objNode.putPOJO("info", info);
        arrayNode.add(objNode);
        System.out.println(arrayNode.toString());
        System.out.println(JsonFormat.printer().includingDefaultValueFields().print(statusUpdate));


    }

    @Test
    public void testSerialize() {
        ObjectMapper mapper = new ObjectMapper();
        AccountIdentifier ai = new AccountIdentifier(123, "asdf");
        ObjectNode res = mapper.convertValue(ai, ObjectNode.class);
        ObjectNode node = mapper.createObjectNode();
        node.set("asdf", res);
        System.out.println(node.toString());
        ObjectNode node2 = mapper.createObjectNode();
        node2.put("asdf", res.toString());
        System.out.println(node2.toString());

    }

    @Test
    public void testCustomSerializer() throws JsonProcessingException {
        class TestItem {
            public int num;
            public String word;
            public int nerd = 1444;
        }
        class SubTestItem {
            public int a1;
            public String a2;
            public int nerd;
        }

        class SubItemSerializer extends StdSerializer<SubTestItem> {
            public SubItemSerializer() {
                this(null);
            }

            public SubItemSerializer(Class<SubTestItem> t) {
                super(t);
            }
            @Override
            public void serialize(SubTestItem subTestItem, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("doot", 1233);
                jsonGenerator.writeEndObject();
            }
        }
        class ItemSerializer extends StdSerializer<TestItem> {

            public ItemSerializer() {
                this(null);
            }

            public ItemSerializer(Class<TestItem> t) {
                super(t);
            }

            @Override
            public void serialize(
                    TestItem value, JsonGenerator jgen, SerializerProvider provider)
                    throws IOException, JsonProcessingException {

                jgen.writeStartObject();
                jgen.writeNumberField("id", 1);
                jgen.writeStringField("itemName", "asdf");
                jgen.writeObjectField("sub", new SubTestItem());
                jgen.writeEndObject();
            }
        }
        ObjectMapper om = new ObjectMapper();
        SimpleModule mod = new SimpleModule();
        mod.addSerializer(TestItem.class, new ItemSerializer());
        mod.addSerializer(SubTestItem.class, new SubItemSerializer());
        om.registerModule(mod);
        TestItem ti = new TestItem();
        ti.num=123;
        ti.word="asdf";
        System.out.println(om.writeValueAsString(ti));
    }
}
