package View.Commands;

import View.Console;

import java.util.ArrayList;

public class ViewPool extends Command{

    Console console;
    private String desription = "Просмотреть все варианты выигрыша";

    public ViewPool(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDiscription() {
        return desription;
    }

    @Override
    public void execute() {
        ArrayList<String> viewToys = new ArrayList<>();
        viewToys = console.viewAll();
        for (String s: viewToys) {
            console.print(s);
        }
    }

}
