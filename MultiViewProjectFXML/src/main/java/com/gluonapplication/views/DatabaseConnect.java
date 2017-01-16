/**
 * 
 */
package com.gluonapplication.views;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.StorageService;
import com.gluonhq.charm.glisten.control.Alert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

/**
 * @author yotti
 *
 */
public class DatabaseConnect {
	private final static String DB_NAME = "test.db";

	public static void  setDB() {
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

		} catch (Exception e) {

		}

	}

}
