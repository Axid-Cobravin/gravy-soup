package grave_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class GutsyBite
extends CustomCard {
    public static final String ID = "Gutsy Bite";
    public static final String NAME = "Gutsy Bite";
    public static final String DESCRIPTION = "If you have half HP or less, deal !D! damage.";
    public static final String EXTENDED_DESCRIPTION = "I can't play this card.";
    public static final String IMG_PATH = null;
    private static final int COST = 0;
    private static final int ATTACK_DMG = 18;
    private static final int ATTACK_PLUS_UPGRADE_DMG = 6;
    
    public GutsyBite() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, EnumPatch.CYAN,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
    			new DamageInfo(p, this.damage, this.damageTypeForTurn),
    	    	AbstractGameAction.AttackEffect.NONE));
    }
    
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
	  {
	    boolean canUse = super.canUse(p, m);
	    if (!canUse) {
	      return false;
	    }
	    if (p.currentHealth > p.maxHealth / 2)
	    {
	      canUse = false;
	      this.cantUseMessage = EXTENDED_DESCRIPTION;
	    }
	    return canUse;
	  }
    @Override
    public AbstractCard makeCopy() {
        return new GutsyBite();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(ATTACK_PLUS_UPGRADE_DMG);
        }
    }
}