package recode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.defect.GashAction;
import recode.cards.AbstractBashClaw;

@SpirePatch ( clz = GashAction.class, method = "update")
public class BinaryBashUsePatch {
    @SpirePrefixPatch
    public static void Prefix(GashAction _instance) {
        AbstractBashClaw.BashInc ( _instance.amount, true);
        }
    }

