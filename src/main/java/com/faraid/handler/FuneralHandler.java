//Subtracts funeral costs first.

package com.faraid.handler;

import com.faraid.model.Estate;

public class FuneralHandler extends FinancialHandler {
    @Override
    public void process(Estate estate, double funeral, double debt, double will) {
        estate.deduct(funeral);
        if (next != null) next.process(estate, funeral, debt, will);
    }
}