package recode.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import recode.DefaultMod;
import recode.characters.Recode;

import static recode.DefaultMod.makeCardPath;

public class Data_Drive extends AbstractDynamicCard {

    public final static String ID = DefaultMod.makeID ( Data_Drive.class.getSimpleName () );
    public static final String IMG = makeCardPath ( "recodeplacehold.png" );


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Recode.Enums.COLOR_ORANGE;
    private static final int COST = 1;
    private static final int UPGRADE_PLUS_COST = 0;
    private static final int DRAW = 2;
    private static final int UPGRADE_PLUS_BLOCK = 0;


    public Data_Drive () {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        baseDraw = DRAW ;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, DRAW));
        if (m != null && m.getIntentBaseDmg() >= 0) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction (p, magicNumber));
        }
    }

    @Override
    public void upgrade () {
        if (!upgraded) {
            upgradeName ();
            upgradeBlock ( UPGRADE_PLUS_BLOCK );
            upgradeBaseCost ( UPGRADE_PLUS_COST );
            upgradeMagicNumber (1);
            initializeDescription ();
        }
    }
}
