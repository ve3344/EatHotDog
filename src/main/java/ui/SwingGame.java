package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.HotDog;
import model.Man;
import model.Map;

public class SwingGame implements ImageObserver {
    public static final float SCALE = 0.3f;
    private Map map;
    Image hotDogImage;
    Image manImage;
    JFrame window;
    Graphics graphics;//绘画器

    Map.Painter painter = new Map.Painter() {
        @Override
        public void paintHotDog(HotDog hotDog) {
            if (graphics != null) {
                graphics.drawImage(hotDogImage, (int) hotDog.getX(), (int) (map.getHeight() - hotDog.getY() - hotDog.getH() * 1), (int) hotDog.getW(), (int) hotDog.getH(), SwingGame.this);
            }
        }

        @Override
        public void paintMan(Man man) {
            if (graphics != null) {
                graphics.drawImage(manImage, (int) man.getX(), (int) (map.getHeight() - man.getY() - man.getH() * 1), (int) man.getW(), (int) man.getH(), SwingGame.this);
            }
        }

        public void painGround(float ground) {
            if (graphics != null) {
                graphics.setColor(Color.GREEN);
                graphics.drawLine(0, (int) (map.getHeight() - ground), (int) map.getWidth(), (int) (map.getHeight() - ground));
            }
        }
    };

    SwingGame() {
        window = new JFrame();
        window.setSize(800, 800);
        map = new Map(window.getWidth(), window.getHeight());


        try {
            hotDogImage = ImageIO.read(SwingGame.class.getResource("/hotdog.png"));
            manImage = ImageIO.read(SwingGame.class.getResource("/man.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Man man = new Man(manImage.getWidth(this) * SCALE, manImage.getHeight(this) * SCALE);
        map.setMan(man);
    }


    void run() {

        window.setContentPane(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                graphics = g;
                map.paint(painter);
                String text = "srore:" + map.getMan().getScore();
                g.setColor(Color.BLUE);
                g.drawChars(text.toCharArray(), 0, text.length(), 30, 30);
            }
        });
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(new KeyEventPostProcessor() {
            @Override
            public boolean postProcessKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    map.getMan().moveLeft(map);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    map.getMan().moveRight(map);

                }
                return false;
            }
        });

        window.setVisible(true);
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            window.repaint();
            map.run();
            map.generateHotDog(hotDogImage.getWidth(null) * SCALE, hotDogImage.getHeight(SwingGame.this) * SCALE);
        }

    }


    public static void main(String[] args) {
        new SwingGame().run();
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
