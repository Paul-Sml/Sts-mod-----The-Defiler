package theDefiler.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.WingBoots;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theDefiler.DefilerMod;

import java.util.ArrayList;

public class IgnorePathPatch
{
    @SpirePatch(clz = MapRoomNode.class, method = "wingedIsConnectedTo")
    public static class WingedConnectedTo
    {
        @SpireInsertPatch(locator = ConnectLocator.class, localvars = {"edge"})
        public static SpireReturn Insert(MapRoomNode __instance, MapRoomNode node, MapEdge edge)
        {
            if (DefilerMod.canIgnorePath && (node.y == edge.dstY))
            {
                return SpireReturn.Return(true);
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = MapRoomNode.class, method = "update")
    public static class EnterPatch
    {
        @SpireInsertPatch(locator = UpdateLocator.class, localvars = {"normalConnection", "wingedConnection"})
        public static void Insert(MapRoomNode __instance, boolean normalConnection, boolean wingedConnection) {
            if (!normalConnection && wingedConnection && DefilerMod.canIgnorePath) {
                WingBoots wingBoots = (WingBoots) AbstractDungeon.player.getRelic(WingBoots.ID);
                if (wingBoots != null && wingBoots.counter > 0) {
                    wingBoots.counter++;
                }
            }
            DefilerMod.canIgnorePath = false;
        }
    }

    private static class ConnectLocator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(ModHelper.class, "isModEnabled");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }

    private static class UpdateLocator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }

}