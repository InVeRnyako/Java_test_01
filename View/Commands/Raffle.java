package View.Commands;

import View.Console;

import java.util.ArrayList;

public class Raffle extends Command {
    Console console;
    private String desription = "Запустить розыгрыш";

    public Raffle(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDiscription() {
        return desription;
    }

    @Override
    public void execute() {
        ArrayList<String> winList = new ArrayList<>();
        winList = console.raffleWeighted(console.readInt("Введите количество разыгрываемых игрушек: "));
        if (winList.size()>0){
            console.print("Список выигравших игрушек в порядке выигрыша:");
            for (String win: winList) {
                console.print(win);
            }
            console.print("===Конец списка===");
        }
    }
}
