package grave_mod.cards;

import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import grave_mod.CustomTwoMagicCard;
import grave_mod.EnumPatch;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Divide extends CustomTwoMagicCard implements ModalChoice.Callback {
    public static final String ID = "Divide";
    public static final String NAME = "Divide";
    public static final String DESCRIPTION = "Lose !hydra:M2! HP. Gain !M! Strength. Divergent.";
    private static final int COST = 0;
    private static final int STR_GAIN_BASE = 1;
    private static final int HP_LOSS_BASE = 6;
    private static final int INCREASE_STR = 1;
    private static final int DECREASE_HP_LOSS = -2;
    private static final int UP_INCREASE_STR = 1;
    private static final int UP_DECREASE_HP_LOSS = -2;
    private ModalChoice modal;

    public Divide() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, EnumPatch.CYAN, CardRarity.UNCOMMON,
                CardTarget.ENEMY);

        this.baseMagicNumber = STR_GAIN_BASE;
        this.baseSecondMagicNumber = HP_LOSS_BASE;
        this.magicNumber = baseMagicNumber;
        this.secondMagicNumber = baseSecondMagicNumber;

        modal = new ModalChoiceBuilder().setCallback(this) // Sets callback of all the below options to this
                .setColor(EnumPatch.CYAN) // Sets color of any following cards to red
                .addOption("Option 1", "+1 Strength", CardTarget.NONE)
                .addOption("Option 2", "-2 HP loss", CardTarget.NONE).create();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.secondMagicNumber));
        AbstractDungeon.actionManager
                .addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        modal.open();
    }

    // This is called when one of the option cards us chosen
    @Override
    public void optionSelected(AbstractPlayer p, AbstractMonster m, int i) {
        if (this.secondMagicNumber != 0) {
            switch (i) {
            case 0:
                this.upgradeMagicNumber(INCREASE_STR);
                break;
            case 1:
                this.upgradeSecondMagicNumber(DECREASE_HP_LOSS);
                break;
            default:
                return;
            }
        }
        else {
            this.upgradeMagicNumber(INCREASE_STR);
        }

        this.upgraded = true;
        this.timesUpgraded++;
        this.name = (NAME + "+" + this.timesUpgraded);
        initializeTitle();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeMagicNumber(INCREASE_STR);
            this.upgradeSecondMagicNumber(DECREASE_HP_LOSS);
            upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Divide();
    }
}