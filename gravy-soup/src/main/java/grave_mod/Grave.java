package grave_mod;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import grave_mod.GraveMod;

public class Grave extends AbstractPlayer {
	
    public static final String HYDRA_SHOULDER_1 = "crowbot/shoulder.png";
    public static final String HYDRA_SHOULDER_2 = "crowbot/shoulder.png";
    public static final String HYDRA_CORPSE = "crowbot/corpse.png";
    public static final String HYDRA_SKELETON_ATLAS = "crowbot/idle/skeleton.atlas";
    public static final String HYDRA_SKELETON_JSON = "crowbot/idle/skeleton.json";
	
	public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
	public static final String GRAVE_SHOULDER_2 = GraveMod.makePath(HYDRA_SHOULDER_2); // campfire pose
    public static final String GRAVE_SHOULDER_1 = GraveMod.makePath(HYDRA_SHOULDER_1); // another campfire pose
	public static final String GRAVE_CORPSE = GraveMod.makePath(HYDRA_CORPSE); // dead corpse
    public static final String GRAVE_SKELETON_ATLAS = GraveMod.makePath(HYDRA_SKELETON_ATLAS); // spine animation atlas
    public static final String GRAVE_SKELETON_JSON = GraveMod.makePath(HYDRA_SKELETON_JSON); // spine animation json

	public Grave (String name, PlayerClass setClass) {
		super(name, setClass);
		
		this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
		
		initializeClass(null, GRAVE_SHOULDER_2, // required call to load textures and setup energy/loadout
				GRAVE_SHOULDER_1,
				GRAVE_CORPSE, 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
		
		loadAnimation(GRAVE_SKELETON_ATLAS, GRAVE_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
		
		AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
		e.setTime(e.getEndTime() * MathUtils.random());
	}

	public static ArrayList<String> getStartingDeck() { // starting deck 'nuff said
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
	
	public static ArrayList<String> getStartingRelics() { // starting relics - also simple
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Lizard Tail");
		UnlockTracker.markRelicAsSeen("Lizard Tail");
		return retVal;
	}
	
        public static final int STARTING_HP = 75;
        public static final int MAX_HP = 75;
        public static final int STARTING_GOLD = 99;
        public static final int HAND_SIZE = 5;

	public static CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
		return new CharSelectInfo("Grave", "A hydra who is doubly feroicious and tactful.",
				STARTING_HP, MAX_HP, STARTING_GOLD, HAND_SIZE,
			EnumPatch.HYDRA, getStartingRelics(), getStartingDeck(), false);
	}
	
}