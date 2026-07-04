package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class FullSisterStrategy implements InheritanceStrategy {
    @Override
    public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
        // 1. Exclusion (Hajb): Blocked by Father, Son, or Grandson
        // We use .equals() or == for Enums. Ensure Heir class has getType()
        boolean isBlocked = allHeirs.stream().anyMatch(h ->
                (h.getType() == HeirType.SON ||
                        h.getType() == HeirType.FATHER ||
                        h.getType() == HeirType.GRANDSON) && !h.isExcluded());

        // 2. Asabah Check: If a Full Brother exists OR if there are Daughters/Granddaughters
        boolean hasBrother = allHeirs.stream().anyMatch(h ->
                h.getType() == HeirType.FULL_BROTHER && !h.isExcluded());

        boolean hasFemaleDescendants = allHeirs.stream().anyMatch(h ->
                (h.getType() == HeirType.DAUGHTER || h.getType() == HeirType.GRANDDAUGHTER) && !h.isExcluded());

        // If blocked or converted to Asabah, fixed share is zero
        if (isBlocked || hasBrother || hasFemaleDescendants) {
            return new Fraction(0, 1);
        }

        // 3. Count active Full Sisters
        // Use (int) cast because Fraction constructor usually takes ints, not longs
        int sisterCount = (int) allHeirs.stream()
                .filter(h -> h.getType() == HeirType.FULL_SISTER && !h.isExcluded())
                .count();

        if (sisterCount == 0) return new Fraction(0, 1);

        // 4. Fixed Share Logic
        if (sisterCount == 1) {
            return new Fraction(1, 2); // Single sister gets 1/2
        } else {
            // Multiple sisters share 2/3.
            // Individual share = 2 / (3 * number of sisters)
            return new Fraction(2, 3 * sisterCount);
        }
    }
}