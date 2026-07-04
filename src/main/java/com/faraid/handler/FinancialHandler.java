//Package: com.faraid.islamicinheritancecalculator.handler
//Implements the Chain of Responsibility for pre-inheritance financial steps.

//The Abstract Base Class for the chain.

package com.faraid.handler;

import com.faraid.model.Estate;

public abstract class FinancialHandler {
    protected FinancialHandler next;

    public void setNext(FinancialHandler next) { this.next = next; }

    public abstract void process(Estate estate, double funeral, double debt, double will);
}