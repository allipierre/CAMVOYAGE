/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;
import static com.gluonapplication.GluonApplication.ALLE_VIEW;

import java.net.URL;

import java.util.ResourceBundle;

import javax.inject.Inject;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;

import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * @author yotti
 *
 */

public class DetailsSuchPresenter implements Initializable {

	@FXML
	private View primary1;
	AppBar appBar;
	@Inject
	private Model4 model4;
	@FXML
	private Label LID, SID, DID, SID1, DID1, SA, DTA, DTB, NDP, CADA, ID_B, ID_E, ID_C;
	@FXML
	private TextArea ID_D;

	public void initialize(URL location, ResourceBundle resources) {
		primary1.getStylesheets().add(GluonApplication.class.getResource("detailssuch.css").toExternalForm());
		primary1.setShowTransitionFactory(BounceInRightTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.ARROW_BACK
						.button(e -> MobileApplication.getInstance().switchToPreviousView()));

				appBar.setStyle("-fx-background-color: green;");

				// appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
				// .button(e ->
				// MobileApplication.getInstance().switchToPreviousView()));
			}
		});
		setDP2();
		setLabel();

	}

	public void setDP2() {

		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {

			if (newValue) {

				appBar.setTitleText(model4.getText());

			}
		});

	}

	public void setLabel() {
		LID.setText("Aujourd'hui - 20:00");
		SID.setText("Douala");
		DID.setText("Yaounde");
		SID1.setText("Douala");
		DID1.setText("Yaounde");
		SA.setText("Bastos");
		DTA.setText("20:00");
		DTB.setText("~" + "03:40");
		NDP.setText("3 Place(s) disponibles(s)");
		CADA.setText("Contribution a donner");
		ID_B.setText("au conducteur");
		ID_E.setText("3000FCFA");
		ID_C.setText("COMMENTAIRE DE ALLI P");
		ID_D.setText("Je vais deux Jours a Paris du 08/01 au 10/01");
		
		
	}
	
	

}