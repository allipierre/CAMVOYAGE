/**
 * 
 */
package com.gluonapplication.views;

import static com.gluonapplication.GluonApplication.PRIMARY_VIEW;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.StorageService;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author yotti
 *
 */
public class InscriptionPresenter {

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
	private TextField NAME_D;
	//
	// @SuppressWarnings("rawtypes")
	// @FXML
	// private ComboBox AC, AB;

	public void initialize() {
		primary1.setShowTransitionFactory(BounceInUpTransition::new);
		primary1.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU
						.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
				appBar.setTitleText("Inscription");

				appBar.setStyle("-fx-background-color: green;");

				// appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK
				// .button(e ->
				// MobileApplication.getInstance().switchView(PRIMARY_VIEW)));
			}

			// Image imageDecline = new
			// Image(getClass().getResourceAsStream("/icon.png"));
			Image image = new Image("/facebook-logo.png");

			INA.setGraphic(new ImageView(image));
			// IDX

			// autocompleteField1();
		});
	}

	// public void setSlider() {
	// lbl5.setText(sld1.getValue() + "");
	// sld1.valueProperty().addListener((obs, oldval, newVal) ->
	//
	// lbl5.setText(newVal.intValue() + ""));
	//
	// }
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

	public void saveData() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/databases/test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO COMPANY (NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES ('Paul', 32, 'Yaounde', 20000.00 );";
			stmt.executeUpdate(sql);
			

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	private byte[] getByteArrayFromFile(String filePath) {
		byte[] result = null;
		FileInputStream fileInStr = null;
		try {
			File imgFile = new File(filePath);
			fileInStr = new FileInputStream(imgFile);
			long imageSize = imgFile.length();

			if (imageSize > Integer.MAX_VALUE) {
				return null; // image is too large
			}

			if (imageSize > 0) {
				result = new byte[(int) imageSize];
				fileInStr.read(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInStr.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	public void addImageToDB() {
		Connection c = null;
		String imageName="src/main/resources/prof.png";
		String s=NAME_D.getText();
		String query = "INSERT INTO COMPANY(NAME,AGE,ADDRESS,SALARY,bild) VALUES (?, 32, 'Yaounde', 20000.00,?)";
		PreparedStatement prepStmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/databases/test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			c.setAutoCommit(false);
			prepStmt = c.prepareStatement(query);
			prepStmt.setString(1, s); 
			byte[] imageFileArr = getByteArrayFromFile(imageName);
			prepStmt.setBytes(2, imageFileArr);

			prepStmt.executeUpdate();
			c.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
				prepStmt.close();
			} catch (Exception e) {
			}
		}
	}

}
