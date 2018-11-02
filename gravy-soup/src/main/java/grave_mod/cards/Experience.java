package grave_mod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class Experience
extends CustomCard {
    public static final String ID = "Experience";
    public static final String NAME = "Experience";
    public static final String DESCRIPTION = "Gain !M! strength and !M! dexterity.";
    public static final String IMG_PATH = null;
    private static final int COST = 2;
    private static final int MIRRORED = 1;
    private static final int MIRRORED_PLUS_UPGRADE = 1;
    private static final int POOL = 1;

    public Experience() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, EnumPatch.CYAN,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, POOL);
        this.baseMagicNumber = MIRRORED;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 
        		this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 
        		this.magicNumber), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Experience();
    }

    public void upgrade() {
    	upgradeMagicNumber(MIRRORED_PLUS_UPGRADE);
    	this.upgraded = true;
    	this.timesUpgraded++;
    	this.name = (NAME + "+" + this.timesUpgraded);
    	initializeTitle();
    }
    
    public boolean canUpgrade() {
    	return true;
    }
}