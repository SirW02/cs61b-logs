public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p){
        double G = 6.67e-11;
        return G * mass * p.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double sum = 0;
        for(Planet p : ps){
            if (!this.equals(p)) {
                sum += calcForceExertedByX(p);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double sum = 0;
        for(Planet p : ps){
            if (!this.equals(p)) {
                sum += calcForceExertedByY(p);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY){
        xxVel += dt * (fX / mass);
        yyVel += dt * (fY / mass);
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}