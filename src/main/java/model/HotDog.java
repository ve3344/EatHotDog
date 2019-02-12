package model;

public class HotDog extends Thing {
    protected float speed;

    public HotDog(float x, float y) {
        super(x, y);
        speed=1;
    }

    public HotDog(float x, float y, float w, float h, float speed) {
        super(x, y, w, h);
        this.speed = speed;
    }

    public void fallDown(Map map){
        y-=speed;
        if (y<h){
            y=0;
        }
    }
    public boolean isGround(Map map){
        return y<=0;
    }



}
