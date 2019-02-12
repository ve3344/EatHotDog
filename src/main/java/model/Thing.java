package model;

public class Thing {
    protected float x, y;//位置
    protected float w, h;


    public Thing(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Thing(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }


    public boolean collide(Thing other){
        return this.x < other.x + other.w && other.x < this.x + this.w && this.y < other.y + other.h&& other.y < this.y + this.h;
    }


}
