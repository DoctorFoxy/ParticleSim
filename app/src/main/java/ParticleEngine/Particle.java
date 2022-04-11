package ParticleEngine;

public abstract class Particle {
    protected Type type;
    protected ParticleWorld world;
    protected boolean isSolid;

    public Particle(ParticleWorld world) {
        this.world = world;
        isSolid = true;
    }

    public void doPhysics(int x, int y){};

    public Type getType() {
        return type;
    }

    public boolean getIsSolid() {
        return true;
    }

}
