//The Interface that all role-based strategies follow.

package com.faraid.strategy;

import com.faraid.model.Fraction;
import com.faraid.model.Heir;
import java.util.List;
import com.faraid.logic.LegalReferences; // 1. Import the class
/**
 * Strategy interface for calculating Quranic fixed shares (Fardh).
 * Part of the Strategy Design Pattern implementation for the Faraid System.
 */
public interface InheritanceStrategy {

    /**
     * Calculates the exact Quranic fraction for a specific heir role.
     * * @param self     The heir object whose share is being calculated.
     * @param allHeirs The complete list of survivors (used to check for exclusions or dependencies).
     * @return         A Fraction object representing the heir's fixed portion (e.g., 1/2, 1/4, 1/8).
     */
    Fraction calculateFixedShare(Heir self, List<Heir> allHeirs);
}