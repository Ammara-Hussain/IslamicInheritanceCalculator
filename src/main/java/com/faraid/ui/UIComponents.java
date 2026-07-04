//Helper for custom-styled buttons and emerald-gold themes.

//This class handles common UI tasks like pop-up messages.

package com.faraid.ui;

import javafx.scene.control.Alert;

public class UIComponents {
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}