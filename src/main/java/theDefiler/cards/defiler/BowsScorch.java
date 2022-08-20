package theDefiler.cards.defiler;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDefiler.cards.AbstractDefilerCard;

import static theDefiler.DefilerMod.makeID;

public class BowsScorch extends AbstractDefilerCard {
    public final static String ID = makeID(BowsScorch.class.getSimpleName());
    // intellij stuff power, self, uncommon
    private static AbstractCard c = new DomisdrawsBow();
    private static final int COST = -2;

    public BowsScorch() {
        super(ID, COST, CardType.CURSE, CardRarity.CURSE, CardTarget.NONE);
        cardToPreview.add(c);
        cardToPreview.add(new Burn());
    }

    @Override
    public void onRemoveFromMasterDeck() {
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractCard c : p.masterDeck.group) {
            p.masterDeck.removeCard(DomisdrawsBow.ID);
            break;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public void dug() {
        atb(new MakeTempCardInHandAction(new Burn(), 2));
    }

    public void upp() {}
}
