package ParticleEngine;

public class Run {
    public static void main(String[] args) {
        ParticleWorld world = new ParticleWorld(15,5);
        Particle sand = new SandParticle(world);
        world.setParticle(2,2, sand);
        world.setParticle(0,0, sand);
        world.setParticle(0,1, sand);
        world.setParticle(9,0, sand);
        world.setParticle(14,0, sand);
        world.toString();
        world.doPhysics();
        world.toString();
        world.doPhysics();
        world.toString();
        world.doPhysics();
        world.toString();
        world.doPhysics();
        world.toString();
    }
}
