//DaughterStrategy.java
//Sharia Rule: 1/2 if alone; 2/3 if 2+ daughters and NO son.
// If a son exists, she becomes Asabah (no fixed share).
package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class DaughterStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        boolean hasSon = allHeirs.stream()
                .anyMatch(h -> h.getType() == HeirType.SON && !h.isExcluded());

        if (hasSon) return new Fraction(0, 1); // Becomes Asabah with her brother

        long daughterCount = allHeirs.stream()
                .filter(h -> h.getType() == HeirType.DAUGHTER && !h.isExcluded()).count();

        if (daughterCount == 1) return new Fraction(1, 2);
        return new Fraction(2, 3 * daughterCount); // 2/3 shared equally
    }
}