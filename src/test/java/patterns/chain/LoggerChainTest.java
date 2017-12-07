package patterns.chain;

import org.junit.Test;

public class LoggerChainTest {
    /**
     * all messages must be processed by at least one system object;
     * */
    @Test
    public void test() {
        // Build the chain of responsibility
        Logger logger, logger1,logger2;
        logger = new StdoutLogger(Logger.Priority.DEBUG);
        logger1 = logger.setNext(new EmailLogger(Logger.Priority.NOTICE));
        logger2 = logger1.setNext(new StderrLogger(Logger.Priority.ERR));

        // Handled by StdoutLogger
        logger.message("Entering function y.", Logger.Priority.DEBUG);
        System.out.println();
        // Handled by StdoutLogger and EmailLogger
        logger.message("Step1 completed.", Logger.Priority.NOTICE);
        System.out.println();
        // Handled by all three loggers
        logger.message("An error has occurred.", Logger.Priority.ERR);
    }
}

abstract class Logger {
    enum Priority {
        ERR(3), NOTICE(5), DEBUG(7);
        int val;

        Priority(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
    protected Priority mask;

    // The next element in the chain of responsibility
    protected Logger next;

    public Logger setNext(Logger log) {
        next = log;
        return log;
    }

    public final void message(String msg, Priority priority) {
        if (priority.getVal() <= mask.getVal()) {
            writeMessage(msg);
        }
        if (next != null) {
            next.message(msg, priority);
        }
    }

    abstract protected void writeMessage(String msg);
}

class StdoutLogger extends Logger {
    public StdoutLogger(Priority mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Writing to stdout: " + msg);
    }
}

class EmailLogger extends Logger {
    public EmailLogger(Priority mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Sending via email: " + msg);
    }
}

class StderrLogger extends Logger {
    public StderrLogger(Priority mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Sending to stderr: " + msg);
    }
}
