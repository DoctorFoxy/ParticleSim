package ParticleEngine;

public class EmptyParticle extends Particle {

    public EmptyParticle(ParticleWorld world) {
        super(world);
        this.type = Type.EMPTY;
    }

    @Override
    public boolean getIsSolid() {
        return false;
    }
}
