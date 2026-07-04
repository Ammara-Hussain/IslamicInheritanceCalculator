//Sharia Rule: 1/6 fixed if children exist.
// If no children, he is purely Asabah (residuary).
package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class FatherStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        boolean hasDescendants = allHeirs.stream()
                .anyMatch(h -> (h.getType() == HeirType.SON || h.getType() == HeirType.DAUGHTER) && !h.isExcluded());

        // If descendants exist, he gets 1/6 as Fardh.
        // If not, he takes nothing here because he will take the residue in AsabahEngine.
        return hasDescendants ? new Fraction(1, 6) : new Fraction(0, 1);
    }
}
