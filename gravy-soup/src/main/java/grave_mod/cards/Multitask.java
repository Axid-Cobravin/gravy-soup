package grave_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class Multitask
extends CustomCard {
    public static final String ID = "Multitask";
    public static final String NAME = "Multitask";
    public static final String DESCRIPTION = "Apply !M! Weak and !M! Vulnerable to all enemies. Gain !M! " +
    		"Strength and !M! Dexterity. NL Exhaust.";
    public static final String IMG_PATH = null;
    private static final int COST = 2;
    private static final int MIRRORED_VALUE = 1;
    private static final int UPGRADE_PLUS_MIRRORED_VALUE = 1;

    public Multitask() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, EnumPatch.CYAN,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ALL);
        this.baseMagicNumber = MIRRORED_VALUE;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false), 
        			1, true, AbstractGameAction.AttackEffect.NONE));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1,
        			false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 
        		this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 
        		this.magicNumber), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Multitask();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_MIRRORED_VALUE);
        }
    }
}