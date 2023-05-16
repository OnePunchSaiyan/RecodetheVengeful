package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;
import recode.characters.Recode;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Corrution_Cleave extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Corrution_Cleave.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = -1;
    private static final int UPGRADED_COST = -1;

    private static final int DAMAGE = 16;
    private static final int UPGRADE_PLUS_DMG = 4;

    // /STAT DECLARATION/


    public Corrution_Cleave () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 5;
        isMultiDamage = true;

        this.tags.add ( DEBUFF_STRIKE );
    }
    @Override
    public void calculateCardDamage ( AbstractMonster mo ) {
        super.calculateCardDamage ( mo );

        int energyAmount = AbstractDungeon.player.energy.energy;

        if(mo != null && mo.hasPower( "DEBUFF" )){
            damage += magicNumber;
            damage *= energyAmount;

        } else {
            damage *= energyAmount;
        }
        isDamageModified = damage != baseDamage;
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot ( new DamageAction ( m , new DamageInfo ( p , damage , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );
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
