package theDefiler.cards.defiler;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDefiler.actions.GainGoldDefilerAction;
import theDefiler.cards.AbstractDefilerCard;

import static theDefiler.DefilerMod.makeID;

public class Diversion extends AbstractDefilerCard {
    public final static String ID = makeID(Diversion.class.getSimpleName());
    // intellij stuff power, self, uncommon

    private static final int COST = 1;
    private static final int GOLD_COST = 5;

    public Diversion() {
        super(ID, COST, GOLD_COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 9;
        magicNumber = baseMagicNumber = 30;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
        draw();
    }

    @Override
    public void drafted() {
        AbstractDungeon.player.gainGold(magicNumber);
    }



    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(10);
    }
}
