/*In professional SDA, the validator checks the state of the math to decide
if we need to apply Awl (Increase denominator) or Radd (Redistribute surplus).*/

package com.faraid.logic;

import com.faraid.model.Fraction;

public class MathValidator {

    public enum State {
        NORMAL, // Total = 1
        AWL,    // Total > 1 (Needs denominator adjustment)
        RADD    // Total < 1 (Needs surplus redistribution)
    }

    public static State validate(Fraction totalAssigned) {
        if (totalAssigned.getNumerator() > totalAssigned.getDenominator()) {
            return State.AWL;
        } else if (totalAssigned.getNumerator() < totalAssigned.getDenominator()) {
            return State.RADD;
        }
        return State.NORMAL;
    }
}