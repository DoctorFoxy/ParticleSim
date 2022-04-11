package ParticleEngine;

public class SandParticle extends Particle {

    public SandParticle(ParticleWorld world) {
        super(world);
        this.type = Type.SAND;
    }

    @Override
    public void doPhysics(int x, int y) {
        if (!world.locSolid(x, y+1)) {
            world.switchParticle(x, y, x, y+1);
        }
    }

    @Override
    public boolean getIsSolid() {
        return true;
    }
}
