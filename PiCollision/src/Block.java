public class Block {
   double velX = 0 ;
   double positionX = 0;
   double positionY = 0;
   double w = 0;
   double m = 0;
   double width = 0;
   double height = 300;

    public Block(double x, double w, double Vel, double m , double width){
        positionX= x;
        this.width = width;
        positionY = height - w;
        this.w = w;
        velX = Vel;
        this.m = m;
    }
    public void update(){
        positionX += velX;
    }
    public double getTranslateX(){
        return positionX;
    }

    public double getTranslateY(){
        return positionY;
    }

    public boolean collide(Block other){
        //return(Math.abs(positionX+20 - other.positionX) < 0.2);
        return!(positionX + this.w < other.w || positionX > other.positionX + other.w);
    }

    public double bounce(Block other){
        double sumMass = this.m + other.m;
        double newVelocity = (this.m -other.m)/sumMass * velX;
        newVelocity += (2 * other.m /sumMass) * other.velX;
        return newVelocity;
    }

    public boolean wall(){
        return(positionX <=0);//+this.width/2)){

    }

    public void reverse(){
        velX *= -1;
    }
}
