package grave_mod;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import grave_mod.GraveMod;
import grave_mod.cards.Multitask;

public class Grave extends CustomPlayer {

    public static final String HYDRA_SHOULDER_1 = "crowbot/shoulder.png";
    public static final String HYDRA_SHOULDER_2 = "crowbot/shoulder.png";
    public static final String HYDRA_CORPSE = "crowbot/corpse.png";
    public static final String HYDRA_SKELETON_ATLAS = "crowbot/idle/skeleton.atlas";
    public static final String HYDRA_MODEL = "crowbot/main.png";
    public static final String HYDRA_SKELETON_JSON = "crowbot/idle/skeleton.json";
    public static final String NAME = "Grave";

    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String GRAVE_SHOULDER_2 = GraveMod.makePath(HYDRA_SHOULDER_2); // campfire pose
    public static final String GRAVE_SHOULDER_1 = GraveMod.makePath(HYDRA_SHOULDER_1); // another campfire pose
    public static final String GRAVE_CORPSE = GraveMod.makePath(HYDRA_CORPSE); // dead corpse
    public static final String GRAVE_SKELETON_ATLAS = GraveMod.makePath(HYDRA_SKELETON_ATLAS); // spine animation atlas
    public static final String GRAVE_MODEL = GraveMod.makePath(HYDRA_MODEL); // full model
    public static final String GRAVE_SKELETON_JSON = GraveMod.makePath(HYDRA_SKELETON_JSON); // spine animation json
    
    //Temporary animation type
    public static class TempAnimation extends AbstractAnimation {

        @Override
        public Type type() {
            return AbstractAnimation.Type.NONE;
        }
    }

    public Grave(String name) {
        super(NAME, EnumPatch.HYDRA, new EnergyOrbBlue(), new TempAnimation());

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, GRAVE_SHOULDER_2, // required call to load textures and setup energy/loadout
                GRAVE_SHOULDER_1, GRAVE_CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F,
                new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(GRAVE_SKELETON_ATLAS, GRAVE_SKELETON_JSON, 1.0F); // if you're using modified versions of base
                                                                        // game animations or made animations in spine
                                                                        // make sure to include this bit and the
                                                                        // following lines

        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Strike_Grave");
        retVal.add("Strike_Grave");
        retVal.add("Strike_Grave");
        retVal.add("Strike_Grave");
        retVal.add("Strike_Grave");
        retVal.add("Defend_Grave");
        retVal.add("Defend_Grave");
        retVal.add("Defend_Grave");
        retVal.add("Defend_Grave");
        retVal.add("Multitask");
        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Lizard Tail");
        UnlockTracker.markRelicAsSeen("Lizard Tail");
        return retVal;
    }

    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    public static final int ORB_SLOTS = 1;

    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen
                                         // info plus hp and starting gold
        return new CharSelectInfo("Grave", "A hydra who is doubly feroicious and tactful.", STARTING_HP, MAX_HP,
                ORB_SLOTS, STARTING_GOLD, HAND_SIZE, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    public static final int ASC_MAX_HP_LOSS = 4;

    @Override
    public int getAscensionMaxHPLoss() {
        return ASC_MAX_HP_LOSS;
    }

    @Override
    public CardColor getCardColor() {
        return EnumPatch.CYAN;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(0.0f, 200.0f, 200.0f);
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(0.0f, 200.0f, 200.0f);
    }

    public static final String BUTTON_SOUND = "SNECKO_GLARE";
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return BUTTON_SOUND;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue; // Possibly change?
    }

    public static final String LOCAL_NAME = "Grave";

    @Override
    public String getLocalizedCharacterName() {
        return LOCAL_NAME;
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(0.0f, 200.0f, 200.0f);
    }

    public static final AttackEffect[] FINISHER = { AttackEffect.FIRE, AttackEffect.FIRE, AttackEffect.FIRE };

    @Override
    public AttackEffect[] getSpireHeartSlashEffect() {
        return FINISHER;
    }

    public static final String FINISHER_TEXT = "NL Your lungs broil with noxious flame.";

    @Override
    public String getSpireHeartText() {
        return FINISHER_TEXT;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Multitask();
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return playerClass.name();
    }

    public static final String VAMPIRE_REFERRAL = "beast";

    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual."
                + " As you approach, they turn to you in eerie unison."
                + " The tallest among them bares fanged teeth and extends a long, pale hand towards you."
                + " NL ~\"Join~ ~us~ ~" + VAMPIRE_REFERRAL + ",~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Grave(this.name);
    }
    
    @Override
    public Texture getEnergyImage() {
        return ImageMaster.BLUE_ORB_FLASH_VFX;
    }

}