//Ensures the Will (Wasiyyah) does not exceed the Sharia limit of 1/3.

package com.faraid.handler;

import com.faraid.model.Estate;

public class WillHandler extends FinancialHandler {
    @Override
    public void process(Estate estate, double funeral, double debt, double will) {
        double maxWill = estate.getNetWealth() / 3.0;
        estate.deduct(Math.min(will, maxWill));
        if (next != null) next.process(estate, funeral, debt, will);
    }
}
