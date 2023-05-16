package recode.powers.stances;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AttackPowerReductionPower extends AbstractPower {
    private static final String POWER_ID = "AttackCardPowerReduction";
    private static final String NAME = "Attack Card Power Reduction";
    private static final String DESCRIPTION = "Attack cards cost 1 less until an attack card is played for the turn.";

    public AttackPowerReductionPower ( AbstractPlayer p , int magicNumber ) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = -1; // Placeholder value, to be updated later
        this.description = DESCRIPTION;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("energized_red");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (this.amount == -1) {
                this.amount = 1;
            } else {
                this.amount++;
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            resetPower();
        }
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.type == AbstractCard.CardType.ATTACK) {
            resetPower();
        }
    }

    private void resetPower() {
        this.amount = 0;
        CardGroup hand = AbstractDungeon.player.hand;
        for (AbstractCard card : hand.group) {
            if (card.type == AbstractCard.CardType.ATTACK) {
                this.amount++;
            }
        }
        if (this.amount > 0) {
            this.amount--;
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount > 0) {
            this.description = DESCRIPTION + " Current reduction: " + this.amount;
        } else {
            this.description = DESCRIPTION;
        }
    }


}

