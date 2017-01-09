/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;
import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static com.gluonapplication.GluonApplication.INSCRIPTION_VIEW;
import static com.gluonapplication.GluonApplication.DATABASETEST_VIEW;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * @author yotti
 *
 */
public class ModifierProfilPresenter {

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
	@FXML
	private TextArea TAREA;
	@FXML
	private Label countC;
	
	@FXML
	private Label FLAGE;

	//
	// @SuppressWarnings("rawtypes")
	// @FXML
	// private ComboBox AC, AB;

	public void initialize() {
		primary1.setShowTransitionFactory(BounceInRightTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				// appBar.setNavIcon(MaterialDesignIcon.MENU
				// .button(e ->
				// MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));

				appBar.setNavIcon(MaterialDesignIcon.ARROW_BACK.button(e -> {
//					MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER);
					MobileApplication.getInstance().switchView(DATABASETEST_VIEW);

				}));

				appBar.setTitleText("Modifier votre Profil");

				appBar.setStyle("-fx-background-color: green;");

				// appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
				// .button(e ->
				// MobileApplication.getInstance().switchView(DATABASETEST_VIEW)));

//				appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e -> {
//					MobileApplication.getInstance().switchView(DATABASETEST_VIEW);
//
//				}));
			}

			// Image imageDecline = new
			// Image(getClass().getResourceAsStream("/icon.png"));
			Image image = new Image("/facebook-logo.png");
			Image image1 = new Image(getClass().getResourceAsStream("/Cameroon.png"),30,20,false,false);

			INA.setGraphic(new ImageView(image));
			FLAGE.setText("");
			FLAGE.setGraphic(new ImageView(image1));
			

			countCharacter();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void countCharacter() {
		Kater herbi = new Kater();
		herbi.setAlter(500);

		countC.setText(herbi.getAlter() + "");
		herbi.alterProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue ov, Object alterwert, Object neuerwert) {
				countC.setText(neuerwert.toString());
			}
		});

		TAREA.setOnKeyPressed((event) -> {
			herbi.setAlter(TAREA.getText().length());
		});
		TAREA.setOnKeyReleased((event) -> {
			herbi.setAlter(500 - TAREA.getText().length());
		});

		TAREA.setOnKeyTyped((event) -> {
			herbi.setAlter(500 - TAREA.getText().length());
		});

	}

}
