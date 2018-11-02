package grave_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class HastySearch
extends CustomCard {
    public static final String ID = "Hasty Search";
    public static final String NAME = "Hasty Search";
    public static final String DESCRIPTION = "Draw 3 cards. Gain 2 Vulnerable.";
    public static final String IMG_PATH = null;
    private static final int COST = 1;
    private static final int COST_UPGRADE = 0;
    private static final int DRAW = 3;
    private static final int VULNERABLE = 2;
    private static final int POOL = 1;

    public HastySearch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, EnumPatch.CYAN,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, DRAW));
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, VULNERABLE, false), 
    			VULNERABLE, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HastySearch();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(COST_UPGRADE);
        }
    }
}