package Model;


import Model.Elements.ToyItem;
import Presenter.Presenter;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EditPool {
    Pool<ToyItem> pool;
    Presenter presenter;
    DataLogger dataLogger;

    public void resetPool(){
        pool = new Pool<>();
        pool.getData().add(new ToyItem(1, "Игрушка №1", 30, 400));
        pool.getData().add(new ToyItem(2, "Игрушка №2", 100, 50));
        pool.getData().add(new ToyItem(3, "Игрушка №3", 50, 100));
        pool.getData().add(new ToyItem(4,"Игрушка №4", 100, 200));
    }
    public void editToy(Integer toyId, String newTitle, String newAmount, String newWeight) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getId() == toyId){
                if (!newTitle.isBlank())
                    toy.setTitle(newTitle);
                if (!newAmount.replaceAll("[^0-9]","").isBlank()){
                    toy.setAmount(Integer.parseInt(newAmount.replaceAll("[^0-9]","")));
                }
                if (!newWeight.replaceAll("[^0-9]","").isBlank()){
                    toy.setWeight(Integer.parseInt(newWeight.replaceAll("[^0-9]","")));
                }
                return;
            }
        }
        return;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        this.dataLogger = new DataLogger(presenter);
    }
    public EditPool(Pool pool){
        this.pool = pool;
    }

    public EditPool(){
        this.pool = new Pool<>();
    }

    public String showAll(){
        StringBuilder outputString =  new StringBuilder();
        String prefix = "";
        for (ToyItem t: pool.getData()) {
            outputString.append(prefix);
            outputString.append(t);
        }
        return outputString.toString();
    }
    public void addToy(ToyItem newToyItem){
        if (newToyItem == null)
            return;
        pool.getData().add(newToyItem);
    }
    public Integer checkIfToyExists(String newToyName) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getTitle().compareToIgnoreCase(newToyName) == 0)
                return toy.getId();
        }
        return -1;
    }
    public void changeAmount(Integer id, Integer change) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getId() == id) {
                toy.setAmount(toy.getAmount() + change);
                if (toy.getAmount() < 0)
                    toy.setAmount(0);
            }
        }
    }
    public void newToy(String newToyTitle, Integer newToyAmount, Integer newToyWeight) {
        addToy(new ToyItem(GetNewId(), newToyTitle, newToyAmount, newToyWeight));
    }



    public Integer tryRemoveToys(Integer id, Integer toRemove){
        for (ToyItem toy: pool.getData()) {
            if (toy.getId() == id){
                if (toy.getAmount() >= toRemove){
                    changeAmount(id,toRemove * -1);
                    return 0; // Количество игрушек успешно уменьшено
                }
                else
                    return toy.getAmount();
            }
        }
        return -1; // неверный id
    }

    public Integer getTotalWeight(){
        Integer totalWeight = 0;
        for (ToyItem toy: pool.getData()) {
            totalWeight = totalWeight + (toy.getWeight() * toy.getAmount());
        }
        return totalWeight;
    }

    public ArrayList<String> raffleWeighted(Integer amountOfIds){
        ArrayList<String> winList = new ArrayList<>();
        ArrayList<String> winnersForLog = new ArrayList<>();
        Integer randomNumber; // Случайное число среди суммарного веса
        Double chanceToWin = 0.0;
        for (int i = 0; i < amountOfIds; i++) {
            randomNumber = ThreadLocalRandom.current().nextInt(1,getTotalWeight() + 1);
            for (ToyItem toy: pool.getData()) {
                if (randomNumber <= toy.getWeight() * toy.getAmount()){
                    winList.add(toy.toString());
                    chanceToWin = (double) (getTotalWeight()) / (double) (toy.getWeight() * toy.getAmount());
//                    winnersForLog.add(toy.toString() + ". Шанс выигрыша: " + Math.round(), 2) )
                    winnersForLog.add(String.format("%s. Шанс выигрыша: %.2f", toy.toString(), chanceToWin));
                    changeAmount(toy.getId(),-1);
                    break;
                } else {
                    randomNumber = randomNumber - toy.getWeight() * toy.getAmount();
                }
            }
        }
        logWinners(winnersForLog);
        return winList;
    }

    public String raffleOneWeighted(){
        return raffleWeighted(1).get(0);
    }

    public Integer GetNewId(){
        Integer id = -1;
        for (ToyItem toy: pool.getData()) {
            if (toy.getId() > id)
                id = toy.getId();
        }
        id = id + 1;
        return id;
    }

    public void logWinners(ArrayList<String> winnersList){
        dataLogger.saveData(winnersList);
    }

    public String getToyFullInfo(Integer toyId) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getId() == toyId)
                return toy.getFullInfo();
        }
        return "Ошибка";
    }

    public void deleteToy(Integer toyId) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getId() == toyId){
                pool.getData().remove(toy);
            }
        }

    }

    public ArrayList<String> viewAll() {
        ArrayList<String> outputList = new ArrayList<>();
        for (ToyItem toy: pool.getData()) {
            outputList.add(toy.getFullInfo());
        }
        return outputList;
    }
}
