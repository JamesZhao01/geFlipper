package server.tasks;

import com.example.components.StatusUpdate;

public abstract class Operation {
    public abstract boolean run();
    public abstract boolean check(StatusUpdate statusUpdate);
}
