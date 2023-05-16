package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import recode.DefaultMod;
import recode.characters.Recode;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Spyware_Slam extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Spyware_Slam.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 13;
    private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/


    public Spyware_Slam () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 2;
        this.secondMagicNumber = baseSecondMagicNumber = 1;

        this.tags.add ( DEBUFF_STRIKE );
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot(new ApplyPowerAction (m, p, new WeakPower (m, magicNumber, false), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, secondMagicNumber, false), secondMagicNumber));
        addToBot ( new DamageAction ( m , new DamageInfo ( p , damage , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );
    }


    // Upgraded stats.
    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeDamage ( UPGRADE_PLUS_DMG );
            upgradeBaseCost ( UPGRADED_COST );
            upgradeSecondMagicNumber ( -1 );
            initializeDescription ();
        }
    }
}
