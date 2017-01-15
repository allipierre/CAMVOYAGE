package com.gluonapplication;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import static com.gluonapplication.GluonApplication.MENU_LAYER;
import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;
import static com.gluonapplication.GluonApplication.SECONDARY_VIEW;
import static com.gluonapplication.GluonApplication.SUCHEFAHRT_VIEW;
import static com.gluonapplication.GluonApplication.DETAILSSUCH_VIEW;

import com.gluonapplication.views.DetailsSuchView;

import static com.gluonapplication.GluonApplication.ANMELDEN_VIEW;
import static com.gluonapplication.GluonApplication.DATABASETEST_VIEW;
import javafx.scene.Node;
import javafx.scene.image.Image;


public class DrawerManager {
	 private final static String DB_NAME = "test.db";
    private final NavigationDrawer drawer;

    public DrawerManager() {
        this.drawer = new NavigationDrawer();
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("CAM VOYAGE",
                "Voyagez moins cher en covoiturage",
                new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);
        
        final Item primaryItem = new ViewItem("Home", MaterialDesignIcon.HOME.graphic(), PRIMARY_VIEW, ViewStackPolicy.SKIP);
        final Item secondaryItem = new ViewItem("Secondary", MaterialDesignIcon.DASHBOARD.graphic(), SECONDARY_VIEW);
        final Item suchefahrt = new ViewItem("Chercher un trajet", MaterialDesignIcon.DASHBOARD.graphic(), SUCHEFAHRT_VIEW);
        final Item anmelden = new ViewItem("Inscription", MaterialDesignIcon.DASHBOARD.graphic(), ANMELDEN_VIEW);
        final Item databasetest = new ViewItem("Profil", MaterialDesignIcon.DASHBOARD.graphic(), DATABASETEST_VIEW);
//        final Item detailssuche = new ViewItem("detils", MaterialDesignIcon.DASHBOARD.graphic(), DETAILSSUCH_VIEW);
        
        
        drawer.getItems().addAll(primaryItem, secondaryItem,anmelden,suchefahrt,databasetest);
//        drawer.getItems().get(3).setVisible(false);
        
        
        
        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }
        
        drawer.addEventHandler(NavigationDrawer.ITEM_SELECTED, 
                e -> MobileApplication.getInstance().hideLayer(MENU_LAYER));
        
        MobileApplication.getInstance().viewProperty().addListener((obs, oldView, newView) -> updateItem(newView.getName()));
        updateItem(PRIMARY_VIEW);
    }
    
    private void updateItem(String nameView) {
    	
        for (Node item : drawer.getItems()) {
            if (item instanceof ViewItem && ((ViewItem) item).getViewName().equals(nameView)) {
                drawer.setSelectedItem(item);
                break;
            }
        }
    }
    
    public NavigationDrawer getDrawer() {
        return drawer;
    }
}
