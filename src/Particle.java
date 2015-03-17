/**
 * Created by jens on 03.03.15.
 * Modded by Per-Olav.
 */
class Particle {

   // private Random rand;

    private float xpos;
    private float ypos;

    private int xMoves = 0;
    private int yMoves= 0;

    private int colR;
    private int colG;
    private int colB;

    private float width;
    private float height;

    Particle(float xpos, float ypos, int colR, int colG, int colB) {
        this.xpos = xpos;
        this.ypos = ypos;

        this.colR = colR;
        this.colG = colG;
        this.colB = colB;

        this.width = 8;
        this.height = 8;

      //  rand = new Random();

    }

    public float getXpos() {
        return xpos;
    }



    public float getYpos() {
        return ypos;
    }

    public int getxMoves() {
        return xMoves;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getColR() {
        return colR;
    }

    public int getColG() {
        return colG;
    }

    public int getColB() {
        return colB;
    }

    public int getyMoves() {
        return yMoves;
    }

    public void move(int displayWidth, int displayHeight){

        int xRand=0;
        int yRand=0;

        double r = Math.random();
        if (r < 0.25) xRand--;
        else if (r < 0.50) xRand++;
        else if (r < 0.75) yRand--;
        else if (r < 1.00) yRand++;

        // Kan den bevege seg dit?

        if ((this.xpos + xRand) >= 0 && (this.xpos + xRand) <= displayWidth){
            // Den er innenfor display, bevege partikkel
            if (xRand == 1 || xRand == -1){
                // Den har beveget seg i x-retning
                this.xMoves++;
            }
            this.xpos =  xpos + xRand;

        }

        if ((this.ypos + yRand) >= 0 && (this.ypos + yRand) <= displayHeight){
            // Den er innenfor displayrrrr
            if (yRand == 1 || yRand == -1){
                // Den har beveget seg i y-retning
                this.yMoves++;
            }
            this.ypos =  ypos + yRand;
        }


    }
}