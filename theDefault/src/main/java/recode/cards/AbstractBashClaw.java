package recode.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;
import recode.characters.Recode;



import java.util.ArrayList;


import static recode.characters.Recode.Enums.BINARY_BASH;

public abstract class AbstractBashClaw extends AbstractDynamicCard {
    public AbstractBashClaw(String id, String img, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target){
        super(id, img, cost, type, color, rarity, target);
    }




        public static void BashInc(int amount){
            BashInc(amount, false);}

        public static void BashInc(int amount, boolean excludeBash){
            ArrayList<AbstractCard> cards = new ArrayList<> ();
            cards.addAll( AbstractDungeon.player.hand.group);
            cards.addAll( AbstractDungeon.player.drawPile.group);
            cards.addAll( AbstractDungeon.player.discardPile.group);
            for (AbstractCard x : cards) {
                if ((!excludeBash || x == null) && x.hasTag(BINARY_BASH) && x.baseDamage >= 0) {
                    x.baseDamage += amount;
                    x.applyPowers ();
                    if (AbstractDungeon.player.hand.group.contains ( x ))
                        x.flash( Color.BLUE.cpy ());

                }
            }


        }

}
