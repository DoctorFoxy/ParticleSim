package ParticleEngine;

import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Particle {
    protected Type type;
    protected ParticleWorld world;
    protected boolean isSolid;
    protected int color;

    public Particle(ParticleWorld world) {
        this.world = world;
        isSolid = true;
        color = Color.WHITE;
    }

    public void doPhysics(int x, int y,ParticleWorld copyworld,ParticleWorld realworld){

    };

    public Type getType() {
        return type;
    }

    public boolean getIsSolid() {
        return true;
    }

    public int getColor() {
        return color;
    }

    public abstract  Particle clone (ParticleWorld cloneWorld);
    public void isBlack()
    {
        color = Color.BLACK;
    }
    public void isWhite()
    {
        color = Color.WHITE;
    }
}
