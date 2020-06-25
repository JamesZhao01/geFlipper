package server.tasks.flip;

import com.example.components.StatusUpdate;
import server.MainServer;
import server.data.AccountIdentifier;
import server.data.AccountInformation;
import server.tasks.Operation;
import server.tasks.ProcessIdFactory;
import server.tasks.Task;
import server.tasks.TaskInformation;

public class FlipBuyTask extends Task {

    public FlipBuyTask(AccountIdentifier accountIdentifier, MainServer mainServer, int boxId, int itemId, String searchTerm) {
        super(accountIdentifier, mainServer);
        Operation[] operations = new FlipBuyOperation[1];
        TaskInformation taskInformation = new TaskInformation(accountIdentifier, boxId);
        FlipInformation flipInformation = new FlipInformation(itemId, searchTerm);
        operations[0] = new FlipBuyOperation(mainServer, taskInformation, flipInformation);
        setOperations(operations);
    }
}
