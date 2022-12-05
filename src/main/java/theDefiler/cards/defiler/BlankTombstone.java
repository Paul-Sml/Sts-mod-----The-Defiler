package theDefiler.cards.defiler;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDefiler.actions.BlankTombstoneAction;
import theDefiler.actions.DefilerDigAction;
import theDefiler.cards.AbstractDefilerCard;

import java.util.Arrays;
import java.util.List;

import static theDefiler.DefilerMod.makeID;

public class BlankTombstone extends AbstractDefilerCard {
    public final static String ID = makeID(BlankTombstone.class.getSimpleName());
    // intellij stuff power, self, uncommon

    private static final int COST = 1;

    public BlankTombstone() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 4;
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DefilerDigAction(magicNumber, c -> c.color == CardColor.COLORLESS));
        block();
        System.out.println(p.hand.size());
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                atb(new BlankTombstoneAction());
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeBlock(2);
    }

    public List<TooltipInfo> getCustomTooltips() {
        UIStrings strings = CardCrawlGame.languagePack.getUIString("thedefilermod:Dig");
        return Arrays.asList(new TooltipInfo(strings.TEXT[0], strings.TEXT[1] + magicNumber + strings.TEXT[2] + cardStrings.EXTENDED_DESCRIPTION[0] + strings.TEXT[3]
        ));
    }
}
