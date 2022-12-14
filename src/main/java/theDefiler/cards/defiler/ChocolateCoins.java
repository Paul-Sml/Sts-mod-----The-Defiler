package theDefiler.cards.defiler;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDefiler.actions.GainGoldDefilerAction;
import theDefiler.actions.GainMaxhpAction;
import theDefiler.cards.AbstractDefilerCard;

import static theDefiler.DefilerMod.makeID;

public class ChocolateCoins extends AbstractDefilerCard {
    public final static String ID = makeID(ChocolateCoins.class.getSimpleName());
    // intellij stuff power, self, uncommon

    private static final int COST = 1;

    public ChocolateCoins() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 5;
        magicNumber = baseMagicNumber = 25;
        secondMagic = baseSecondMagic = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
        atb(new GainGoldDefilerAction(magicNumber));
        atb(new GainMaxhpAction(secondMagic));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
