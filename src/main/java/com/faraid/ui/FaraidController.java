/*package com.faraid.ui;

import com.faraid.model.*;
import com.faraid.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FaraidController {

    @FXML private ScrollPane mainScrollPane;
    @FXML private HBox navigationBar;
    @FXML private VBox calculatorSection, referencesSection, fixedSharesSection, topologySection, guidanceBannerSection;
    @FXML private ImageView fixedSharesImageView, topologyImageView, referencesImageView, quranicImageView;
    @FXML private TextField totalWealthField, debtField, funeralField, willField;
    @FXML private ComboBox<String> heirTypeSelector;
    @FXML private Label netEstateLabel;
    @FXML private TableView<Heir> resultsTable;
    @FXML private TableColumn<Heir, String> nameColumn, shareColumn, refColumn;

    private final ObservableList<Heir> heirList = FXCollections.observableArrayList();
    private final RuleEngine ruleEngine = new RuleEngine();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(d -> d.getValue().nameProperty());
        shareColumn.setCellValueFactory(d -> d.getValue().shareDisplayProperty());

        // Reference column as a clickable link
        refColumn.setCellValueFactory(d -> d.getValue().referenceProperty());
        refColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.equals("—")) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Hyperlink link = new Hyperlink(item);
                    link.setStyle("-fx-text-fill: #008080; -fx-underline: true;");
                    link.setOnAction(e -> scrollToReference());
                    setGraphic(link);
                }
            }
        });

        resultsTable.setItems(heirList);
        for (HeirType type : HeirType.values()) heirTypeSelector.getItems().add(type.name());

        // Safe image loading
        loadImage(quranicImageView,       "/assets/image_4c8270.jpeg");
        loadImage(fixedSharesImageView,   "/assets/image_4c772c.png");
        loadImage(topologyImageView,      "/assets/image_4c77a3.jpeg");
        loadImage(referencesImageView,    "/assets/quranic_references.jpeg");
    }

    private void loadImage(ImageView iv, String path) {
        try {
            var stream = getClass().getResourceAsStream(path);
            if (stream != null) iv.setImage(new Image(stream));
        } catch (Exception e) {
            System.err.println("Load Error: " + path);
        }
    }

    @FXML
    private void onCalculateButtonClick() {
        try {
            double total   = Double.parseDouble(totalWealthField.getText());
            double debt    = Double.parseDouble(debtField.getText());
            double funeral = Double.parseDouble(funeralField.getText());
            double will    = Double.parseDouble(willField.getText());
            double netEstateValue = total - debt - funeral - will;

            ruleEngine.calculateDistributions(netEstateValue, heirList);

            for (Heir heir : heirList) {
                if (!heir.isExcluded()) {
                    Fraction f = heir.getShare();
                    double cash = ((double) f.getNumerator() / f.getDenominator()) * netEstateValue;
                    heir.shareDisplayProperty().set(String.format("%s ➔ Rs %.2f", f.toString(), cash));
                } else {
                    heir.shareDisplayProperty().set("Excluded");
                }
            }

            netEstateLabel.setText(String.format("Net Estate for Distribution: Rs %.2f", netEstateValue));
            resultsTable.refresh();

        } catch (Exception e) {
            UIComponents.showAlert("Error", "Check numeric fields.");
        }
    }

    // Navigation Methods
    @FXML private void scrollToCalculator()  { scroll(calculatorSection); }
    @FXML private void scrollToReference()   { scroll(referencesSection); }
    @FXML private void scrollToFixedShares() { scroll(fixedSharesSection); }
    @FXML private void scrollToTopology()    { scroll(topologySection); }

    private void scroll(VBox target) {
        if (target == null || mainScrollPane.getContent() == null) return;

        // Walk up the parent chain to accumulate the true Y position
        // within the scrollable content (getLayoutY() is only relative to direct parent)
        double totalY = 0;
        javafx.scene.Node node = target;
        while (node != null && node != mainScrollPane.getContent()) {
            totalY += node.getLayoutY();
            node = node.getParent();
        }

        double navbarHeight = navigationBar.getHeight();
        double padding = 15;

        double contentHeight = mainScrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = mainScrollPane.getViewportBounds().getHeight();

        double rawY = totalY - navbarHeight - padding;
        double vValue = rawY / (contentHeight - viewportHeight);
        mainScrollPane.setVvalue(Math.max(0, Math.min(1, vValue)));
    }

    @FXML private void handleAddHeirAction() {
        String selection = heirTypeSelector.getValue();
        if (selection != null) heirList.add(new Heir(HeirType.valueOf(selection), selection));
    }

    @FXML private void onResetButtonClick() {
        totalWealthField.clear();
        debtField.setText("0");
        funeralField.setText("0");
        willField.setText("0");
        heirList.clear();
        netEstateLabel.setText("Net Estate for Distribution: --");
        mainScrollPane.setVvalue(0.0);
    }
}*/
/*package com.faraid.ui;

import com.faraid.model.*;
import com.faraid.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FaraidController {

    @FXML private ScrollPane mainScrollPane;
    @FXML private VBox calculatorSection, referencesSection, fixedSharesSection, topologySection;
    @FXML private ImageView referencesImageView, quranicImageView;
    @FXML private TextField totalWealthField, debtField, funeralField, willField;
    @FXML private ComboBox<String> heirTypeSelector;
    @FXML private Label netEstateLabel;
    @FXML private TableView<Heir> resultsTable;
    @FXML private TableColumn<Heir, String> nameColumn, shareColumn, refColumn;

    private final ObservableList<Heir> heirList = FXCollections.observableArrayList();
    private final RuleEngine ruleEngine = new RuleEngine();

    @FXML
    public void initialize() {
        // Link Table Columns
        nameColumn.setCellValueFactory(d -> d.getValue().nameProperty());
        shareColumn.setCellValueFactory(d -> d.getValue().shareDisplayProperty());
        refColumn.setCellValueFactory(d -> d.getValue().referenceProperty());

        resultsTable.setItems(heirList);
        for (HeirType type : HeirType.values()) heirTypeSelector.getItems().add(type.name());

        // Asset Loading
        loadImage(quranicImageView,    "/assets/image_4c8270.jpeg");

    }

    private void loadImage(ImageView iv, String path) {
        try {
            var stream = getClass().getResourceAsStream(path);
            if (stream != null) iv.setImage(new Image(stream));
        } catch (Exception e) { System.err.println("Asset missing: " + path); }
    }

    @FXML
    private void onCalculateButtonClick() {
        try {
            double total = Double.parseDouble(totalWealthField.getText());
            double net = total - Double.parseDouble(debtField.getText())
                    - Double.parseDouble(funeralField.getText())
                    - Double.parseDouble(willField.getText());

            ruleEngine.calculateDistributions(net, heirList);
            netEstateLabel.setText(String.format("Net Estate for Distribution: Rs %.2f", net));
            resultsTable.refresh();
        } catch (Exception e) {
            System.err.println("Calc Error: " + e.getMessage());
        }
    }

    // Unified Navigation Logic
    @FXML private void scrollToCalculator()  { scroll(calculatorSection); }
    @FXML private void scrollToReference()   { scroll(referencesSection); }
    @FXML private void scrollToFixedShares() { scroll(fixedSharesSection); }
    @FXML private void scrollToTopology()    { scroll(topologySection); }

    private void scroll(VBox target) {
        if (target == null) return;
        double totalY = 0;
        javafx.scene.Node node = target;
        while (node != null && node != mainScrollPane.getContent()) {
            totalY += node.getLayoutY();
            node = node.getParent();
        }
        double contentHeight = mainScrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = mainScrollPane.getViewportBounds().getHeight();
        mainScrollPane.setVvalue(totalY / (contentHeight - viewportHeight));
    }

    @FXML private void handleAddHeirAction() {
        String s = heirTypeSelector.getValue();
        if (s != null) heirList.add(new Heir(HeirType.valueOf(s), s));
    }

    @FXML private void onResetButtonClick() {
        totalWealthField.clear();
        heirList.clear();
        netEstateLabel.setText("Net Estate for Distribution: --");
    }
}*/
/*package com.faraid.ui;

import com.faraid.model.*;
import com.faraid.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FaraidController {

    @FXML private ScrollPane mainScrollPane;
    @FXML private HBox navigationBar;
    @FXML private VBox calculatorSection, referencesSection, fixedSharesSection, topologySection;
    @FXML private ImageView quranicImageView;
    @FXML private TextField totalWealthField, debtField, funeralField, willField;
    @FXML private ComboBox<String> heirTypeSelector;
    @FXML private Label netEstateLabel;
    @FXML private TableView<Heir> resultsTable;
    @FXML private TableColumn<Heir, String> nameColumn, shareColumn, refColumn;

    private final ObservableList<Heir> heirList = FXCollections.observableArrayList();
    private final RuleEngine ruleEngine = new RuleEngine();

    @FXML
    public void initialize() {
        // --- PREVIOUS LOGIC PRESERVED: Table Cell Rendering ---
        nameColumn.setCellValueFactory(d -> d.getValue().nameProperty());
        shareColumn.setCellValueFactory(d -> d.getValue().shareDisplayProperty());

        refColumn.setCellValueFactory(d -> d.getValue().referenceProperty());
        refColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.equals("—")) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Hyperlink link = new Hyperlink(item);
                    link.setStyle("-fx-text-fill: #008080; -fx-underline: true;");
                    link.setOnAction(e -> scrollToReference());
                    setGraphic(link);
                }
            }
        });

        resultsTable.setItems(heirList);
        for (HeirType type : HeirType.values()) {
            heirTypeSelector.getItems().add(type.name());
        }

        // --- UPDATED LOGIC: Loading remaining asset only ---
        loadImage(quranicImageView, "/assets/image_4c8270.jpeg");
    }

    private void loadImage(ImageView iv, String path) {
        if (iv == null) return;
        try {
            var stream = getClass().getResourceAsStream(path);
            if (stream != null) iv.setImage(new Image(stream));
        } catch (Exception e) {
            System.err.println("Load Error: " + path);
        }
    }

    @FXML
    private void onCalculateButtonClick() {
        // --- PREVIOUS LOGIC PRESERVED: Precise Calculation & Display Loop ---
        try {
            double total   = Double.parseDouble(totalWealthField.getText());
            double debt    = Double.parseDouble(debtField.getText());
            double funeral = Double.parseDouble(funeralField.getText());
            double will    = Double.parseDouble(willField.getText());
            double netEstateValue = total - debt - funeral - will;

            // Business logic call
            ruleEngine.calculateDistributions(netEstateValue, heirList);

            // Calculation Display Loop (Logic from your provided code)
            for (Heir heir : heirList) {
                if (!heir.isExcluded()) {
                    Fraction f = heir.getShare();
                    double cash = ((double) f.getNumerator() / f.getDenominator()) * netEstateValue;
                    heir.shareDisplayProperty().set(String.format("%s ➔ Rs %.2f", f.toString(), cash));
                } else {
                    heir.shareDisplayProperty().set("Excluded");
                }
            }

            netEstateLabel.setText(String.format("Net Estate for Distribution: Rs %.2f", netEstateValue));
            resultsTable.refresh();

        } catch (Exception e) {
            // Using logic from your provided version
            UIComponents.showAlert("Error", "Check numeric fields.");
        }
    }

    // --- NAVIGATION LOGIC PRESERVED: Accurate coordinate walking ---
    @FXML private void scrollToCalculator()  { scroll(calculatorSection); }
    @FXML private void scrollToReference()   { scroll(referencesSection); }
    @FXML private void scrollToFixedShares() { scroll(fixedSharesSection); }
    @FXML private void scrollToTopology()    { scroll(topologySection); }

    private void scroll(VBox target) {
        if (target == null || mainScrollPane.getContent() == null) return;

        double totalY = 0;
        javafx.scene.Node node = target;
        while (node != null && node != mainScrollPane.getContent()) {
            totalY += node.getLayoutY();
            node = node.getParent();
        }

        double navbarHeight = navigationBar.getHeight();
        double padding = 15;

        double contentHeight = mainScrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = mainScrollPane.getViewportBounds().getHeight();

        double rawY = totalY - navbarHeight - padding;
        double vValue = rawY / (contentHeight - viewportHeight);
        mainScrollPane.setVvalue(Math.max(0, Math.min(1, vValue)));
    }

    @FXML private void handleAddHeirAction() {
        String selection = heirTypeSelector.getValue();
        if (selection != null) {
            heirList.add(new Heir(HeirType.valueOf(selection), selection));
        }
    }

    @FXML private void onResetButtonClick() {
        totalWealthField.clear();
        debtField.setText("0");
        funeralField.setText("0");
        willField.setText("0");
        heirList.clear();
        netEstateLabel.setText("Net Estate for Distribution: --");
        mainScrollPane.setVvalue(0.0);
    }
}*/
package com.faraid.ui;

import com.faraid.model.*;
import com.faraid.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FaraidController {

    @FXML private ScrollPane mainScrollPane;
    @FXML private HBox navigationBar;
    @FXML private VBox calculatorSection, referencesSection, fixedSharesSection, topologySection;
    @FXML private ImageView quranicImageView;
    @FXML private TextField totalWealthField, debtField, funeralField, willField;
    @FXML private ComboBox<String> heirTypeSelector;
    @FXML private Label netEstateLabel;
    @FXML private TableView<Heir> resultsTable;
    @FXML private TableColumn<Heir, String> nameColumn, shareColumn, refColumn;

    private final ObservableList<Heir> heirList = FXCollections.observableArrayList();
    private final RuleEngine ruleEngine = new RuleEngine();

    @FXML
    public void initialize() {
        // --- PREVIOUS LOGIC PRESERVED: Table Cell Rendering ---
        nameColumn.setCellValueFactory(d -> d.getValue().nameProperty());
        shareColumn.setCellValueFactory(d -> d.getValue().shareDisplayProperty());

        refColumn.setCellValueFactory(d -> d.getValue().referenceProperty());
        refColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.equals("—")) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Hyperlink link = new Hyperlink(item);
                    link.setStyle("-fx-text-fill: #008080; -fx-underline: true;");
                    link.setOnAction(e -> scrollToReference());
                    setGraphic(link);
                }
            }
        });

        resultsTable.setItems(heirList);
        for (HeirType type : HeirType.values()) {
            heirTypeSelector.getItems().add(type.name());
        }

        // --- UPDATED LOGIC: Loading remaining asset only ---
        loadImage(quranicImageView, "/assets/image_4c8270.jpeg");
    }

    private void loadImage(ImageView iv, String path) {
        if (iv == null) return;
        try {
            var stream = getClass().getResourceAsStream(path);
            if (stream != null) iv.setImage(new Image(stream));
        } catch (Exception e) {
            System.err.println("Load Error: " + path);
        }
    }

    @FXML
    private void onCalculateButtonClick() {
        // --- PREVIOUS LOGIC PRESERVED: Precise Calculation & Display Loop ---
        try {
            double total   = Double.parseDouble(totalWealthField.getText());
            double debt    = Double.parseDouble(debtField.getText());
            double funeral = Double.parseDouble(funeralField.getText());
            double will    = Double.parseDouble(willField.getText());
            double netEstateValue = total - debt - funeral - will;

            // Business logic call
            ruleEngine.calculateDistributions(netEstateValue, heirList);

            // Calculation Display Loop (Logic from your provided code)
            for (Heir heir : heirList) {
                if (!heir.isExcluded()) {
                    Fraction f = heir.getShare();
                    double cash = ((double) f.getNumerator() / f.getDenominator()) * netEstateValue;
                    heir.shareDisplayProperty().set(String.format("%s ➔ Rs %.2f", f.toString(), cash));
                } else {
                    heir.shareDisplayProperty().set("Excluded");
                }
            }

            netEstateLabel.setText(String.format("Net Estate for Distribution: Rs %.2f", netEstateValue));
            resultsTable.refresh();

        } catch (Exception e) {
            // Using logic from your provided version
            UIComponents.showAlert("Error", "Check numeric fields.");
        }
    }

    // --- NAVIGATION LOGIC PRESERVED: Accurate coordinate walking ---
    @FXML private void scrollToCalculator()  { scroll(calculatorSection); }
    @FXML private void scrollToReference()   { scroll(referencesSection); }
    @FXML private void scrollToFixedShares() { scroll(fixedSharesSection); }
    @FXML private void scrollToTopology()    { scroll(topologySection); }

    private void scroll(VBox target) {
        if (target == null || mainScrollPane.getContent() == null) return;

        double totalY = 0;
        javafx.scene.Node node = target;
        while (node != null && node != mainScrollPane.getContent()) {
            totalY += node.getLayoutY();
            node = node.getParent();
        }

        double navbarHeight = navigationBar.getHeight();
        double padding = 15;

        double contentHeight = mainScrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = mainScrollPane.getViewportBounds().getHeight();

        double rawY = totalY - navbarHeight - padding;
        double vValue = rawY / (contentHeight - viewportHeight);
        mainScrollPane.setVvalue(Math.max(0, Math.min(1, vValue)));
    }

    @FXML private void handleAddHeirAction() {
        String selection = heirTypeSelector.getValue();
        if (selection != null) {
            heirList.add(new Heir(HeirType.valueOf(selection), selection));
        }
    }

    @FXML private void onResetButtonClick() {
        totalWealthField.clear();
        debtField.setText("0");
        funeralField.setText("0");
        willField.setText("0");
        heirList.clear();
        netEstateLabel.setText("Net Estate for Distribution: --");
        mainScrollPane.setVvalue(0.0);
    }
}