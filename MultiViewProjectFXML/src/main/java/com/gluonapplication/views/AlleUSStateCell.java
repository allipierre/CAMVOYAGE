/**
 * 
 */
package com.gluonapplication.views;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;

import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author yotti
 *
 */

public class AlleUSStateCell extends CharmListCell<AlleUSState> {

	private final ListTile tile;
	private final ImageView imageView;

	public AlleUSStateCell() {
		this.tile = new ListTile();
		imageView = new ImageView();
		tile.setPrimaryGraphic(imageView);
		setText(null);
	}

	@Override
	public void updateItem(AlleUSState item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null && !empty) {
			tile.textProperty().setAll(item.getName() + " (" + item.getAbbr() + ")" + "   2000 Francs",
					"Depart: " + item.getCapital() + ", Population (M): "
							+ String.format("%.2f", item.getPopulation() / 1_000_000d),
					"Arrive: " + item.getArea() + ", Density (pop/km" + "\u00B2" + "): "
							+ String.format("%.1f", item.getDensity()));
			final Image image = new Image(item.getFlag(), 60, 85, true, true);
			 final Circle clip = new Circle();
			 clip.setStyle("-fx-border-color:green;");
			 clip.setCenterX(30);
			 clip.setCenterY(35);
			 clip.setRadius(30);
			
			 
			 System.out.println(tile.getProperties().get(0));
			 
			// imageView.setStyle("-fx-border-color:blue !important;");
//			Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
			
			
            

			if (image != null) {
				imageView.setClip(clip);
				imageView.setImage(image);

			}
			setGraphic(tile);
		} else {
			setGraphic(null);
		}
	}

}
