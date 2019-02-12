package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {

    private int width, height;
    private List<HotDog> hotDogs;
    private Man man;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        hotDogs = new ArrayList<>();

        man = new Man(20, 20);
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public void addHotDog(HotDog hotDog) {
        hotDogs.add(hotDog);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void run() {
        Iterator<HotDog> it = hotDogs.iterator();
        while (it.hasNext()) {
            HotDog hotDog = it.next();

            hotDog.fallDown(this);

            if (hotDog.isGround(this)) {
                it.remove();
            }else if (man.canEat(hotDog)) {
                man.addScore(1);
                it.remove();
            }
        }

    }

    public void paint(Painter painter) {
        for (HotDog hotDog : hotDogs) {
            painter.paintHotDog(hotDog);
        }
        painter.paintMan(man);

    }


    public void generateHotDog(float w, float h) {
        while (hotDogs.size() < 5) {
            float x = (float) (Math.random() * (getWidth() - w));
            float speed = (float) (Math.random() * 4) + 2;
            hotDogs.add(new HotDog(x, getHeight() - h, w, h, speed));
        }
    }

    public Man getMan() {
        return man;
    }

    public interface Painter {
        void paintHotDog(HotDog hotDog);

        void paintMan(Man man);

    }
}
