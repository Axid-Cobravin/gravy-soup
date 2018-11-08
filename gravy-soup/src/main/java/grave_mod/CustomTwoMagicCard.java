package grave_mod;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.abstracts.DynamicVariable;

public abstract class CustomTwoMagicCard extends CustomCard {

    public static class SecondMagicNumber extends DynamicVariable {
        public int baseValue(AbstractCard card) {
            return ((CustomTwoMagicCard) card).baseSecondMagicNumber;
        }

        public boolean isModified(AbstractCard card) {
            return ((CustomTwoMagicCard) card).isSecondMagicNumberModified;
        }

        public String key() {
            return "hydra:M2";
        }

        public boolean upgraded(AbstractCard card) {
            return ((CustomTwoMagicCard) card).upgradedSecondMagicNumber;
        }

        public int value(AbstractCard card) {
            return ((CustomTwoMagicCard) card).secondMagicNumber;
        }
    }
    
    public CustomTwoMagicCard(String id, String name, String img, int cost, String rawDescription, CardType type,
            CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public int baseSecondMagicNumber;
    public int secondMagicNumber;
    public boolean isSecondMagicNumberModified;
    public boolean upgradedSecondMagicNumber;

    public abstract void upgrade();

    public abstract void use(AbstractPlayer arg0, AbstractMonster arg1);

    public void upgradeSecondMagicNumber(int amount) {
        this.baseSecondMagicNumber += amount;
        this.secondMagicNumber = this.baseSecondMagicNumber;
        this.upgradedSecondMagicNumber = true;
    }

}