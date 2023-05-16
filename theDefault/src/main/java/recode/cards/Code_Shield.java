package recode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;
import recode.characters.Recode;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;
import static recode.DefaultMod.makeCardPath;

public class Code_Shield extends AbstractDynamicCard {

    public final static String ID = DefaultMod.makeID ( Code_Shield.class.getSimpleName () );
    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = SKILL;
    public static final CardColor COLOR = Recode.Enums.COLOR_ORANGE;
    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 0;


    public Code_Shield () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseBlock = BLOCK;
        this.magicNumber = baseMagicNumber = 4;
    }

    @Override
    public void use ( AbstractPlayer p , AbstractMonster m ) {
        if (p.hasPower("DEBUFF")){
            AbstractDungeon.actionManager.addToBottom ( new GainBlockAction ( p, p, magicNumber ) );
        }
        AbstractDungeon.actionManager.addToBottom ( new GainBlockAction ( p , p , block ) );
    }

    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeBlock ( UPGRADE_PLUS_BLOCK );
            upgradeMagicNumber (4);
            initializeDescription ();
        }
    }
}
