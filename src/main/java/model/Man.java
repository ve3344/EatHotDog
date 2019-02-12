package model;


public class Man extends Thing {
    private float speed;
    private int score;

    public Man( float w, float h) {
        super(0, 0, w, h);
        speed = 10;
        score = 0;
    }

    public void moveLeft(Map map) {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight(Map map) {
        x += speed;
        if (x + w > map.getWidth()) {
            x = map.getWidth() - w;
        }
    }
    public void addScore(int inscreaseScore ){
        score+=inscreaseScore;
    }

    public int getScore() {
        return score;
    }

    public boolean canEat(HotDog hotDog) {
        return collide(hotDog);
    }

}
