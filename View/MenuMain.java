package View;
import View.Commands.*;

import java.util.ArrayList;
import java.util.List;

public class MenuMain {
    private List<Command> mainMenuList;

    public MenuMain(Console console){
        mainMenuList = new ArrayList<>();
        mainMenuList.add(new AddToy(console));
        mainMenuList.add(new EditToy(console));
        mainMenuList.add(new RemoveToy(console));
        mainMenuList.add(new ViewPool(console));
        mainMenuList.add(new Raffle(console));
        mainMenuList.add(new LoadPreLoad(console));
//        mainMenuList.add(new )
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        String prefix = "=== Главное меню. \n";
        for (int i = 0; i < mainMenuList.size(); i++) {
            stringBuilder.append(prefix);
            stringBuilder.append(i+1);
            stringBuilder.append(". ");
            stringBuilder.append(mainMenuList.get(i).getDiscription());
            prefix = "\n";
        }
        return stringBuilder.toString();
    }

    void execute(Integer choice){
        mainMenuList.get(choice-1).execute();
    }
}
