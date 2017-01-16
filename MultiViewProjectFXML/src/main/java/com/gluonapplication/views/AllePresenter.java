/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.DETAILSSUCH_VIEW;
import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;

import java.io.IOException;
import java.net.URL;
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
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;

import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;

/**
 * @author yotti
 *
 */

public class AllePresenter implements Initializable {
	@Inject
	private Model model;

	@Inject
	private Model4 model4;

	private FilteredList<AlleUSState> filteredList;
	@FXML
	private CharmListView<AlleUSState, DENSITY> charmListView;
	private boolean ascending = true;

	@FXML
	private View primary1;
	AppBar appBar;

	public void initialize(URL location, ResourceBundle resources) {
		primary1.getStylesheets().add(GluonApplication.class.getResource("alle.css").toExternalForm());
		primary1.setShowTransitionFactory(BounceInUpTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.ARROW_BACK
						.button(e -> MobileApplication.getInstance().switchView(SUCHEFAHRT_VIEW)));

				appBar.setStyle("-fx-background-color: green;");

				// appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
				// .button(e ->
				// MobileApplication.getInstance().switchView(SUCHEFAHRT_VIEW)));
			}

			filteredList = new FilteredList<>(AlleUSStates.statesList, getStatePredicate(null));
			FilteredList<AlleUSState> filteredData = new FilteredList<>(AlleUSStates.statesList, s -> true);
			// charmListView = new CharmListView<>(filteredList);
			charmListView.setItems(filteredList);
			charmListView.setHeadersFunction(Density::getDensity);
			charmListView.setCellFactory(p -> new AlleUSStateCell());

			charmListView.setHeaderCellFactory(p -> new CharmListCell<AlleUSState>() {

				private final ListTile tile = new ListTile();

				{
					// Avatar avatar = new Avatar(18, AlleUSStates.getUSFlag());
					// tile.setPrimaryGraphic(avatar);
					setText(null);
					System.out.println();
				}

				@Override
				public void updateItem(AlleUSState item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null && !empty) {
						tile.textProperty().setAll("Density", charmListView.toString(item));
						setGraphic(tile);
					} else {
						setGraphic(null);
					}
				}

			});
			charmListView.setConverter(new StringConverter<DENSITY>() {

				@Override
				public String toString(DENSITY d) {
					return "From " + ((int) d.getIni()) + " up to " + ((int) d.getEnd()) + " pop/km" + "\u00B2";
				}

				@Override
				public DENSITY fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});

			// 2. Set the filter Predicate whenever the filter changes.

		});
		setDP2();
		test();

	}

	private Predicate<AlleUSState> getStatePredicate(Double population) {
		return state -> population == null || state.getPopulation() >= population * 1_000_000;
	}

	private List<MenuItem> buildFilterMenu() {
		final List<MenuItem> menu = new ArrayList<>();

		EventHandler<ActionEvent> menuActionHandler = e -> {
			MenuItem item = (MenuItem) e.getSource();
			Double population = (Double) item.getUserData();
			filteredList.setPredicate(getStatePredicate(population));
		};

		ToggleGroup toggleGroup = new ToggleGroup();

		RadioMenuItem allStates = new RadioMenuItem("All States");
		allStates.setOnAction(menuActionHandler);
		allStates.setSelected(true);
		menu.add(allStates);
		toggleGroup.getToggles().add(allStates);

		List<Double> items = Arrays.asList(0.5, 1.0, 2.5, 5.0);
		for (Double d : items) {
			RadioMenuItem item = new RadioMenuItem("Population > " + d + "M");
			item.setUserData(d);
			item.setOnAction(menuActionHandler);
			menu.add(item);
			toggleGroup.getToggles().add(item);
		}

		return menu;
	}

	public void setDP2() {

		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {

			if (newValue) {

				appBar.setTitleText(model4.getText());

			}
		});

	}

	public void test() {
		charmListView.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			if (newSelection != null) {
				model4.setText(newSelection.getName() + " >> " + newSelection.getName());
			}

			MobileApplication.getInstance().switchView(DETAILSSUCH_VIEW);

		});
	}

}