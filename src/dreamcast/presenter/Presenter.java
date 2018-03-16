package dreamcast.presenter;

import dreamcast.model.Model;
import dreamcast.view.ViewController;

public class Presenter {

    private ViewController view;

    private Model model;

    public Model getModel() {
        return model;
    }

    public ViewController getView() {
        return view;
    }

    public void setView(ViewController viewControler) {
        this.view = viewControler;
        viewControler.initView();
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
