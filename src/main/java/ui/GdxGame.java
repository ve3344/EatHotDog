package ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.jglfw.JglfwApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import model.Man;
import model.HotDog;
import model.Map;

public class GdxGame implements ApplicationListener {
    public static final float SCALE = 0.3f;
    private Map map;
    private SpriteBatch spriteBatch;//图片绘制器
    private BitmapFont bitmapFont;//字体绘制器
    private TextureRegion hotDogTexture;//热狗图片
    private TextureRegion manTexture;//人物图片


    final Map.Painter painter = new Map.Painter() {
        @Override
        public void paintHotDog(HotDog hotDog) {
            spriteBatch.draw(hotDogTexture, hotDog.getX(), hotDog.getY(), hotDog.getW(), hotDog.getH());
        }

        @Override
        public void paintMan(Man man) {
            spriteBatch.draw(manTexture, man.getX(), man.getY(), man.getW(), man.getH());
        }


    };

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLUE);
        map = new Map(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //添加小球
        //加载图片
        hotDogTexture = new TextureRegion(new Texture(Gdx.files.internal("hotdog.png")));
        manTexture = new TextureRegion(new Texture(Gdx.files.internal("man.png")));
        map.setMan(new Man(manTexture.getRegionWidth()*SCALE,manTexture.getRegionHeight()*SCALE));
    }

    @Override
    public void resize(int w, int h) {
        //当窗口大小变化时
    }

    @Override
    public void render() {
        //这个方法 绘制每一帧时都会调用 相当于在循环里

        Gdx.gl.glClearColor(0.6f, 0.5f, 1, 1);//设置清除颜色
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//清除背景

        spriteBatch.begin();

        map.paint(painter);//用painter绘制地图
        bitmapFont.draw(spriteBatch, "score:" + map.getMan().getScore(), 0, map.getHeight());

        spriteBatch.end();
        /////////////////

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            map.getMan().moveLeft(map);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            map.getMan().moveRight(map);
        }
        map.run();
        map.generateHotDog(hotDogTexture.getRegionWidth()*SCALE,hotDogTexture.getRegionHeight()*SCALE);

    }

    @Override
    public void pause() {
        //程序暂停时
    }

    @Override
    public void resume() {
        //程序暂停恢复
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        //程序关闭时 执行一些清理操作
    }

    public static void main(String[] args) {
        new JglfwApplication(new GdxGame(), "窗口标题", 800, 800);
    }


}
