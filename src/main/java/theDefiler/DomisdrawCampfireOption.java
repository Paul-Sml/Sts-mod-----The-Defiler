package theDefiler;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theDefiler.cards.defiler.*;

import static theDefiler.DefilerMod.makeImagePath;

public class DomisdrawCampfireOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public DomisdrawCampfireOption() {
        this.label = DomisdrawCampfireOption.TEXT[0];
        this.description = DomisdrawCampfireOption.TEXT[1];
        this.img = ImageMaster.loadImage(makeImagePath("ui/DomisdrawCampfireOption.png"));
    }

    @Override
    public void useOption() {
        loseCard(DomisdrawsSpear.ID);
        loseCard(DomisdrawsBow.ID);
        loseCard(DomisdrawsCross.ID);
        loseCard(SpearsShake.ID);
        loseCard(BowsScorch.ID);
        loseCard(CrossDisease.ID);
        UnlockTracker.markCardAsSeen(DomisdrawsCrossbow.ID);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new DomisdrawsCrossbow(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        //AbstractDungeon.effectList.add(new CampfireBurnResetEffect(this));
        this.usable = false;
    }

    private void loseCard (String id) {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(id)) {
                AbstractDungeon.player.masterDeck.removeCard(c);
                break;
            }
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(DefilerMod.makeID(DomisdrawCampfireOption.class.getSimpleName()));
        TEXT = DomisdrawCampfireOption.uiStrings.TEXT;
    }
}
