package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Defragment_Destruction extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Defragment_Destruction.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 28;
    private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/


    public Defragment_Destruction () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 3;
        this.exhaust = true;

        this.tags.add ( DEBUFF_STRIKE );
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot ( new DamageAction ( m , new DamageInfo ( p , damage , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );
        addToBot (new MakeTempCardInDrawPileAction ( new Fragment(), magicNumber, true, true));
    }


    // Upgraded stats.
    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeDamage ( UPGRADE_PLUS_DMG );
            upgradeBaseCost ( UPGRADED_COST );
            upgradeMagicNumber(2);
            initializeDescription ();
        }
    }
}
