/*PaternalSisterStrategy.javaPaternal sisters (same father, different mother)
 are a special case. They are excluded by a Full Brother or two Full Sisters.
 However, if there is only one Full Sister,the Paternal Sister takes 1/6
 to complete the $2/3$ (Takmilat al-Thuluthayn).*/
package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class PaternalSisterStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        // Excluded by Son, Father, Full Brother, or 2+ Full Sisters
        long fullSisterCount = allHeirs.stream()
                .filter(h -> h.getType() == HeirType.FULL_SISTER && !h.isExcluded()).count();
        boolean hasBlockingMale = allHeirs.stream().anyMatch(h ->
                (h.getType() == HeirType.SON || h.getType() == HeirType.FATHER || h.getType() == HeirType.FULL_BROTHER) && !h.isExcluded());

        if (hasBlockingMale || fullSisterCount >= 2) return new Fraction(0, 1);

        // Special Rule: If 1 Full Sister exists, Paternal Sister gets 1/6
        if (fullSisterCount == 1) return new Fraction(1, 6);

        // Otherwise, she follows the 1/2 or 2/3 rule like a Full Sister
        long patSisterCount = allHeirs.stream()
                .filter(h -> h.getType() == HeirType.PATERNAL_SISTER && !h.isExcluded()).count();

        return patSisterCount == 1 ? new Fraction(1, 2) : new Fraction(2, 3 * patSisterCount);
    }
}