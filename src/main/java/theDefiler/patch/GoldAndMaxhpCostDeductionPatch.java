package theDefiler.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDefiler.cards.AbstractDefilerCard;

@SpirePatch(clz = AbstractPlayer.class, method = "useCard")
public class GoldAndMaxhpCostDeductionPatch {

    //@SpireInsertPatch(locator = Locator.class)
    public static void Postfix(AbstractPlayer p, AbstractCard c, AbstractMonster m, int energyonuse) {
        if (!c.freeToPlayOnce && c instanceof AbstractDefilerCard) {
            AbstractDefilerCard card = (AbstractDefilerCard)c;

            if (card.goldCostForTurn > 0) {
                AbstractDungeon.player.loseGold(card.goldCostForTurn);;
            }
            if (card.maxhpCostForTurn > 0) {
                AbstractDungeon.player.decreaseMaxHealth(card.maxhpCostForTurn);
                if (AbstractDungeon.player.currentHealth > AbstractDungeon.player.maxHealth)
                    AbstractDungeon.player.maxHealth = AbstractDungeon.player.currentHealth;
            }
        }
    }

    /*
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher matcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "cardInUse");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }
    */

}