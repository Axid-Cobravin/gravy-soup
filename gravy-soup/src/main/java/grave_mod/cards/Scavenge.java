package grave_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import grave_mod.EnumPatch;

public class Scavenge extends AbstractCard {
	
    public static final String ID = "Scavenge";
    public static final String NAME = "Scavenge";
    public static final String DESCRIPTION = "Deal !D! damage. If it kills an enemy, draw 1 card and gain 1 energy.";
    public static final String IMG_PATH = null;
    private static final int COST = 1;
    private static final int DMG = 10;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int GAIN = 1;
    private static final int POOL = 1;
    
    public Scavenge() {
    	super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, EnumPatch.CYAN,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
        this.baseDamage = DMG;
        this.baseMagicNumber = GAIN;
        this.magicNumber = this.baseMagicNumber;
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.NONE));
    	if ((m.isDying) || (m.currentHealth <= 0)) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));  
    	}
	}
	
    @Override
    public AbstractCard makeCopy() {
        return new Scavenge();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
