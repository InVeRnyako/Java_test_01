package View.Commands;

import View.Console;

public class LoadPreLoad extends Command{

        Console console;
        private String desription = "Загрузить демонстративный набор игрушек";

        public LoadPreLoad(Console console){
            super(console);
            this.console = console;
        }

        @Override
        public String getDiscription() {
            return desription;
        }

        @Override
        public void execute() {
            console.loadPreLoad();
        }

    }


