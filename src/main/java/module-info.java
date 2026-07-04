module com.faraid.islamicinheritancecalculator {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.faraid.ui to javafx.fxml;
    opens com.faraid.model to javafx.base;

    exports com.faraid.ui;
    exports com.faraid.islamicinheritancecalculator;
}