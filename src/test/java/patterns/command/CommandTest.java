package patterns.command;

import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandTest {
    private final Light lamp = new Light();

    private final Command switchUp = new FlipUpCommand(lamp);
    private final Command switchDown = new FlipDownCommand(lamp);

    private final Switch theSwitch = new Switch();

    @Test
    public void testSwitchUp() {
        theSwitch.storeAndExecute(switchUp);
    }

    @Test
    public void testSwitchDown() {
        theSwitch.storeAndExecute(switchDown);
    }

    @Test(expected = RuntimeException.class)
    public void testSwitchBroken() {
        theSwitch.storeAndExecute(() -> {
            throw new RuntimeException("It's broke, doge!");
        });
    }
}

/**The Command interface
 * */
@FunctionalInterface
interface Command {
    void execute();
}

/**
 * The Invoker class
 */
class Switch {
    private List<Command> history = new ArrayList<>();

    public void storeAndExecute(final Command cmd) {
        this.history.add(cmd);
        cmd.execute();
    }
}

/**The Receiver class
* */
class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}

/**
 * The Command for turning on the light
 * */
@RequiredArgsConstructor
class FlipUpCommand implements Command {
    private final Light light;

    @Override
    public void execute() {
        light.turnOn();
    }
}

/**
 * The Command for turning of the light
 * */
@RequiredArgsConstructor
class FlipDownCommand implements Command {
    private final Light light;

    @Override
    public void execute() {
        light.turnOff();
    }
}