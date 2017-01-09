/**
 * 
 */
package com.gluonapplication.views;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author yotti
 *
 */

public class USStateCell extends CharmListCell<USState> {

    private final ListTile tile;
    private final ImageView imageView;

    public USStateCell() {
        this.tile = new ListTile();
        imageView = new ImageView();
        tile.setPrimaryGraphic(imageView);
        setText(null);
    }

    @Override
    public void updateItem(USState item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(item.getName() + " (" + item.getAbbr() + ")",
                    "Capital: " + item.getCapital() +
                            ", Population (M): " + String.format("%.2f", item.getPopulation() / 1_000_000d),
                    "Area (km" + "\u00B2" + "): " + item.getArea() +
                            ", Density (pop/km" + "\u00B2" + "): " + String.format("%.1f", item.getDensity())
            );
            final Image image = USStates.getImage(item.getFlag());
            if (image != null) {
                imageView.setImage(image);
            }
            setGraphic(tile);
        } else {
            setGraphic(null);
        }
    }

}
