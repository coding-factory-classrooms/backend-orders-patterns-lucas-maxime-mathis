import org.example.models.Command;
import org.example.models.CommandSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandSystemTest {

    private CommandSystem commandSystem;

    @Before
    public void before() {
        commandSystem = new CommandSystem();
    }

    @Test
    public void addCommandSuccess(){
        commandSystem.addCommand(new Command());
        Assert.assertEquals(1,commandSystem.getCommands().size());
    }

    @Test
    public void addCommandNotNewFail(){
        Command command = new Command();
        command.setState(Command.State.CANCELED);

        commandSystem.addCommand(command);
        Assert.assertEquals(0,commandSystem.getCommands().size());
    }
}
