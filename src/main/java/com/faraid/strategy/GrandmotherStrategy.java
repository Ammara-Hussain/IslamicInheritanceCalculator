//Sharia Rule: Maternal and Paternal grandmothers share 1/6 equally.
//Maternal is excluded by the Mother; Paternal is excluded by the Mother and the Father.
package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class GrandmotherStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        boolean hasMother = allHeirs.stream().anyMatch(h -> h.getType() == HeirType.MOTHER && !h.isExcluded());
        boolean hasFather = allHeirs.stream().anyMatch(h -> h.getType() == HeirType.FATHER && !h.isExcluded());

        // Maternal Grandmother logic
        if (self.getType() == HeirType.MATERNAL_GRANDMOTHER && hasMother) return new Fraction(0, 1);

        // Paternal Grandmother logic
        if (self.getType() == HeirType.PATERNAL_GRANDMOTHER && (hasMother || hasFather)) return new Fraction(0, 1);

        long grandmotherCount = allHeirs.stream()
                .filter(h -> (h.getType() == HeirType.MATERNAL_GRANDMOTHER || h.getType() == HeirType.PATERNAL_GRANDMOTHER) && !h.isExcluded())
                .count();

        // 1/6 shared among eligible grandmothers
        return new Fraction(1, 6 * grandmotherCount);
    }
}
