package logger;

import common.Globals;
import org.osbot.rs07.script.Script;

import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ScriptLoggerHandler extends Handler {

    private Script script;
    public ScriptLoggerHandler(Script script) {
        this.script = script;
    }
    @Override
    public void publish(LogRecord logRecord) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(logRecord.getLoggerName())
                .append("#").append(logRecord.getSourceMethodName()).append("][")
                .append(logRecord.getLevel())
                .append("]")
                .append(logRecord.getMessage());
        script.log(sb.toString());
    }


    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
