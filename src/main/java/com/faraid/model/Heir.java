package com.faraid.model;

import javafx.beans.property.*;

public class Heir {
    private final HeirType type;
    private final StringProperty name;
    private final StringProperty shareDisplay;
    private final BooleanProperty excluded;
    private final StringProperty exclusionReason; // Added for the engine
    private Fraction shareFraction;
    private final StringProperty reference;
    public Heir(HeirType type, String name) {
        this.type = type;
        this.name = new SimpleStringProperty(name);
        this.shareDisplay = new SimpleStringProperty("0");
        this.excluded = new SimpleBooleanProperty(false);
        this.exclusionReason = new SimpleStringProperty("");
        this.shareFraction = new Fraction(0, 1);
        this.reference = new SimpleStringProperty("—");
    }

    // --- THIS IS THE MISSING METHOD ---
    public void markAsExcluded(String reason) {
        this.excluded.set(true);
        this.exclusionReason.set(reason);
        this.shareDisplay.set("Excluded: " + reason);
        this.shareFraction = new Fraction(0, 1);
    }

    // Standard Getters
    public HeirType getType() { return type; }
    public boolean isExcluded() { return excluded.get(); }
    public StringProperty shareDisplayProperty() { return shareDisplay; }
    public StringProperty nameProperty() { return name; }

    public Fraction getShare() { return shareFraction; }
    public void setShare(Fraction fraction) {
        if (!isExcluded()) {
            this.shareFraction = fraction;
            this.shareDisplay.set(fraction.toString());
        }
    }
    public StringProperty referenceProperty() { return reference; }
    public void setReference(String ref) { this.reference.set(ref); }
}