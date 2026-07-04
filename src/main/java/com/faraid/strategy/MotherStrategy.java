//MotherStrategy
//Sharia Rule: 1/6 if children exist OR 2+ siblings exist(Case of Ikhwah); otherwise 1/3.
//Note: Omariyyah cases where she takes 1/3 of residue are handled in the RuleEngine.
package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class MotherStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        boolean hasDescendants = allHeirs.stream()
                .anyMatch(h -> (h.getType() == HeirType.SON || h.getType() == HeirType.DAUGHTER) && !h.isExcluded());

        long siblings = allHeirs.stream()
                .filter(h -> (h.getType() == HeirType.FULL_BROTHER || h.getType() == HeirType.FULL_SISTER ||
                        h.getType() == HeirType.PATERNAL_BROTHER || h.getType() == HeirType.PATERNAL_SISTER)
                        && !h.isExcluded()).count();

        return (hasDescendants || siblings >= 2) ? new Fraction(1, 6) : new Fraction(1, 3);
    }
}