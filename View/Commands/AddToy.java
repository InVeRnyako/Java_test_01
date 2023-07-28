package View.Commands;

import View.Console;

public class AddToy extends Command{
    Console console;
    private String desription = "Добавить игрушку";

    public AddToy(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDiscription() {
        return desription;
    }

    @Override
    public void execute() {
//        (TODO) Добавление игрушки
//        Если название совпадает с существующей игрушкой - добавить в существует
        String newToyTitle = console.read("Введите название игрушки.");
        Integer toyExists = console.CheckIfToyExists(newToyTitle);
        if (toyExists > -1){
            if (console.readBool("Игрушка с указанным названием уже существует." +
                    " Хотите увеличить количество игрушек c таким названием (y) или вернуться в меню(n)?")){
                console.changeAmount(toyExists, console.readInt("Введите количество игрушек (" + newToyTitle + ") для добавления: "));
            }
            return;
        }
        Integer newToyAmount = console.readInt("Введите количество новых игрушек: ");
        Integer newToyWeight = console.readInt("Введите вероятность выигрыша для новой игрушки (целое число): ");
        console.newToy(newToyTitle, newToyAmount, newToyWeight);
    }
}
