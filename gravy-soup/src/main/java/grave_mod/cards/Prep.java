package grave_mod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class Prep extends CustomCard {
	
    public static final String ID = "Prep";
    public static final String NAME = "Prep";
    public static final String DESCRIPTION = "Draw !M! card and gain [E] next turn.";
    public static final String UPGRADE_DESCRIPTION = "Draw !M! cards and gain [E][E] energy next turn.";
    public static final String IMG_PATH = null;
    private static final int COST = 1;
    private static final int GAIN = 1;
    private static final int UPGRADE_PLUS_GAIN = 1;
    
    public Prep() {
    	super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, EnumPatch.CYAN,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
    	this.rawDescription = DESCRIPTION;
        this.initializeDescription();
        this.baseMagicNumber = GAIN;
        this.magicNumber = this.baseMagicNumber;
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergizedPower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
	}
	
    @Override
    public AbstractCard makeCopy() {
        return new Prep();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_GAIN);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
