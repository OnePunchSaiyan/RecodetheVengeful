package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import recode.DefaultMod;
import recode.characters.Recode;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Buffer_Breaker extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Buffer_Breaker.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 0;

    // /STAT DECLARATION/


    public Buffer_Breaker () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 3;
        this.secondMagicNumber = baseSecondMagicNumber = 1;

        this.tags.add ( DEBUFF_STRIKE );
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot(new LoseHPAction(p,p, magicNumber));
        addToBot(new ApplyPowerAction (p,p, new BufferPower(p, secondMagicNumber), secondMagicNumber));
        addToBot ( new DamageAction ( m , new DamageInfo ( p , damage , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );

    }


    // Upgraded stats.
    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeDamage ( UPGRADE_PLUS_DMG );
            upgradeBaseCost ( UPGRADED_COST );
            upgradeMagicNumber (-2);
            initializeDescription ();
        }
    }
}
