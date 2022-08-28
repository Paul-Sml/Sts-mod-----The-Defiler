package theDefiler.cards.defiler;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDefiler.actions.GainMaxhpAction;
import theDefiler.actions.HuntingKnifeAction;
import theDefiler.actions.SpecificNonChosenDiscardPileToHandAction;
import theDefiler.actions.SpecificNonChosenDrawPileToHandAction;
import theDefiler.cards.AbstractDefilerCard;

import static theDefiler.DefilerMod.makeID;

public class HuntingKnife extends AbstractDefilerCard {
    public final static String ID = makeID(HuntingKnife.class.getSimpleName());
    // intellij stuff power, self, uncommon

    private static final int COST = 2;

    public HuntingKnife() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 2;
        baseDamage = 13;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HuntingKnifeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public void drafted() {
        AbstractDungeon.player.increaseMaxHp(magicNumber, true);
    }

    public void upp() {
        upgradeDamage(5);
        upgradeMagicNumber(1);
    }
}
