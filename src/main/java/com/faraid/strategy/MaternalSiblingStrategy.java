package com.faraid.strategy;

import com.faraid.model.*;
import java.util.List;
import com.faraid.logic.LegalReferences; // 1. Import the class
public class MaternalSiblingStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        // Excluded by any descendant or male ascendant
        boolean isBlocked = allHeirs.stream().anyMatch(h ->
                (h.getType() == HeirType.SON || h.getType() == HeirType.DAUGHTER ||
                        h.getType() == HeirType.FATHER || h.getType() == HeirType.PATERNAL_GRANDFATHER)
                        && !h.isExcluded());

        if (isBlocked) return new Fraction(0, 1);

        long count = allHeirs.stream()
                .filter(h -> (h.getType() == HeirType.MATERNAL_BROTHER || h.getType() == HeirType.MATERNAL_SISTER)
                        && !h.isExcluded())
                .count();

        if (count == 1) return new Fraction(1, 6);
        // Shared equally among all maternal siblings
        return count > 1 ? new Fraction(1, 3 * count) : new Fraction(0, 1);
    }
}