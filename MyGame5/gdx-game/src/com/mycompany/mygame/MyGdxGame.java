package com.mycompany.mygame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;




public class MyGdxGame implements ApplicationListener
{

	final int SIZE_CHR = 64;
	final int VIEWPORT_WIDTH = 800;
	final int VIEWPORT_HEIGHT = 800;


	SpriteBatch batch;
	Texture img;
	Animation anim;
	BitmapFont font;
	GlyphLayout glyph;
	OrthographicCamera camera;
	Music music;
	Sound coin;

	float mCurrentDeltaTime = 0;
	private int touchCount = 0;


	@Override
	public void create()
	{
		batch = new SpriteBatch();

		img = new Texture("UnityChan.png");
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int rows = 1; rows <=2; ++rows){
			for(int columns = 0; columns < 4; ++columns){
				TextureRegion region  = new TextureRegion(img,columns * SIZE_CHR,rows * SIZE_CHR,SIZE_CHR,SIZE_CHR);
				frames.add(region);
			}
		}

		anim = new Animation(0.05f,  frames, Animation.PlayMode.LOOP);
		font = new BitmapFont(Gdx.files.internal("verdana39.fnt"));
		glyph = new GlyphLayout();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,VIEWPORT_WIDTH/2,VIEWPORT_HEIGHT/2);
		camera.position.set(VIEWPORT_WIDTH/2,VIEWPORT_HEIGHT/2,0);

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.play();
		coin = Gdx.audio.newSound(Gdx.files.internal("coin05.mp3"));

;	}

	@Override
	public void render()
	{

	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}