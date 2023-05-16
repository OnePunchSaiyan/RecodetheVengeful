package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import recode.DefaultMod;
import recode.characters.Recode;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Debug_Drive extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Debug_Drive.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 4;

    // /STAT DECLARATION/


    public Debug_Drive () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 2;

        this.tags.add ( DEBUFF_STRIKE );
    }


    // Actions the card should do.

    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot ( new DamageAllEnemiesAction ( p , DamageInfo.createDamageMatrix ( damage , true ) , damageTypeForTurn , AbstractGameAction.AttackEffect.SLASH_HEAVY ) );
        for (AbstractMonster enemy : AbstractDungeon.getCurrRoom ().monsters.monsters) {
            if (enemy != null && !enemy.isDeadOrEscaped ()) {
                addToBot ( new ApplyPowerAction ( enemy , p , new VulnerablePower ( enemy , magicNumber , false ) , magicNumber ) );
            }
        }
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

