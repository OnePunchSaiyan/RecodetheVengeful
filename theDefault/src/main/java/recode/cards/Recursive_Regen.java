package recode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import recode.DefaultMod;
import recode.characters.Recode;

import static recode.DefaultMod.makeCardPath;

public class Recursive_Regen extends AbstractDynamicCard {
    private static final String ID = DefaultMod.makeID ( Recursive_Regen.class.getSimpleName () );
    private static final String IMG = makeCardPath("recodeplacehold.png");
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Recode.Enums.COLOR_ORANGE;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int REGEN_AMOUNT = 4;

    public Recursive_Regen() {
        super ( ID , IMG , COST , TYPE , COLOR , RARITY , TARGET );
        this.baseMagicNumber = this.magicNumber = REGEN_AMOUNT;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber), this.magicNumber));
    }




    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2); // Increase the base regen amount by 2 when upgraded
            this.upgradeBaseCost ( UPGRADED_COST );
            initializeDescription ();
        }
    }
}
