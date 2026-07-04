//Sharia Rule: 1/2 if no children; 1/4 if children exist.
package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class HusbandStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        boolean hasDescendants = allHeirs.stream()
                .anyMatch(h -> (h.getType() == HeirType.SON || h.getType() == HeirType.DAUGHTER) && !h.isExcluded());

        return hasDescendants ? new Fraction(1, 4) : new Fraction(1, 2);
    }
}