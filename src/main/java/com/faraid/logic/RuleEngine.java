package com.faraid.logic;

import com.faraid.model.*;
import com.faraid.strategy.*;
import java.util.List;
import java.util.stream.Collectors;

public class RuleEngine {
    private final AsabahEngine asabahEngine = new AsabahEngine();

    public void calculateDistributions(double netEstate, List<Heir> heirs) {
        heirs.forEach(h -> {
            h.setShare(new Fraction(0, 1));
            h.referenceProperty().set("—");
        });

        ExclusionEngine.applyHajb(heirs);

        Fraction totalAssigned = new Fraction(0, 1);
        for (Heir h : heirs) {
            if (!h.isExcluded()) {
                InheritanceStrategy strategy = StrategyFactory.getStrategy(h.getType());
                Fraction fardh = strategy.calculateFixedShare(h, heirs);
                h.setShare(fardh);

                h.referenceProperty().set(getQuranicReference(h.getType()));
                totalAssigned = totalAssigned.add(fardh);
            }
        }

        MathValidator.State state = MathValidator.validate(totalAssigned);

        if (state == MathValidator.State.AWL) {
            applyAwlLogic(heirs, totalAssigned);
        } else {
            handleResidueOrRadd(heirs, totalAssigned);
        }
    }

    private void applyAwlLogic(List<Heir> heirs, Fraction totalAssigned) {
        long newDenom = totalAssigned.getNumerator();
        for (Heir h : heirs) {
            if (!h.isExcluded() && h.getShare().getNumerator() > 0) {
                h.setShare(new Fraction(h.getShare().getNumerator(), newDenom));
                String currentRef = h.referenceProperty().get();
                h.referenceProperty().set(currentRef + " (Reduced by Awl)");
            }
        }
    }

    private void handleResidueOrRadd(List<Heir> heirs, Fraction totalAssigned) {
        Fraction residue = new Fraction(totalAssigned.getDenominator() - totalAssigned.getNumerator(),
                totalAssigned.getDenominator());

        if (residue.getNumerator() <= 0) return;

        boolean hasAsabah = heirs.stream().anyMatch(h ->
                (h.getType() == HeirType.SON || h.getType() == HeirType.FULL_BROTHER ||
                        h.getType() == HeirType.FATHER || h.getType() == HeirType.PATERNAL_BROTHER ||
                        h.getType() == HeirType.GRANDSON)
                        && !h.isExcluded());

        if (hasAsabah) {
            asabahEngine.distributeResidue(residue, heirs);

            heirs.stream().filter(h -> !h.isExcluded() && h.getShare().getNumerator() > 0)
                    .forEach(h -> {
                        if (h.referenceProperty().get().equals("—") || h.getType() == HeirType.SON || h.getType() == HeirType.GRANDSON) {
                            h.referenceProperty().set("Surah An-Nisa 4:11 / Bukhari 6732 (Residue)");
                        }
                    });
        } else {
            applyRaddLogic(heirs, totalAssigned);
        }
    }

    private void applyRaddLogic(List<Heir> heirs, Fraction totalAssigned) {
        List<Heir> bloodHeirs = heirs.stream()
                .filter(h -> !h.isExcluded() && h.getType() != HeirType.HUSBAND &&
                        h.getType() != HeirType.WIFE && h.getShare().getNumerator() > 0)
                .collect(Collectors.toList());

        if (bloodHeirs.isEmpty()) return;

        long bloodNumeratorSum = 0;
        for (Heir h : bloodHeirs) bloodNumeratorSum += h.getShare().getNumerator();

        Heir spouse = heirs.stream()
                .filter(h -> (h.getType() == HeirType.HUSBAND || h.getType() == HeirType.WIFE) && !h.isExcluded())
                .findFirst().orElse(null);

        if (spouse != null) {
            Fraction leftoverForRadd = new Fraction(totalAssigned.getDenominator() - spouse.getShare().getNumerator(),
                    totalAssigned.getDenominator());

            for (Heir h : bloodHeirs) {
                Fraction raddWeight = new Fraction(h.getShare().getNumerator(), bloodNumeratorSum);
                h.setShare(leftoverForRadd.multiply(raddWeight));

                String currentRef = h.referenceProperty().get();
                h.referenceProperty().set(currentRef + " (Increased by Radd)");
            }
        } else {
            for (Heir h : bloodHeirs) {
                h.setShare(new Fraction(h.getShare().getNumerator(), bloodNumeratorSum));

                String currentRef = h.referenceProperty().get();
                h.referenceProperty().set(currentRef + " (Increased by Radd)");
            }
        }
    }

    private String getQuranicReference(HeirType type) {
        return switch (type) {
            case HUSBAND -> "Surah An-Nisa 4:12 (1/4 or 1/2 share)";
            case WIFE -> "Surah An-Nisa 4:12 (1/8 or 1/4 share)";
            case FATHER -> "Surah An-Nisa 4:11 (1/6 fixed)";
            case MOTHER -> "Surah An-Nisa 4:11 (1/6 or 1/3 share)";
            case DAUGHTER -> "Surah An-Nisa 4:11 (Fixed or Residue)";
            case SON -> "Surah An-Nisa 4:11 (2:1 Ratio)";
            case FULL_SISTER, PATERNAL_SISTER -> "Surah An-Nisa 4:176";
            default -> "—";
        };
    }
}