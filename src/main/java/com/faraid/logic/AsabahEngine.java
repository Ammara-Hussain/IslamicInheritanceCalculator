//The "Brain" of the system that follow the 7-step Sharia procedure.

/*Handles the distribution of the residue for the blood relationships
 that do not have a fixed share.*/
/*This handles the Residue Distribution (Step 7),
enforcing the Quranic 2:1 ratio for sons/daughters and brothers/sisters.*/

package com.faraid.logic;

import com.faraid.model.*;
import java.util.List;
import java.util.stream.Collectors;

public class AsabahEngine {

    public void distributeResidue(Fraction residue, List<Heir> heirs) {
        if (residue.getNumerator() <= 0) return;

        // Find primary residuaries (Sons and Daughters)
        List<Heir> asabahHeirs = heirs.stream()
                .filter(h -> (h.getType() == HeirType.SON || h.getType() == HeirType.DAUGHTER) && !h.isExcluded())
                .collect(Collectors.toList());

        if (asabahHeirs.isEmpty()) return;

        // Calculate total weight (Son=2, Daughter=1)
        long totalWeight = 0;
        for (Heir h : asabahHeirs) {
            totalWeight += (h.getType() == HeirType.SON) ? 2 : 1;
        }

        // Apply weight to the residue
        for (Heir h : asabahHeirs) {
            long weight = (h.getType() == HeirType.SON) ? 2 : 1;
            Fraction portion = residue.multiply(new Fraction(weight, totalWeight));
            h.setShare(h.getShare().add(portion));
        }
    }
}