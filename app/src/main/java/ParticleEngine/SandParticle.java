package ParticleEngine;

import android.graphics.Color;

public class SandParticle extends Particle {

    public SandParticle(ParticleWorld world) {
        super(world);
        this.type = Type.SAND;
        this.color = Color.YELLOW;
    }

    @Override
    public void doPhysics(int x, int y, ParticleWorld copyWorld,ParticleWorld realworld) {
        if (!copyWorld.locSolid(x, y+1)) {
            realworld.switchParticle(x, y, x, y+1);
        }
    }

    @Override
    public boolean getIsSolid() {
        return true;
    }

    @Override
    public Particle clone (ParticleWorld cloneWorld) {
        Particle cloneParticle = new SandParticle(cloneWorld);

        return  cloneParticle;
    }
}
