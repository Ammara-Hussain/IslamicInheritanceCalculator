//To keep your UI code professional,
//you shouldn't have long if/else statements in the Controller.

package com.faraid.strategy;
import com.faraid.logic.LegalReferences; // 1. Import the class
import com.faraid.model.*;
import java.util.List;

public class StrategyFactory {

    public static InheritanceStrategy getStrategy(HeirType type) {
        switch (type) {
            case HUSBAND: return new HusbandStrategy();
            case WIFE: return new WifeStrategy();
            case MOTHER: return new MotherStrategy();
            case DAUGHTER: return new DaughterStrategy();
            case FATHER: return new FatherStrategy();
            case FULL_SISTER: return new FullSisterStrategy();
            case PATERNAL_SISTER: return new PaternalSisterStrategy();
            case MATERNAL_BROTHER:
            case MATERNAL_SISTER: return new MaternalSiblingStrategy();

            // Fix: Use the correct Enum names from your HeirType file
            case MATERNAL_GRANDMOTHER:
            case PATERNAL_GRANDMOTHER: return new GrandmotherStrategy();

            default: return new InheritanceStrategy() {
                @Override
                public Fraction calculateFixedShare(Heir self, List<Heir> allHeirs) {
                    return new Fraction(0, 1);
                }
            };
        }
    }
}