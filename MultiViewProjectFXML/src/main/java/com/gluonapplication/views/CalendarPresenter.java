/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;
import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.inject.Inject;

import com.airhacks.afterburner.views.FXMLView;
import com.gluonapplication.GluonApplication;
import com.gluonapplication.views.Density.DENSITY;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;

import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;

/**
 * @author yotti
 *
 */

public class CalendarPresenter implements Initializable {

	@FXML
	private View primary1;
	@FXML
	javafx.scene.control.TextField textdate;
	@Inject
	private Model model;
	@Inject
	private Model3 model3;

	public void initialize(URL location, ResourceBundle resources) {

		primary1.setShowTransitionFactory(BounceInRightTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU
						.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
				appBar.setTitleText("Calendar");

				appBar.setStyle("-fx-background-color: green;");

				appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
						.button(e -> MobileApplication.getInstance().switchView(SUCHEFAHRT_VIEW)));
			}
		});

		final DatePicker datePicker = new DatePicker(LocalDate.now());
		DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
		javafx.scene.Node popupContent = datePickerSkin.getPopupContent();

		datePicker.setMaxHeight(6000.);
		datePicker.setMinHeight(6000.);
		datePicker.setPrefHeight(6000.);
		datePicker.setStyle("-fx-font-size: 5.5em;");
		datePicker.setOnAction((event) -> {
			LocalDate date = datePicker.getValue();
			System.err.println("Selected date: " + date);
			model3.setText(date+"");
			System.out.println("the is" +model3.getText());
			MobileApplication.getInstance().switchView(SUCHEFAHRT_VIEW);
			textdate.setText(date+"");
		});
		

		primary1.setCenter(popupContent);

	}

}