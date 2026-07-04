/*ExclusionEngine.java
This class implements Hajb (blocking).
 It must be called first to ensure that distant relatives
 (like brothers)
 don't get a share if a primary relative (like a son) is present.*/

package com.faraid.logic;

import com.faraid.model.*;
import java.util.List;

public class ExclusionEngine {

    public static void applyHajb(List<Heir> heirs) {
        // Essential Presence Checks
        boolean hasSon = heirs.stream().anyMatch(h -> h.getType() == HeirType.SON && !h.isExcluded());
        boolean hasFather = heirs.stream().anyMatch(h -> h.getType() == HeirType.FATHER && !h.isExcluded());

        for (Heir h : heirs) {
            // Rule: Son excludes all siblings
            if (isSibling(h.getType()) && hasSon) {
                h.markAsExcluded("Blocked by the existence of a Son.");
            }
            // Rule: Father excludes Grandfather and Paternal Siblings
            if (h.getType() == HeirType.PATERNAL_GRANDFATHER && hasFather) {
                h.markAsExcluded("Blocked by the existence of the Father.");
            }
            if (h.getType() == HeirType.PATERNAL_SISTER && hasFather) {
                h.markAsExcluded("Blocked by the existence of the Father.");
            }
        }
    }

    private static boolean isSibling(HeirType type) {
        return type == HeirType.FULL_BROTHER || type == HeirType.FULL_SISTER ||
                type == HeirType.PATERNAL_BROTHER || type == HeirType.PATERNAL_SISTER ||
                type == HeirType.MATERNAL_BROTHER || type == HeirType.MATERNAL_SISTER;
    }
}