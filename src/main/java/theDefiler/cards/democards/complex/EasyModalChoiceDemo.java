package theDefiler.cards.democards.complex;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theDefiler.actions.EasyModalChoiceAction;
import theDefiler.cards.AbstractEasyCard;
import theDefiler.cards.EasyModalChoiceCard;

import java.util.ArrayList;

import static theDefiler.DefilerMod.makeID;
import static theDefiler.util.Wiz.*;

@AutoAdd.Ignore
public class EasyModalChoiceDemo extends AbstractEasyCard {
    public final static String ID = makeID("EasyModalChoiceDemo");
    // intellij stuff skill, self, uncommon, , , , , , 

    public EasyModalChoiceDemo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("Draw", "Draw " + magicNumber + " cards.", () -> att(new DrawCardAction(magicNumber))));
        easyCardList.add(new EasyModalChoiceCard("Strength", "Gain " + secondMagic + " Strength.", () -> applyToSelfTop(new StrengthPower(p, secondMagic))));
        addtb(new EasyModalChoiceAction(easyCardList));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}