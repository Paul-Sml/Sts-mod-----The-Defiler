package theDefiler.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import theDefiler.cards.AbstractDefilerCard;
import theDefiler.TheDefiler;
import theDefiler.cards.defiler.Worms;
import theDefiler.powers.*;
import theDefiler.util.Wiz;

import java.util.function.Predicate;

public class DefilerDigAction  extends AbstractGameAction
{
    private final float startingDuration;
//    private AbstractCard ignoredCard;
    private Predicate<AbstractCard> condition;

    public DefilerDigAction(int numCards, Predicate<AbstractCard> digCondition)
    {
        amount = numCards;
        actionType = ActionType.CARD_MANIPULATION;
        startingDuration = Settings.ACTION_DUR_FAST;
        duration = startingDuration;
        condition = digCondition;
    }

//    public DefilerDigAction(int numCards, AbstractCard cardToIgnore)
//    {
//        amount = numCards;
//        actionType = ActionType.CARD_MANIPULATION;
//        startingDuration = Settings.ACTION_DUR_FAST;
//        duration = startingDuration;
//        ignoredCard = cardToIgnore;
//    }

    public DefilerDigAction(int numCards, boolean block)
    {
        amount = numCards;
        actionType = ActionType.CARD_MANIPULATION;
        startingDuration = Settings.ACTION_DUR_FAST;
        duration = startingDuration;
    }

    public void update()
    {
        if(amount > 0)
        {
            PreMill();
            while (amount > 0)
            {
                if (AbstractDungeon.player.drawPile.size() > 0)
                    ProcessMill();
                amount--;
            }
//            PostMill();
            isDone = true;
        } else {isDone = true;}
    }

    private void PreMill()
    {

    }

    private void ProcessMill()
    {
        AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
        if(card != null)
        {
            CheckRebound(card, condition.test(card));
            //return;
        }
    }

    private void CheckRebound(AbstractCard card, boolean isConditionMet)
    {
        if(isConditionMet || card.cardID.equals(Worms.ID))
            Rebound(card);
        else
        {
//            if((card instanceof AbstractDefilerCard && ((AbstractDefilerCard)card).postMillAction))
//                ((AbstractDefilerCard)card).PostMillAction();
            MoveToDiscard(card);
        }
    }

    private void Rebound(AbstractCard card)
    {
        if(AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE)
        {
//            if((card instanceof AbstractDefilerCard && ((AbstractDefilerCard)card).postMillAction))
//                ((AbstractDefilerCard)card).PostMillAction();
            MoveToDiscard(card);
            return;
        }

        AbstractDungeon.player.drawPile.removeCard(card);
        addToTop(new VFXAction(AbstractDungeon.player, new ShowCardAndMillEffect(card, AbstractDungeon.player.hand), Settings.ACTION_DUR_XFAST, true));

//        if((card instanceof AbstractDefilerCard && ((AbstractDefilerCard)card).postMillAction))
//            ((AbstractDefilerCard)card).PostMillAction();
//        ProcessPostMill(card, true);
    }

    private void MoveToDiscard(AbstractCard card)
    {
        AbstractDungeon.player.drawPile.removeCard(card);
        addToTop(new VFXAction(AbstractDungeon.player, new ShowCardAndMillEffect(card, AbstractDungeon.player.discardPile), Settings.ACTION_DUR_XFAST, true));
        AbstractDungeon.player.discardPile.addToTop(card);
//        AbstractDungeon.player.drawPile.moveToDiscardPile(AbstractDungeon.player.drawPile.getTopCard());
//        ProcessPostMill(card, false);
    }

    /*private void PostMill()
    {
        AbstractDungeon.player.hand.applyPowers();
        if(gainBlockForMill && millNum > 0)
            addToBot(new GainBlockAction(AbstractDungeon.player, millNum));
    }*/

//    private void GetBonusVoid()
//    {
//        AbstractPlayer player = AbstractDungeon.player;
//        if(player != null && player.hasPower(VacancyRune.POWER_ID))
//            voidAmount += player.getPower(VacancyRune.POWER_ID).amount;
//    }

//    private void GainVoid()
//    {
//        AbstractPlayer player = AbstractDungeon.player;
//        if(player != null && voidAmount > 0)
//        {
//            addToTop(new VFXAction(player, new InflameEffect(player), .1F));
//            player.addPower(new VoidPower(player, player, voidAmount));
//        }
//    }

//    private void ProcessPostMill(AbstractCard card, boolean rebounded)
//    {
//        PostMillCard(card);
//        PostMillVoidGain();
//    }

//    private void PostMillCard(AbstractCard card)
//    {
//        AbstractPlayer player = AbstractDungeon.player;
//        if(player != null)
//        {
//            if(player.hasPower(CleanseSoulPower.POWER_ID))
//            {
//                if(card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE)
//                {
//                    actionType = ActionType.EXHAUST;
//                    if(player.discardPile.contains(card))
//                    {
//                        player.discardPile.moveToExhaustPile(card);
//                        if(!AbstractDungeon.actionManager.actions.contains(waka))
//                            AbstractDungeon.actionManager.addToBottom(waka);
//                        player.getPower(CleanseSoulPower.POWER_ID).flash();
//                    }
//                }
//            }
//            if(player.hasPower(AquamarinePower.POWER_ID) && player.getPower(AquamarinePower.POWER_ID).amount > 0)
//                addToBot(new GainBlockAction(player, player.getPower(AquamarinePower.POWER_ID).amount));
//            if(player instanceof TheVacant)
//                ((TheVacant)player).millsThisTurn++;
//            if(player.hasPower(ForgeSoulPower.POWER_ID) && card.canUpgrade())
//                card.upgrade();
//        }
//    }

//    private void PostMillVoidGain()
//    {
//        voidAmount++;
//    }



//    private boolean GetSpecialRebound(AbstractCard card)
//    {
//        AbstractPlayer player = AbstractDungeon.player;
//        if(player != null)
//        {
//            if(player.hasPower(RunicThoughtsPower.POWER_ID) && card.type == AbstractCard.CardType.POWER)
//                return true;
//
//            if(player.hasPower(DarkReflexPower.POWER_ID) && millNum <= player.getPower(DarkReflexPower.POWER_ID).amount)
//                return true;
//        }
//        return false;
//    }
}
