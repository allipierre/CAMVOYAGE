/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;
import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;
import static com.gluonapplication.GluonApplication.DESTINATION_VIEW;
import static com.gluonapplication.GluonApplication.ALLE_VIEW;
import java.time.LocalDate;
import java.util.Locale;
import javax.inject.Inject;
import static com.gluonapplication.GluonApplication.BASIC_VIEW;
import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.DatePicker;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * @author yotti
 *
 */
public class SucheFahrtPresenter {
	@Inject
	private Model model;
	@Inject
	private Model2 model2;
	@Inject
	private Model3 model3;
	@Inject
	private Model4 model4;

	@FXML
	private View primary1;
	@FXML
	private Slider sld1;
	@FXML
	private Label lbl5, PLACE;

	@FXML
	TextField DP, DC, Tdate;

	@FXML
	private Button CUT;
	@FXML
	private ToggleButton ADDA, SUBBA;

	@FXML
	private ToggleButtonGroup TGA;

	final Kater herbi = new Kater();

	public void initialize() {
		primary1.setShowTransitionFactory(BounceInRightTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU
						.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
				appBar.setTitleText("Votre Destination");

				appBar.setStyle("-fx-background-color: green;");

				appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
						.button(e -> MobileApplication.getInstance().switchView(PRIMARY_VIEW)));
			}

			// autocompleteField();
			// autocompleteField1();

			// DP.setOnAction((event) -> {
			// MobileApplication.getInstance().switchView(BASIC_VIEW);
			// });
		});
		setDP();
		setDP1();
		// setDP2();
		activButton();
		ADDA.setStyle("-fx-text-fill: green !important;");
		ADDA.setStyle("-fx-fill: green !important;");
		// SUBBA.setStyle("-fx-border-color: green;");

		setCountADD();
		setCountMinus();
		activButtonADD();

	}

	public void setSlider() {
		lbl5.setText((int) Math.round(sld1.getValue()) + "");
		sld1.valueProperty()
				.addListener((obs, oldval, newVal) -> sld1.setValue((int) Math.round(newVal.doubleValue())));
		// lbl5.setText((int)(newVal.intValue()) + ""));

	}

	public void setCountADD() {
		lbl5.setText(1 + "");
		herbi.setAlter(1);
		lbl5.setText(herbi.getAlter() + "");

		herbi.alterProperty().addListener((ov, alterwert, neuerwert) -> {

			if (Integer.parseInt(neuerwert.toString()) > 1) {
				lbl5.setText(neuerwert.toString());
				PLACE.setText("Places");
			} else {
				lbl5.setText(neuerwert.toString());
				PLACE.setText("Place");
			}

		});

		ADDA.setOnMouseClicked((event) -> {

			herbi.setAlter(herbi.getAlter() + 1);

		});
	}

	public void setCountMinus() {
		herbi.setAlter(1);
		lbl5.setText(herbi.getAlter() + "");

		herbi.alterProperty().addListener((ov, alterwert, neuerwert) -> {
			if (Integer.parseInt(neuerwert.toString()) > 1) {
				lbl5.setText(neuerwert.toString());
				PLACE.setText("Places");
			} else {
				lbl5.setText(neuerwert.toString());
				PLACE.setText("Place");
			}

		});

		SUBBA.setOnMouseClicked((event) -> {

			herbi.setAlter(herbi.getAlter() - 1);

		});

	}

	public void autocompleteField() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll("Douala", "Yaounde", "Buea", "Limbe", "Kribi", "Tchang");
		// AC.setEditable(true);
		// AC.setItems(items);
		// ACD.setItems(items);
		// ACD.getStyleClass().add("combo-border");
		// ACD.setMinWidth(200.0);
		// ACD.setPrefWidth(200.0);

		// AC.getSelectionModel().select(0);
		// new AutoCompleteComboBoxListener<String>(AC);

	}

	public void autocompleteField1() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll("Douala", "Yaounde", "Buea", "Limbe", "Kribi", "Tchang");
		// AB.setEditable(true);
		// AB.setItems(items);
		// AC.getSelectionModel().select(0);
		// new AutoCompleteComboBoxListener<String>(AB);

	}

	@FXML
	void buttonClick() {

		MobileApplication.getInstance().switchView(SUCHEFAHRT_VIEW);
	}

	@FXML
	void buttonClick1() {

		MobileApplication.getInstance().switchView(PRIMARY_VIEW);
	}

	@FXML
	void buttonClick2() {
		model.setText(DP.getText());
		MobileApplication.getInstance().switchView(BASIC_VIEW);
	}

	@FXML
	void buttonClick3() {
		model2.setText(DC.getText());
		MobileApplication.getInstance().switchView(DESTINATION_VIEW);
	}

	public void setDP() {

		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {

				DP.setText(model.getText());

			}
		});

	}

	public void setDP1() {

		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {

				DC.setText(model2.getText());

			}
		});

	}

	public void setDP2() {

		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {

				Tdate.setText(model3.getText());

			}
		});

	}

//	public SucheFahrtPresenter start() {
//		try {
//
//			Stage primaryStage = new Stage();
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("SucheFahrt.fxml"));
//			Parent root = loader.load();
//			Scene scene = new Scene(root);
//
//			primaryStage.setScene(scene);
//
//			primaryStage.setTitle("My Dialog");
//			primaryStage.setResizable(false);
//			primaryStage.show();
//
//			return loader.getController();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@FXML
	void openCalendar() {

		Locale.setDefault(Locale.FRANCE);
		// MobileApplication.getInstance().switchView(CALENDAR_VIEW);
		DatePicker datePicker = new DatePicker();
		datePicker.getButtons().get(0).setText("ANNULER");
		datePicker.getButtons().get(1).setText("ACCEPTER");
		datePicker.getButtons().get(0).setStyle("-fx-font-weight: bold;");
		datePicker.getButtons().get(1).setStyle("-fx-font-weight: bold;");
		datePicker.getButtons().get(1).setStyle("-fx-font-size: 14.0px;");
		datePicker.getButtons().get(0).setStyle("-fx-font-size: 14.0px;");
		datePicker.showAndWait().ifPresent(System.out::println);

		LocalDate date = datePicker.getDate();
		Tdate.setText(date + "");
		System.out.println("Selected date: " + date);

	}

	public void activButton() {
		BooleanBinding nameEntered = DP.textProperty().isNotEmpty().and(DC.textProperty().isNotEmpty());
		CUT.disableProperty().bind((nameEntered).not());

	}

	public void activButtonADD() {
		BooleanBinding nameEntered = lbl5.textProperty().isEqualTo("50");
		BooleanBinding nameEntered1 = lbl5.textProperty().isEqualTo("1");
		ADDA.disableProperty().bind((nameEntered));
		SUBBA.disableProperty().bind((nameEntered1));

	}

	@FXML
	void buttonAlle() {
		model4.setText(DP.getText() + " >> " + DC.getText());
		MobileApplication.getInstance().switchView(ALLE_VIEW);
	}

}
