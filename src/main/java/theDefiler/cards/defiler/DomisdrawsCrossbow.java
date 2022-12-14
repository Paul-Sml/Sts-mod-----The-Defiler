package theDefiler.cards.defiler;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theDefiler.cards.AbstractDefilerCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static theDefiler.DefilerMod.makeID;
import static theDefiler.TheDefiler.Enums.DEFILER_COLOR;

@AutoAdd.NotSeen
public class DomisdrawsCrossbow extends AbstractDefilerCard implements SpawnModificationCard {
    public final static String ID = makeID(DomisdrawsCrossbow.class.getSimpleName());
    // intellij stuff power, self, uncommon

    private static final int COST = 1;

    public DomisdrawsCrossbow() {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 19;
        baseBlock = 13;
        magicNumber = baseMagicNumber = 5;
        secondMagic = baseSecondDamage = 1;
//        setLocked();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dig(magicNumber, c -> c.color != DEFILER_COLOR);
        block();
        atb(new RemoveAllBlockAction(m, p));
        dmg(m);
        this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), secondMagic));
    }

    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        return false;
    }

    public void upp() {
        upMagic(3);
        upgradeDamage(5);
        upgradeBlock(3);
        upSecondMagic(1);
    }

    public List<TooltipInfo> getCustomTooltips() {
        UIStrings strings = CardCrawlGame.languagePack.getUIString("thedefilermod:Dig");
        return Arrays.asList(new TooltipInfo(strings.TEXT[0], strings.TEXT[1] + magicNumber + strings.TEXT[2] + cardStrings.EXTENDED_DESCRIPTION[0] + strings.TEXT[3]
        ));
    }
}
