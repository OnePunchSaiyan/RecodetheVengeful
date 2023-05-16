package recode.powers.stances;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SkillCardReductionPower extends AbstractPower {
    private static final String POWER_ID = "SkillCardPowerReduction";
    private static final String NAME = "Skill Card Power Reduction";
    private static final String DESCRIPTION = "Skill cards cost 1 less until a skill card is played for the turn.";

    public SkillCardReductionPower () {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = -1; // Placeholder value, to be updated later
        this.description = DESCRIPTION;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("energized_green");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.SKILL) {
            if (this.amount == -1) {
                this.amount = 1;
            }
            else {
                this.amount++;
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            resetPower();
        }
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.type == AbstractCard.CardType.SKILL) {
            resetPower();
        }
    }

    private void resetPower() {
        this.amount = 0;
        CardGroup hand = AbstractDungeon.player.hand;
        for (AbstractCard card : hand.group) {
            if (card.type == AbstractCard.CardType.SKILL) {
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




