package com.mycompany.mygame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float deltaTime = Gdx.graphics.getDeltaTime();
		mCurrentDeltaTime += deltaTime;

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		TextureRegion keyframe = anim.getKeyFrame(mCurrentDeltaTime);
		batch.draw(keyframe, 0, 0, SIZE_CHR*2, SIZE_CHR*2);

		if(Gdx.input.justTouched()){
			touchCount++;
			coin.play();
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			Vector3 coords = camera.unproject(new Vector3(x,y,0));
			Gdx.app.log("MyGdxGame",String.format("(x,y)=(%d,%d), coords(x,y)=(%f,%f)", x,y,coords.x,coords.y));

		}

		final String text = (touchCount <= 0)?"Are you Ready":"Of course.\ncount=" + touchCount;
		glyph.setText(font, text,Color.WHITE,0,Align.center, true);
		font.draw(batch,glyph,VIEWPORT_WIDTH*0.5f,VIEWPORT_HEIGHT*0.5f+glyph.height*0.5f);

		batch.end();

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