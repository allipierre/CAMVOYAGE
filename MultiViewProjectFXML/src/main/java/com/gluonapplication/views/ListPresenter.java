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
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonapplication.views.Density.DENSITY;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
public class ListPresenter {

	@FXML
	private View primary1;

	public void initialize() {
		primary1.setShowTransitionFactory(BounceInRightTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU
						.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
				appBar.setTitleText("Chercher");

				appBar.setStyle("-fx-background-color: green;");

				appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
						.button(e -> MobileApplication.getInstance().switchView(PRIMARY_VIEW)));
			}

		});
	}

}
