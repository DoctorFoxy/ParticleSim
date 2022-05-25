package ParticleEngine;

public class ParticleWorld {
    private Particle[][] world;
    public ParticleWorld() {
        world = new Particle[35][35];
        fillWorldEmpty();
    }

    public ParticleWorld(int col, int row) { //Row = y, Column = x
        this.world = new Particle[col][row];
        fillWorldEmpty();
    }

    public ParticleWorld(Particle[][] world) {
        this.world = world;
    }

    public Particle getParticle(int x, int y) {
        try {
            return world[x][y];
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean locSolid(int x, int y) {
        if (getParticle(x,y) == null) {
            return true;
        }
        else {
            return getParticle(x,y).getIsSolid();
        }
    }

    public void setParticle(int x, int y, Particle particle) {
        try {
            if (x < world.length && y < world[0].length) {
                world[x][y] = particle;
            }
        }
        catch (Exception e) {
            System.out.println("Cannot set Particle: " + e);
        }
    }

    public void fillWorldEmpty() {
        for (int colIndex = 0 ; colIndex < world.length ; colIndex++) {
            for (int rowIndex = 0 ; rowIndex < world[colIndex].length ; rowIndex++) {
                world[colIndex][rowIndex] = new EmptyParticle(this);
            }
        }
    }

    public void setWorld(Particle[][] world) {
        this.world = world;
    }

    public Particle[][] getWorld() {
        return this.world;
    }

    public void moveParticle(int x1, int y1, int x2, int y2) {
        setParticle(x2,y2, getParticle(x1,y1));
        setParticle(x1,y1, new EmptyParticle(this));
    }

    public void switchParticle(int x1, int y1, int x2, int y2) {
        Particle tempParticle = getParticle(x2,y2);
        setParticle(x2,y2, getParticle(x1,y1));
        setParticle(x1,y1, tempParticle);
    }
    public ParticleWorld cloneWorld (ParticleWorld particleWorld){
        ParticleWorld cloneWorld = new ParticleWorld(35,35);
        for (int rowIndex = particleWorld.getWorld()[0].length - 1 ; rowIndex >= 0 ; rowIndex--) {
            for (int colIndex = 0; colIndex < particleWorld.getWorld().length; colIndex++) {
                if (particleWorld.getParticle(colIndex,rowIndex).getType()==Type.GAS){
                    cloneWorld.setParticle(colIndex,rowIndex,new GasParticle(cloneWorld));
                }
                else{
                    if (particleWorld.getParticle(colIndex,rowIndex).getType()==Type.STONE){
                    cloneWorld.setParticle(colIndex,rowIndex,new StoneParticle(cloneWorld));
                    }
                    else{
                        if (particleWorld.getParticle(colIndex,rowIndex).getType()==Type.SAND){
                            cloneWorld.setParticle(colIndex,rowIndex,new SandParticle(cloneWorld));
                        }

                        else {
                        if (particleWorld.getParticle(colIndex,rowIndex).getType()==Type.WATER){
                            cloneWorld.setParticle(colIndex,rowIndex,new WaterParticle(cloneWorld));
                        }
                        else{
                            cloneWorld.setParticle(colIndex,rowIndex,new EmptyParticle(cloneWorld));

                        }
                    }
            }}}
        }
        return cloneWorld;
    }
    public ParticleWorld doPhysics(ParticleWorld copieworld, ParticleWorld realworld) {
        for (int rowIndex = copieworld.getWorld()[0].length - 1 ; rowIndex >= 0 ; rowIndex--) {
            for (int colIndex = 0 ; colIndex < copieworld.getWorld().length ; colIndex++) {
                copieworld.getParticle(colIndex, rowIndex).doPhysics(colIndex, rowIndex,copieworld,realworld);
            }
        }
        return realworld;
    }

    @Override
    public String toString() {
        String tempString = new String();
        for (int rowIndex = 0 ; rowIndex < world[0].length ; rowIndex++) {
            for (int colIndex = 0 ; colIndex < world.length ; colIndex++) {
                Type temptype = world[colIndex][rowIndex].getType();
                if (temptype.equals(Type.EMPTY)) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }

        System.out.println("-------------------------");

        return tempString;
    }
}