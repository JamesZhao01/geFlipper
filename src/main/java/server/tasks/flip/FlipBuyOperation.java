package server.tasks.flip;

import com.example.components.StatusUpdate;
import server.MainServer;
import server.data.AccountInformation;
import server.data.DataHandler;
import server.mina.CommandApi;
import server.mina.MinaBotHandler;
import server.tasks.Operation;
import server.tasks.ProcessIdFactory;
import server.tasks.TaskInformation;

public class FlipBuyOperation extends Operation {

    private FlipInformation flipInformation;
    private TaskInformation taskInformation;
    private MainServer mainServer;
    public FlipBuyOperation(MainServer mainServer, TaskInformation taskInformation, FlipInformation flipInformation) {
        this.taskInformation = taskInformation;
        this.flipInformation = flipInformation;
        this.mainServer = mainServer;
    }
    @Override
    public boolean run() {
        CommandApi commandApi = mainServer.getMinaBotServer().getApiCommand();
        try {
            commandApi.buyIncrement(taskInformation.getIdentifier().getId(), taskInformation.getBoxId(), flipInformation.getSearchTerm(), 5, 1 );
        } catch (MinaBotHandler.SessionNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean check(StatusUpdate statusUpdate) {
        DataHandler dataHandler = mainServer.getDataHandler();
        AccountInformation accountInformation = dataHandler.get(taskInformation.getIdentifier());
        /*echoed status update needs to receive the id*/
        boolean processReceived = accountInformation.getProcessId() == accountInformation.getStatusUpdate().getProcessId();
        boolean processComplete = accountInformation.getStatusUpdate().getProcessing();

        System.out.println(String.format("[FlipBuyOperation] ProcessReceived: %b ProcessCOmplete: %b", processReceived, processComplete));
        return processComplete && processComplete;
    }
}
