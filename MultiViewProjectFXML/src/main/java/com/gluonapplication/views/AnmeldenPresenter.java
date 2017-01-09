/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;
import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;
import static com.gluonapplication.GluonApplication.INSCRIPTION_VIEW;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author yotti
 *
 */
public class AnmeldenPresenter {

	@FXML
	private View primary1;
	// @FXML
	// private Slider sld1;
	@FXML
	private Label AID2;
	@FXML
	private Button INA;
	@FXML
	private Icon IDX;
	//
	// @SuppressWarnings("rawtypes")
	// @FXML
	// private ComboBox AC, AB;

	public void initialize() {
		primary1.setShowTransitionFactory(BounceInRightTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU
						.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
				appBar.setTitleText("Inscription");

				appBar.setStyle("-fx-background-color: green;");

				appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
						.button(e -> MobileApplication.getInstance().switchView(PRIMARY_VIEW)));
			}

			// Image imageDecline = new
			// Image(getClass().getResourceAsStream("/icon.png"));
			Image image = new Image("/facebook-logo.png");

			INA.setGraphic(new ImageView(image));
			// IDX

			// autocompleteField1();
		});
	}

	@FXML
	public void setSlider() {
		MobileApplication.getInstance().switchView(INSCRIPTION_VIEW);

	}
	//
	// @SuppressWarnings("unchecked")
	// public void autocompleteField() {
	// ObservableList<String> items = FXCollections.observableArrayList();
	// items.addAll("Douala", "Yaounde", "Buea", "Limbe", "Kribi", "Tchang");
	// AC.setEditable(true);
	// AC.setItems(items);
	// // AC.getSelectionModel().select(0);
	// new AutoCompleteComboBoxListener<String>(AC);
	//
	// }
	//
	// @SuppressWarnings("unchecked")
	// public void autocompleteField1() {
	// ObservableList<String> items = FXCollections.observableArrayList();
	// items.addAll("Douala", "Yaounde", "Buea", "Limbe", "Kribi", "Tchang");
	// AB.setEditable(true);
	// AB.setItems(items);
	// // AC.getSelectionModel().select(0);
	// new AutoCompleteComboBoxListener<String>(AB);
	//
	// }

}
