package grave_mod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class Hardhead
extends CustomCard {
    public static final String ID = "Hardhead";
    public static final String NAME = "Hardhead";
    public static final String DESCRIPTION = "Gain !B! block !M! times.";
    public static final String IMG_PATH = null;
    private static final int COST = 1;
    private static final int BLOCK = 3;
    private static final int TIMES = 3;
    private static final int UPGRADE_PLUS_TIMES = 1;

    public Hardhead() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, EnumPatch.CYAN,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = TIMES;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    	AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    	AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    	if (this.upgraded) {
    		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    	}
    }

    @Override
    public AbstractCard makeCopy() {
        return new Hardhead();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_TIMES);
        }
    }
}