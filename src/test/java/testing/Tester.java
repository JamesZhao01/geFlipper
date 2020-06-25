package testing;

import com.example.components.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import org.junit.Test;
import server.data.AccountIdentifier;
import server.data.AccountInformation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tester {
    @Test
    public void testMethod() {
        Account a = Account.newBuilder().setId(1).setName("asdf").build();
        System.out.println(new JsonFormat().printToString(a));
    }

    @Test
    public void testDummyAccount() {
        Map<AccountIdentifier, AccountInformation> entries = new HashMap<AccountIdentifier, AccountInformation>();
        AccountIdentifier dummyAccountIdentifier = new AccountIdentifier(123, "asdf");
        Account dummyAcount = Account.newBuilder().setName("asdf").setId(123).build();
        Item dummyItem = Item.newBuilder().setName("dummy item").setId(1336).build();
        InventoryItem dummyInventoryItem = InventoryItem.newBuilder().setItem(dummyItem).setCount(11).build();
        Inventory dummyInventory = Inventory.newBuilder().addItems(dummyInventoryItem).build();

        Box.BoxStatus dummyBoxStatus = Box.BoxStatus.EMPTY;
        Item dummyItem2 = Item.newBuilder().setId(1111).setName("stupid").build();
        Box dummyBox = Box.newBuilder().setBoxNum(22).setAmountRemaining(2).setAmountSpent(3).setAmountTotal(43).setAmountTraded(3)
                .setItem(dummyItem2).setItemPrice(333333).setStatus(dummyBoxStatus).build();

        int processId = 1111;
        boolean processing = false;

        StatusUpdate statusUpdate = StatusUpdate.newBuilder().setInv(dummyInventory).setProcessId(processId).setProcessing(false)
                .setAccount(dummyAcount).addBox(dummyBox).build();

        AccountInformation accountInformation = new AccountInformation(statusUpdate, 123, true);

        System.out.println(accountInformation.getJacksonSerialization(new ObjectMapper()));
//        Set<Map.Entry<AccountIdentifier, AccountInformation>> entries =mainServer.fetchAll();
        entries.put(dummyAccountIdentifier, accountInformation);
        Set<Map.Entry<AccountIdentifier, AccountInformation>> dataEntries = entries.entrySet();


    }
}
