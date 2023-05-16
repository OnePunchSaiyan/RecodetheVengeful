package recode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;
import recode.characters.Recode;
import recode.powers.stances.AttackPowerReductionPower;

import static recode.DefaultMod.makeCardPath;

public class Vengeful_Vigor extends AbstractDynamicCard {

    public final static String ID = DefaultMod.makeID ( Vengeful_Vigor.class.getSimpleName () );
    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Recode.Enums.COLOR_ORANGE;
    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int UPGRADE_PLUS_BLOCK = 0;


    public Vengeful_Vigor () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        addToBot ( new GainBlockAction ( p , p , block ) );
        addToBot (new ApplyPowerAction (p,p, new AttackPowerReductionPower( p, this.magicNumber)));
    }



    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeBlock ( UPGRADE_PLUS_BLOCK );
            initializeDescription ();
        }
    }
}
