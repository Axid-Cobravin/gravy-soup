package grave_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class TailSwipe
extends CustomCard {
    public static final String ID = "Tail_Swipe";
    public static final String NAME = "Tail Swipe";
    public static final String DESCRIPTION = "Deal !D! Damage and apply !M! Weak to all enemies.";
    public static final String IMG_PATH = null;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int WEAK_LEVEL = 1;
    private static final int UPGRADE_PLUS_WEAK_LEVEL = 1;

    public TailSwipe() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, EnumPatch.CYAN,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = WEAK_LEVEL;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage,
    			this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false), 
        			this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TailSwipe();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_WEAK_LEVEL);
        }
    }
}