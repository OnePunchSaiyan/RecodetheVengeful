package recode.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Cache_Crash extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID ( Cache_Crash.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DMG = 0;

    // /STAT DECLARATION/


    public Cache_Crash () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = 3;

        this.tags.add ( DEBUFF_STRIKE );
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int debuffCount = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom ().monsters.monsters) {
            if (mo != null && !mo.isDeadOrEscaped () && p.hasPower ( "DEBUFF" ))
                debuffCount++;

        }

        addToBot ( new DamageAllEnemiesAction ( p , multiDamage , damageTypeForTurn , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL ) );

        if (debuffCount > 0) {
            for (AbstractMonster enemy : AbstractDungeon.getCurrRoom ().monsters.monsters) {
                if (m != null && !enemy.isDeadOrEscaped ())
                    addToBot ( new DamageAction ( enemy , new DamageInfo ( p , magicNumber , damageTypeForTurn ) , AbstractGameAction.AttackEffect.SLASH_DIAGONAL ) );
            }
        }
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractCreature p : AbstractDungeon.getCurrRoom ().monsters.monsters) {
            if (p.hasPower("DEBUFF")) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
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
            upgradeMagicNumber (3);
            initializeDescription ();
        }
    }
}
