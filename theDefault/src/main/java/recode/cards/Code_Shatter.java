package recode.cards;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnReceivePowerPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import recode.DefaultMod;
import recode.characters.Recode;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static recode.DefaultMod.makeCardPath;
import static recode.characters.Recode.Enums.COLOR_ORANGE;
import static recode.characters.Recode.Enums.DEBUFF_STRIKE;

public class Code_Shatter extends AbstractDynamicCard {
    final AbstractPlayer user = AbstractDungeon.player;


    public static final String ID = DefaultMod.makeID ( Code_Shatter.class.getSimpleName () );

    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLOR_ORANGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/


    public Code_Shatter () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = 1;

        this.tags.add (DEBUFF_STRIKE);
    }


    // Actions the card should do.
    @Override
    public void use ( AbstractPlayer p , AbstractMonster m) {
            addToBot ( new DamageAllEnemiesAction ( p , damage , damageTypeForTurn , AbstractGameAction.AttackEffect.LIGHTNING ) );
            addToBot ( new ApplyPowerAction ( p , p , new VulnerablePower ( p , magicNumber , false ) , this.magicNumber ) );
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            addToBot ( new ApplyPowerAction (mo, mo, new VulnerablePower ( mo , magicNumber , false ) , this.magicNumber ) );
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
