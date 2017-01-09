package com.gluonapplication;

import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.injection.Injector;
import com.gluonapplication.views.AlleView;
import com.gluonapplication.views.AnmeldenView;
import com.gluonapplication.views.BasicView;
import com.gluonapplication.views.CalendarView;
import com.gluonapplication.views.DatabaseTestView;
import com.gluonapplication.views.DestinationView;
import com.gluonapplication.views.InscriptionView;
import com.gluonapplication.views.ModifierProfilView;
import com.gluonapplication.views.NoteListView;
import com.gluonapplication.views.PrimaryView;
import com.gluonapplication.views.SecondaryView;
import com.gluonapplication.views.SucheFahrtView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.Swatch;


import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GluonApplication extends MobileApplication {

	public static final String PRIMARY_VIEW = HOME_VIEW;
	public static final String SECONDARY_VIEW = "Secondary View";
	public static final String MENU_LAYER = "Side Menu";
	public static final String SUCHEFAHRT_VIEW = "SucheFahrt View";
	public static final String ANMELDEN_VIEW = "Anmelden View";
	public static final String INSCRIPTION_VIEW = "Inscription View";
	public static final String DATABASETEST_VIEW = "DatabaseTest View";
	public static final String MODIFIERPROFIL_VIEW = "ModifierProfil View";
	public static final String BASIC_VIEW = "Basic View";
	public static final String DESTINATION_VIEW = "Destination View";
	public static final String CALENDAR_VIEW = "Calendar View";
	public static final String ALLE_VIEW = "Alle View";
	public static final String DETAILSSUCH_VIEW = "DetailsSuch View";

	@Override
	public void init() {
		
		addViewFactory(PRIMARY_VIEW, () -> (View)new PrimaryView().getView());
		addViewFactory(SECONDARY_VIEW, () -> new SecondaryView(SECONDARY_VIEW).getView());
		addViewFactory(SUCHEFAHRT_VIEW, () -> (View) new SucheFahrtView().getView());
		addViewFactory(ANMELDEN_VIEW, () -> new AnmeldenView(ANMELDEN_VIEW).getView());
		addViewFactory(INSCRIPTION_VIEW, () -> new InscriptionView(INSCRIPTION_VIEW).getView());
		addViewFactory(DATABASETEST_VIEW, () -> new DatabaseTestView(DATABASETEST_VIEW).getView());
		addViewFactory(MODIFIERPROFIL_VIEW, () -> new ModifierProfilView(MODIFIERPROFIL_VIEW).getView());
		addViewFactory(BASIC_VIEW, () -> (View) new BasicView().getView());
		addViewFactory(DESTINATION_VIEW, () -> (View) new DestinationView().getView());
		addViewFactory(CALENDAR_VIEW, () -> (View) new CalendarView().getView());
		addViewFactory(ALLE_VIEW, () -> (View) new AlleView().getView());
		addViewFactory(DETAILSSUCH_VIEW, () -> (View) new com.gluonapplication.views.DetailsSuchView().getView());
		
		Map<Object, Object> context = new HashMap<>();
        
		Injector.setConfigurationSource( context::get );
		
		addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
		// MobileApplication.getInstance().getGlassPane().setStyle("fx-background-color:
		// #FFFFFF;");
		
		
	}

	@Override
	public void postInit(Scene scene) {
		Swatch.TEAL.assignTo(scene);

		scene.getStylesheets().add(GluonApplication.class.getResource("style.css").toExternalForm());
		// scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Indie+Flower");
		((Stage) scene.getWindow()).getIcons().add(new Image(GluonApplication.class.getResourceAsStream("/icon.png")));
	}

}
