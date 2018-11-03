package com.kalgames.untouchable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kalgames.com.kalgames.assets.Storage;
import com.kalgames.interfaces.AdsController;
import com.kalgames.interfaces.PlayServices;
import com.kalgames.interfaces.ToastService;
import com.kalgames.screens.ChallengeScreen;
import com.kalgames.screens.ChallengeOverScreen;
import com.kalgames.screens.GamesScreen;
import com.kalgames.screens.Level_10;
import com.kalgames.screens.Level_4;
import com.kalgames.screens.Level_5;
import com.kalgames.screens.Level_6;
import com.kalgames.screens.Level_7;
import com.kalgames.screens.Level_8;
import com.kalgames.screens.Level_9;
import com.kalgames.screens.Levels;
import com.kalgames.screens.MenuScreen;
import com.kalgames.screens.Level_1;
import com.kalgames.screens.Level_2;
import com.kalgames.screens.Level_3;
import com.kalgames.screens.SplashScreen;
import com.kalgames.stage.HUD;

public class Untouchable extends Game {

	public static final int screenWidth = 480;
	public static final int screenHeight = 820;

    public Preferences settings;

    public Runnables runnables;
    public SpriteBatch batch;
    public Stage stage;
    public InputMultiplexer inputMultiplexer;
    public HUD hud;
    public Actor hitActor;
    public Image transition;
    public Animation winAnimation;
    public Animation deathAnimation;

    public Sound logoSound, buttonClick, showButtonSound, deathSound, recordSound;
    public Music menuMusic, levelsMusic, challengeMusic;

	public OrthographicCamera camera;
	public Viewport viewport;
    public Vector2 touchPos;
    public Vector2 stageCoords;

    public BitmapFont scoreFont, scoreFont2;
    public GlyphLayout glyphLayout;
    public boolean musicOn;

    public SplashScreen splashScreen;
	public MenuScreen menuScreen;
    public GamesScreen gamesScreen;
	public Level_1 level_1;
    public Level_2 level_2;
	public Level_3 level_3;
    public Level_4 level_4;
    public Level_5 level_5;
    public Level_6 level_6;
    public Level_7 level_7;
    public Level_8 level_8;
    public Level_9 level_9;
    public Level_10 level_10;
    public Levels levels;
	public ChallengeScreen challengeScreen;
    public ChallengeOverScreen challengeOverScreen;

    public int deathCount;

    public ToastService toastService;
    public PlayServices playServices;
    public AdsController adsController;

    public Untouchable(){

    }

    public Untouchable(ToastService toastService, PlayServices playServices, AdsController adsController){
        this.toastService = toastService;
        this.playServices = playServices;
        this.adsController = adsController;
    }

	@Override
	public void create () {
        Storage.loadStorage();

        settings = Gdx.app.getPreferences("levels");

        runnables = new Runnables(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        viewport = new FitViewport(screenWidth, screenHeight, camera);
        viewport.apply();
        touchPos = new Vector2();
        stageCoords = new Vector2();

        batch = new SpriteBatch();
        stage = new Stage(viewport);
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setCatchBackKey(true);
        hud = new HUD();
        transition = new Image(Storage.transitionTex);
        transition.setPosition(-1, -1);
        winAnimation= new Animation(1/40f, Storage.winAtlas.getRegions());
        deathAnimation = new Animation(1/7f, Storage.deathAtlas.getRegions());

        logoSound = Gdx.audio.newSound(Gdx.files.internal("logoSound.mp3"));
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("buttonClick.mp3"));
        showButtonSound = Gdx.audio.newSound(Gdx.files.internal("showButton.mp3"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("deathSound.mp3"));
        recordSound = Gdx.audio.newSound(Gdx.files.internal("recordSound.mp3"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        menuMusic.setVolume(0.3f);
        menuMusic.setLooping(true);
        levelsMusic = Gdx.audio.newMusic(Gdx.files.internal("levelsMusic.mp3"));
        levelsMusic.setVolume(0.3f);
        levelsMusic.setLooping(true);
        challengeMusic = Gdx.audio.newMusic(Gdx.files.internal("challengeMusic.mp3"));
        challengeMusic.setVolume(0.35f);
        challengeMusic.setLooping(true);

        glyphLayout = new GlyphLayout();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("scoreFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 36;
        params.color.set(0.75f, 0.2f, 0.2f, 1f);
        scoreFont = generator.generateFont(params);
        scoreFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        FreeTypeFontGenerator.FreeTypeFontParameter params1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params1.size = 36;
        params1.color.set(0.2f, 0.2f, 0.2f, 1f);
        scoreFont2 = generator.generateFont(params1);
        scoreFont2.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        generator.dispose();

        musicOn = true;

        deathCount = 0;

        splashScreen = new SplashScreen(this);
        menuScreen = new MenuScreen(this);
        gamesScreen = new GamesScreen(this);
		level_1 = new Level_1(this);
        level_2 = new Level_2(this);
		level_3 = new Level_3(this);
        level_4 = new Level_4(this);
        level_5 = new Level_5(this);
        level_6 = new Level_6(this);
        level_7 = new Level_7(this);
        level_8 = new Level_8(this);
        level_9 = new Level_9(this);
        level_10 = new Level_10(this);
        levels = new Levels(this);
		challengeScreen = new ChallengeScreen(this);
        challengeOverScreen = new ChallengeOverScreen(this);
        setScreen(splashScreen);
	}

	@Override
	public void dispose() {
        batch.dispose();
        stage.dispose();
        menuScreen.dispose();
        gamesScreen.dispose();
        level_1.dispose();
        level_2.dispose();
        level_3.dispose();
        level_4.dispose();
        level_5.dispose();
        level_6.dispose();
        level_7.dispose();
        level_8.dispose();
        level_9.dispose();
        level_10.dispose();
        levels.dispose();
        challengeScreen.dispose();
        challengeOverScreen.dispose();
        scoreFont.dispose();
        scoreFont2.dispose();
        logoSound.dispose();
        buttonClick.dispose();
        showButtonSound.dispose();
        deathSound.dispose();
        recordSound.dispose();
        menuMusic.dispose();
        levelsMusic.dispose();
        challengeMusic.dispose();
        Storage.disposeStorage();
	}
}
