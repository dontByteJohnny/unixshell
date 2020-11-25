import com.mulesoft.Terminal;
import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ClientTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // mkdir -> ls
    @Test
    public void creating_a_folder_then_listing_it() {
        Command command = new Command();
        String[] userInput = "mkdir folder".split(" ");

        Terminal terminal = new Terminal(command, userInput[1]);
        terminal.executeCommand(userInput[0]);

        terminal = new Terminal(command, "");

        terminal.executeCommand("ls");

        Assert.assertTrue(command.routes.get("/home/user/folder") != null);
        Assert.assertEquals("folder\r\n", outContent.toString());
    }

    // pwd
    @Test
    public void knowing_actual_path() {
        Command command = new Command();
        String userInput = "pwd";

        Terminal terminal = new Terminal(command,"");
        terminal.executeCommand(userInput);

        Assert.assertEquals("/home/user\r\n", outContent.toString());
    }

    //cd .. -> pwd
    @Test
    public void navegating_previous_folders_then_printing_actual_path() {
        Command command = new Command();
        String[] userInput = "cd ..".split(" ");

        Terminal terminal = new Terminal(command,userInput[1]);
        terminal.executeCommand(userInput[0]);

        userInput = "pwd".split(" ");
        terminal.executeCommand(userInput[0]);

        Assert.assertEquals("/home\r\n", outContent.toString());
    }

    //mkdir -> cd folder -> pwd
    @Test
    public void creating_folder_then_navegating_it() {
        Command command = new Command();
        String[] userInput = "mkdir folder".split(" ");

        Terminal terminal = new Terminal(command,userInput[1]);
        // mkdir folder
        terminal.executeCommand(userInput[0]);

        userInput = "cd folder".split(" ");
        terminal = new Terminal(command, userInput[1]);
        //cd folder
        terminal.executeCommand(userInput[0]);

        userInput = "pwd".split(" ");
        //pwd
        terminal.executeCommand(userInput[0]);

        Assert.assertTrue(command.routes.get("/home/user/folder") != null);
        Assert.assertEquals("/home/user/folder\r\n", outContent.toString());
    }

    // touch
    @Test
    public void creating_file_then_checking_if_exists() {
        Command command = new Command();
        String[] userInput = "touch file".split(" ");

        Terminal terminal = new Terminal(command,userInput[1]);
        // touch
        terminal.executeCommand(userInput[0]);

        Assert.assertTrue(command.routes.get("/home/user/file") != null);
        Assert.assertTrue("file".equals(command.routes.get("/home/user/file").getType()));
    }

    // mkdir -> rm
    @Test
    public void creating_folder_then_removing_it() {
        Command command = new Command();
        String[] userInput = "mkdir folder".split(" ");

        Terminal terminal = new Terminal(command,userInput[1]);
        //mkdir folder
        terminal.executeCommand(userInput[0]);
        Assert.assertTrue(command.routes.get("/home/user/folder") != null);

        userInput = "rm folder".split(" ");
        //rm folder
        terminal.executeCommand(userInput[0]);
        Assert.assertTrue(command.routes.get("/home/user/folder") == null);
    }

}
