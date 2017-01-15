package com.gluonapplication.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import com.gluonapplication.GluonApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;
import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;

public class PrimaryPresenter {

	@FXML
	private View primary;
	@FXML
	VBox tx;

	@FXML
	private Label label;

	@FXML
	private Button BTEST;

	public void initialize() {

		primary.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU
						.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
				appBar.setTitleText("CAMVOYAGE");

				appBar.setStyle("-fx-background-color: green;");

				appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
			}
		});
		

	}

	@FXML
	void buttonClick() {
		MobileApplication.getInstance().switchView(SUCHEFAHRT_VIEW);
	}

	@FXML
	void buttonClick1() {

		MobileApplication.getInstance().switchView(PRIMARY_VIEW);
	}

}
