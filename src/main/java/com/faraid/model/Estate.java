//Holds financial data (Total wealth, debts, funeral costs).
//Manages the financial state of the deceased.
package com.faraid.model;

public class Estate {
    private double totalWealth;
    private double netWealth;

    public Estate(double totalWealth) {
        this.totalWealth = totalWealth;
        this.netWealth = totalWealth;
    }

    public void deduct(double amount) { this.netWealth -= amount; }
    public double getNetWealth() { return netWealth; }
    public double getTotalWealth() { return totalWealth; }
}
