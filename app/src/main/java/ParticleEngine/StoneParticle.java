package ParticleEngine;

import android.graphics.Color;

public class StoneParticle extends Particle {

    public StoneParticle(ParticleWorld world) {
        super(world);
        this.type = Type.STONE;
        this.color = Color.DKGRAY;
    }

    @Override
    public void doPhysics(int x, int y) {
        //Do nothing, STONE stays in place
    }

    @Override
    public boolean getIsSolid() {
        return true;
    }
}
