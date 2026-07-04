//Subtracts debts second.
package com.faraid.handler;

import com.faraid.model.Estate;

/**
 * Step 2 in the Financial Chain: Deducts all outstanding debts from the estate.
 * Settling debts is a mandatory Sharia requirement before inheritance distribution.
 */
public class DebtHandler extends FinancialHandler {

    @Override
    public void process(Estate estate, double funeral, double debt, double will) {
        // Log the process for professional audit (optional, but good for debugging)
        System.out.println("SDA Pipeline: Processing Debt Deduction - " + debt);

        // Subtract the debt from the net wealth
        estate.deduct(debt);

        // Pass the remaining estate to the next handler (WillHandler)
        if (next != null) {
            next.process(estate, funeral, debt, will);
        }
    }
}