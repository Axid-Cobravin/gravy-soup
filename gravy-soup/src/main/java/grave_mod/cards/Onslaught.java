package grave_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class Onslaught
extends CustomCard {
    public static final String ID = "Onslaught";
    public static final String NAME = "Onslaught";
    public static final String DESCRIPTION = "Deal !D! damage. Lose 5 Max HP.";
    public static final String IMG_PATH = null;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 28;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int SELF_DMG = 5;
    private static final int POOL = 1;

    public Onslaught() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, EnumPatch.CYAN,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, POOL);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = SELF_DMG;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	AbstractDungeon.player.decreaseMaxHealth(this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Onslaught();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}