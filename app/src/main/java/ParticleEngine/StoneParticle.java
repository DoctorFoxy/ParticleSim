package ParticleEngine;

import android.graphics.Color;

public class StoneParticle extends Particle {

    public StoneParticle(ParticleWorld world) {
        super(world);
        this.type = Type.STONE;
        this.color = Color.DKGRAY;
    }

    @Override
    public void doPhysics(int x, int y,ParticleWorld copyWorld,ParticleWorld realworld)  {
        //Do nothing, STONE stays in place
    }

    @Override
    public boolean getIsSolid() {
        return true;
    }

    @Override
    public Particle clone(ParticleWorld cloneWorld) {
        return null;
    }
}
