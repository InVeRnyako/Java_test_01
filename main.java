import Model.EditPool;
import Presenter.Presenter;
import View.Console;
import View.View;

public class main {
    public static void main(String[] args) {
        View view = new Console();
        EditPool service = new EditPool();
        Presenter presenter = new Presenter(view,service);
        view.start();
    }

}
