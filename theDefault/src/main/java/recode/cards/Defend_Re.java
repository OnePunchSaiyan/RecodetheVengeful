package recode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;
import recode.characters.Recode;

import static recode.DefaultMod.makeCardPath;

public class Defend_Re extends AbstractDynamicCard {

    public final static String ID = DefaultMod.makeID ( Defend_Re.class.getSimpleName () );
    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Recode.Enums.COLOR_ORANGE;
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    public Defend_Re () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseBlock = BLOCK;

        this.tags.add (CardTags.STARTER_DEFEND);
    }

    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        AbstractDungeon.actionManager.addToBottom ( new GainBlockAction ( p , p , block ) );
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
