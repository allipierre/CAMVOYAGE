package com.gluonapplication.views;


import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.SMSService;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonapplication.GluonApplication;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class SecondaryPresenter {

    @FXML
    private View secondary;
    @FXML
    private Button btn;
    @FXML
    private TextField textField;
   
   
    public void initialize() {
        secondary.setShowTransitionFactory(BounceInRightTransition::new);
      
        
        secondary.getLayers().add(new FloatingActionButton(MaterialDesignIcon.INFO.text, 
            e -> System.out.println("Info")).getLayer());
        
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
                appBar.setTitleText("Secondary");
                appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e -> 
                        System.out.println("Favorite")));
                textField.setText("004917680501750");
                Services.get(SMSService.class).ifPresent(s -> btn.setOnAction(e -> s.sendSMS(textField.getText())));
            }
        });
        textField.setText("004917680501750");
        Services.get(SMSService.class).ifPresent(s -> btn.setOnAction(e -> s.sendSMS(textField.getText())));
    }
}
