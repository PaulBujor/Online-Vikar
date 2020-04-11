package dk.grouptwo.view;

import javafx.scene.layout.Region;

public class SignInController {
    //todo no viewmodel, just reference to viewhandler to switch to correct controller
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, Region root) {
        this.viewHandler = viewHandler;
    }

    //todo button methods * 3
}
