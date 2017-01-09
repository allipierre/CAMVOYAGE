/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;
import static com.gluonapplication.GluonApplication.MODIFIERPROFIL_VIEW;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.StorageService;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.*;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author yotti
 *
 */
public class DatabaseTestPresenter {

	@FXML
	private View primary1;
	@FXML
	public ComboBox<String> ET;
	private final static String DB_NAME = "test.db";

	public void initialize() {
		primary1.setShowTransitionFactory(BounceInRightTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();

				appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> {
					MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER);
					clearComBox();

				}));

				appBar.setTitleText("Profil");

				appBar.setStyle("-fx-background-color: green;");

				appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e -> {
					MobileApplication.getInstance().switchView(PRIMARY_VIEW);
					clearComBox();

				}));

				setCombox();

			}

		});

	}

	public void setCombox() {
		Connection c = null;
		Statement stmt = null;
		String dbUrl = "jdbc:sqlite:";
		File dir;
		ResultSet rs = null;
		try {
			if (Platform.isDesktop()) {
				Class.forName("org.sqlite.JDBC");

				dir = Services.get(StorageService.class).map(s -> s.getPrivateStorage().get())
						.orElseThrow(() -> new IOException("Error: PrivateStorage not available"));
				File db = new File(dir, DB_NAME);
				DBUtils.copyDatabase("/databases/", dir.getAbsolutePath(), DB_NAME);
				dbUrl = dbUrl + db.getAbsolutePath();
				System.out.println(dbUrl);
				c = DriverManager.getConnection(dbUrl);
			} else if (Platform.isAndroid()) {
				Class.forName("org.sqldroid.SQLDroidDriver");

				try {
					dir = Services.get(StorageService.class).map(s -> s.getPrivateStorage().get())
							.orElseThrow(() -> new IOException("Error: PrivateStorage not available"));
					File db = new File(dir, DB_NAME);
					DBUtils.copyDatabase("/databases/", dir.getAbsolutePath(), DB_NAME);
					dbUrl = dbUrl + db.getAbsolutePath();
					System.out.println(dbUrl);
					c = DriverManager.getConnection(dbUrl);
				} catch (IOException ex) {

					Alert alert = new Alert(AlertType.CONFIRMATION, ex.getClass().getName());
					alert.setContentText(ex.getMessage());
					alert.showAndWait();
				} finally {
					try {
						rs.close();
						stmt.close();
						c.close();

					} catch (Exception e) {
					}
				}

			}

			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM COMPANY;");

			while (rs.next()) {

				ObservableList<String> items = FXCollections.observableArrayList();
				String name1 = rs.getString("NAME");
				// System.out.println(name1);

				items.addAll(name1);

				ET.getItems().addAll(items);

				ET.getSelectionModel().select(0);

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println("Error " + e.getClass().getName() + ": " + e.getMessage());
			//Alert alert = new Alert(AlertType.CONFIRMATION, e.getClass().getName());
			//alert.setContentText(e.getMessage());
			//alert.showAndWait();

		} finally {
			try {
				rs.close();
				stmt.close();
				c.close();

			} catch (Exception e) {
			}
		}
		System.out.println("Operation done successfully");
	}

	@FXML
	void buttonModifierProfil() {

		MobileApplication.getInstance().switchView(MODIFIERPROFIL_VIEW);

	}

	public void clearComBox() {
		ET.getItems().clear();
	}

}
