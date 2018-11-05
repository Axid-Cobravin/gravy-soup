package grave_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import basemod.abstracts.CustomCard;
import grave_mod.EnumPatch;

public class Scavenge extends CustomCard {

    public static final String ID = "Scavenge";
    public static final String NAME = "Scavenge";
    public static final String DESCRIPTION = "Deal !D! damage. If it kills an enemy, draw 1 card and gain 1 energy.";
    public static final String IMG_PATH = null;
    private static final int COST = 1;
    private static final int DMG = 10;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int GAIN = 1;

    private class ScavengeAction extends AbstractGameAction {

        private DamageInfo info;
        private int gainAmount;
        private static final float DURATION = 0.1F;

        public ScavengeAction(AbstractCreature target, DamageInfo info, int gainAmount) {
            this.info = info;
            setValues(target, info);
            this.gainAmount = gainAmount;
            this.actionType = AbstractGameAction.ActionType.DAMAGE;
            this.duration = DURATION;
        }

        @Override
        public void update() {
            {
                if ((this.duration == 0.1F) && (this.target != null)) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY,
                            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

                    this.target.damage(this.info);
                    if (((((AbstractMonster) this.target).isDying) || (this.target.currentHealth <= 0))
                            && (!this.target.halfDead)) {
                        AbstractDungeon.actionManager
                                .addToBottom(new DrawCardAction(AbstractDungeon.player, this.gainAmount));
                        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.gainAmount));
                    }
                    if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                    }
                }
                tickDuration();
            }
        }
    }

    public Scavenge() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, EnumPatch.CYAN,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = DMG;
        this.baseMagicNumber = GAIN;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ScavengeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
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
