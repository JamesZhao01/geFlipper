package controller.operations;

import com.example.components.StatusUpdate;
import controller.GrandExchangeAPI;
import logger.ScriptLogger;
import org.junit.Test;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class OperationRunner {
    /*

        server places an order with id

        client flags bot as busy - cannot send to bot again
        client receives it

        sets current/pastid = id, flag processing
        once completed, deflag processing

     */
    private boolean running = false;

    private ScriptLogger scriptLogger;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public OperationRunner(){
        scriptLogger = new ScriptLogger("[OperationRunner]");
    }
    public synchronized void submit(Runnable r) {
        if(running == true) {
            throw new RuntimeException("Operation already running!");
        }
        running = true;
        scriptLogger.i("received runnable");
        Future f = executor.submit(r);
        try {
            Object obj = f.get();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
