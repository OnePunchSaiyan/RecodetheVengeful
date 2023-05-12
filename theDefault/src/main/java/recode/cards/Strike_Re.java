package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;



import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
public class Strike_Re extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID (Strike_Re.class.getSimpleName());

    public static final String IMG = makeCardPath ( "Strike_Re.png" );


    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 0; // UPGRADED_COST = 0

    private static final int DAMAGE = 6;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 9;  // UPGRADE_PLUS_DMG = 9

    // /STAT DECLARATION/


    public Strike_Re () { // public Strike_Re() - This one and the one right under the imports are the most important ones, don't forget them
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        AbstractDungeon.actionManager.addToBottom (
                new DamageAction ( m , new DamageInfo ( p , damage , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );
    }


    // Upgraded stats.
    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeDamage ( UPGRADE_PLUS_DMG );
            upgradeBaseCost ( UPGRADED_COST );
            initializeDescription ();
        }
    }
}
