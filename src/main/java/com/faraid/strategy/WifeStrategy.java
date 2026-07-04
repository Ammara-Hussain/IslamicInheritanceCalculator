//Sharia Rule: 1/4 if no children; 1/8 if children exist.
// (Multiple wives share this fixed portion equally).

package com.faraid.strategy;

import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class WifeStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        boolean hasDescendants = allHeirs.stream()
                .anyMatch(h -> (h.getType() == HeirType.SON || h.getType() == HeirType.DAUGHTER) && !h.isExcluded());

        long wifeCount = allHeirs.stream()
                .filter(h -> h.getType() == HeirType.WIFE && !h.isExcluded()).count();

        Fraction totalWifeShare = hasDescendants ? new Fraction(1, 8) : new Fraction(1, 4);

        // Divide the fixed share by the number of wives
        return new Fraction(totalWifeShare.getNumerator(), totalWifeShare.getDenominator() * wifeCount);
    }
}