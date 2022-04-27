package ParticleEngine;

import android.graphics.Color;

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

    public void doPhysics(int x, int y){};

    public Type getType() {
        return type;
    }

    public boolean getIsSolid() {
        return true;
    }

    public int getColor() {
        return color;
    }

}
