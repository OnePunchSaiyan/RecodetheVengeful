package recode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;

import static recode.characters.Recode.Enums.BINARY_BASH;

@SpirePatch ( clz = Claw.class, method = "<ctor>")
public class BinaryBash_ClawPatch {
    public static void Postfix( AbstractCard __instance){
        __instance.tags.add(BINARY_BASH);
    }

}
