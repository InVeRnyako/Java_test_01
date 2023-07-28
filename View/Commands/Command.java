package View.Commands;
import View.Console;
public abstract class Command {
    private Console console;

    public Command(Console console){
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }

    public abstract String getDiscription();
    public abstract void execute();
}
