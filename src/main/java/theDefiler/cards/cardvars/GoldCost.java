package theDefiler.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theDefiler.cards.AbstractDefilerCard;
import theDefiler.cards.AbstractEasyCard;

import static theDefiler.DefilerMod.makeID;

public class GoldCost extends DynamicVariable {

    @Override
    public String key() {
        return makeID("gc");
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if (abstractCard instanceof AbstractDefilerCard) {
            return ((AbstractDefilerCard) abstractCard).isGoldCostModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if (abstractCard instanceof AbstractDefilerCard) {
            return ((AbstractDefilerCard) abstractCard).goldCostForTurn;
        }
        return -1;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if (abstractCard instanceof AbstractDefilerCard) {
            return ((AbstractDefilerCard) abstractCard).goldCost;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if (abstractCard instanceof AbstractDefilerCard) {
            return ((AbstractDefilerCard) abstractCard).upgradedGoldCost;
        }
        return false;
    }
}