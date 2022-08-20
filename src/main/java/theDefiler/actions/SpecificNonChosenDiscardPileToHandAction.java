package theDefiler.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SpecificNonChosenDiscardPileToHandAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard cardToReturn;

    public SpecificNonChosenDiscardPileToHandAction(AbstractCard c) {
        this.p = AbstractDungeon.player;
        this.cardToReturn = c;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.p.hand.size() >= BaseMod.MAX_HAND_SIZE || p.hand.contains(cardToReturn)) {
            this.isDone = true;
        } else {
            if (this.p.hand.size() < BaseMod.MAX_HAND_SIZE) {
                this.p.hand.addToHand(cardToReturn);
                this.p.discardPile.removeCard(cardToReturn);
            }

            cardToReturn.lighten(false);
            this.p.hand.refreshHandLayout();
            this.isDone = true;
        }
        this.tickDuration();
    }
}