package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import recode.DefaultMod;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Trojen_Torment extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Trojen_Torment.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 18;
    private static final int UPGRADE_PLUS_DMG = 3;

    // /STAT DECLARATION/


    public Trojen_Torment () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 1;
        this.secondMagicNumber = baseSecondMagicNumber = 1;

        this.tags.add ( DEBUFF_STRIKE );
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot ( new DamageAction ( m , new DamageInfo ( p , damage , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );
        addToBot(new ApplyPowerAction (m, p, new WeakPower (m, magicNumber, false), magicNumber));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower (m, secondMagicNumber, false), secondMagicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeDamage ( UPGRADE_PLUS_DMG );
            upgradeBaseCost ( UPGRADED_COST );
            upgradeMagicNumber (1);
            upgradeSecondMagicNumber (1);
            initializeDescription ();
        }
    }
}
