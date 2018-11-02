package grave_mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;

import grave_mod.Grave;
import grave_mod.cards.DefendGrave;
import grave_mod.cards.Experience;
import grave_mod.cards.GutsyBite;
import grave_mod.cards.Hardhead;
import grave_mod.cards.HastySearch;
import grave_mod.cards.Multitask;
import grave_mod.cards.Onslaught;
import grave_mod.cards.Prep;
import grave_mod.cards.Scavenge;
import grave_mod.cards.SplitDecision;
import grave_mod.cards.StrikeGrave;
import grave_mod.cards.TailSwipe;
import grave_mod.EnumPatch;

@SpireInitializer
public class GraveMod implements EditCardsSubscriber, EditCharactersSubscriber
{

	public static final Logger logger = LogManager.getLogger(GraveMod.class.getName());
	
	private static final String MODNAME = "GraveMod";
	private static final String AUTHOR = "Axid";
	private static final String DESCRIPTION = "v0.0.1\n Adds Grave, the Hydra as a playable character";
	
	public static final Color CYAN = CardHelper.getColor(0.0f, 200.0f, 200.0f);
	public static final String GRAVEMOD_ASSETS_FOLDER = "grave_mod/resources/img";
	
    private static final String HYDRA_BUTTON = "crowbot/crowbotButton.png";
    private static final String HYDRA_PORTRAIT = "crowbot/silentPortrait.jpg";
    public static final String HYDRA_SHOULDER_1 = "crowbot/shoulder.png";
    public static final String HYDRA_SHOULDER_2 = "crowbot/shoulder.png";
    public static final String HYDRA_CORPSE = "crowbot/corpse.png";
    public static final String HYDRA_SKELETON_ATLAS = "crowbot/idle/skeleton.atlas";
    public static final String HYDRA_SKELETON_JSON = "crowbot/idle/skeleton.json";
    
	// card backgrounds
	private static final String ATTACK_BLUE = "bg/bg_attack_blue.png";
	private static final String SKILL_BLUE = "bg/bg_skill_blue.png";
	private static final String POWER_BLUE = "bg/bg_power_blue.png";
	private static final String ENERGY_ORB_BLUE = "bg/card_blue_orb.png";
	
	private static final String ATTACK_BLUE_PORTRAIT = "bg/bg_attack_colorless.png";
	private static final String SKILL_BLUE_PORTRAIT = "bg/bg_skill_colorless.png";
    private static final String POWER_BLUE_PORTRAIT = "bg/bg_power_colorless.png";
    private static final String ENERGY_ORB_BLUE_PORTRAIT = "bg/card_colorless_orb.png";
	
    public static final String makePath(String resource) {
    	return GRAVEMOD_ASSETS_FOLDER + "/" + resource;
    }
	
	public GraveMod()
    {
        BaseMod.subscribe(this);
    }
    
    public static void initialize()
    {
		BaseMod.addColor(
				EnumPatch.CYAN, 
				CYAN, CYAN, CYAN, CYAN, CYAN, CYAN, CYAN, 
				makePath(ATTACK_BLUE),
				makePath(SKILL_BLUE),
				makePath(POWER_BLUE),
				makePath(ENERGY_ORB_BLUE), 
				makePath(ATTACK_BLUE_PORTRAIT),
				makePath(SKILL_BLUE_PORTRAIT),
				makePath(POWER_BLUE_PORTRAIT),
				makePath(ENERGY_ORB_BLUE_PORTRAIT)
		);
    	
        GraveMod gm = new GraveMod();
    }

    @Override
    public void receiveEditCards()
    {
        BaseMod.addCard(new StrikeGrave());
        BaseMod.addCard(new DefendGrave());
        BaseMod.addCard(new TailSwipe());
        BaseMod.addCard(new Multitask());
        BaseMod.addCard(new Hardhead());
        BaseMod.addCard(new GutsyBite());
        BaseMod.addCard(new Onslaught());
        BaseMod.addCard(new HastySearch());
        BaseMod.addCard(new Experience());
        BaseMod.addCard(new SplitDecision());
        BaseMod.addCard(new Scavenge());
        BaseMod.addCard(new Prep());

    }

	@Override
	public void receiveEditCharacters()
	{
		BaseMod.addCharacter(
		        new Grave(CardCrawlGame.playerName), 
				makePath(HYDRA_BUTTON),
				makePath(HYDRA_PORTRAIT), 
				EnumPatch.HYDRA);
	}
}