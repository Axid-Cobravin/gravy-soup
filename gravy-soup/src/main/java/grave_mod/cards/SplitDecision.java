package grave_mod.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import grave_mod.EnumPatch;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

public class SplitDecision extends CustomCard implements ModalChoice.Callback {
    public static final String ID = "Split Decision";
    public static final String NAME = "Split Decision";
    public static final String DESCRIPTION = "Deal !D! damage. Gain !B! block. Divergent.";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final int DMG = 5;
    private static final int BLOCK = 5;
    private static final int UPGRADE = 2;
    private ModalChoice modal;

    public SplitDecision() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, EnumPatch.CYAN, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseDamage = DMG;
        this.baseBlock = BLOCK;
        this.damage = this.baseDamage;
        this.block = this.baseBlock;
        
        modal = new ModalChoiceBuilder()
                .setCallback(this) // Sets callback of all the below options to this
                .setColor(EnumPatch.CYAN) // Sets color of any following cards to red
                .addOption("Option 1", "+2 Damage", CardTarget.NONE)
                .addOption("Option 2", "+2 Block", CardTarget.NONE)
                .create();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    	if ((p != null) && (m != null)) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
        }
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.WaitAction(1.0f));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.NONE));
    	
        modal.open();
    }

    // This is called when one of the option cards us chosen
    @Override
    public void optionSelected(AbstractPlayer p, AbstractMonster m, int i) {
       int damageBlock;
        switch (i) {
            case 0:
                damageBlock = 0;
                break;
            case 1:
                damageBlock = 1;
                break;
            default:
                return;
        }

        if (damageBlock == 0) {
        	this.upgradeDamage(UPGRADE);
        	
        }
        if (damageBlock == 1) {
        	this.upgradeBlock(UPGRADE);
        }
    	this.upgraded = true;
    	this.timesUpgraded++;
    	this.name = (NAME + "+" + this.timesUpgraded);
    	initializeTitle();
    }

    @Override
    public void upgrade()
    {
        if (!upgraded) {
            upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new SplitDecision();
    }
}