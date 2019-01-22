package defaultmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.ui.panels.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;

import defaultmod.powers.CommonPower;

public class UncommonPowerAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int magicNumber;
    private AbstractPlayer p;
    private int energyOnUse;
    private boolean upgraded;

    public UncommonPowerAction(final AbstractPlayer p, final AbstractMonster m,
            final int magicNumber, final boolean upgraded,
            final DamageInfo.DamageType damageTypeForTurn, final boolean freeToPlayOnce,
            final int energyOnUse)

    {
        freeToPlayOnce = false;
        energyOnUse = -1;
        p = p;
        magicNumber = magicNumber;
        freeToPlayOnce = freeToPlayOnce;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.SPECIAL;
        energyOnUse = energyOnUse;
        upgraded = upgraded;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        if (upgraded) {
            ++effect;
        }
        if (effect > 0) {
            for (int i = 0; i < effect; ++i) {

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                        new CommonPower(p, p, magicNumber), magicNumber,
                        AttackEffect.BLUNT_LIGHT));

            }
            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        isDone = true;
    }
}
