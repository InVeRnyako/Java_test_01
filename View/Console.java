package View;

import Presenter.Presenter;

import java.util.ArrayList;
import java.util.Scanner;
public class Console implements View{
    private Scanner scanner;
    private MenuMain mainMenu;
    private Presenter presenter;
    private Boolean work = true;
    String reading = "";

    public Console(){
        scanner = new Scanner(System.in);
        mainMenu = new MenuMain(this);

    }


    @Override
    public void print(String outString) {
        System.out.println(outString);
    }

    @Override
    public void start() {
        while(work){
            print(mainMenu.print());
            Integer choice = readInt("Выберите действие: ");
            mainMenu.execute(choice);
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public String read(String msg) {
        Boolean noCorrect = true;
        reading = "";
        while (noCorrect) {
            print(msg);
            reading = scanner.nextLine();
            if (reading.isBlank())
                print("Ошибка ввода данных");
            else
                noCorrect = false;
        }
        return reading;
    }

    public String readAcceptNothing(String msg){
        print(msg);
        return scanner.nextLine();
    }

    public Integer readInt(String msg){
        while(true){
            print(msg);
            reading = scanner.nextLine();
            if (reading.replaceAll("[^0-9]","").length() == reading.length()){
                return Integer.parseInt(reading);
            }
            print("Ошибка ввода данных");
        }
    }

    public Boolean readBool(String msg){
        reading = "";
        while(true){
            print(msg);
            reading = scanner.nextLine();
            if (reading.toLowerCase().equals("y"))
                return true;
            else if (reading.toLowerCase().equals("n")) {
                return false;
            }
            print("Ошибка ввода данных");
        }
    }

    public Integer CheckIfToyExists(String newToyName){
        return presenter.checkIfToyExists(newToyName);
    }

    public void changeAmount(Integer id, Integer change) {
        presenter.changeAmount(id, change);
    }

    public void newToy(String newToyTitle, Integer newToyAmount, Integer newToyWeight) {
        presenter.newToy(newToyTitle,newToyAmount,newToyWeight);
    }

    public String getToyFullInfo(Integer toyId){
        return presenter.getToyFullInfo(toyId);
    }

    public void editToy(Integer toyId, String newTitle, String newAmount, String newWeight) {
        presenter.editToy(toyId, newTitle, newAmount, newWeight);
    }

    public ArrayList<String> raffleWeighted(Integer amountToRaffle) {
        return presenter.raffleWeighted(amountToRaffle);
    }

    public void deleteToy(Integer toyId) {
        presenter.deleteToy(toyId);
    }

    public void loadPreLoad() {
        presenter.loadPreload();
    }

    public ArrayList<String> viewAll() {
        return presenter.viewAll();
    }
}
