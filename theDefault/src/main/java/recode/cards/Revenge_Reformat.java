package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Revenge_Reformat extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Revenge_Reformat.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 4;

    // /STAT DECLARATION/


    public Revenge_Reformat () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 6;

        this.tags.add ( DEBUFF_STRIKE );
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot ( new DamageAction ( m , new DamageInfo ( p , damage , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );
        if (m != null && (m.hasPower ( "Artifact" ) || m.hasPower ( "Barricade" ) || m.hasPower ( "Intangible" )
                || m.hasPower ( "Metallicize" ) || m.hasPower ( "Plated Armor" ) || m.hasPower ( "Regen" )
                || m.hasPower ( "Ritual" ) || m.hasPower ( "Thorns" ))) {
            addToBot ( new DamageAction ( m , new DamageInfo ( p , magicNumber , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_DIAGONAL ) );
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeDamage ( UPGRADE_PLUS_DMG );
            upgradeBaseCost ( UPGRADED_COST );
            upgradeMagicNumber (2);
            initializeDescription ();
        }
    }
}
