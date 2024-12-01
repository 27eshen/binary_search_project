public class Brownies {
    private int iD;
    private int year;
    private int badges;
    private int stars;
    public boolean compared;
    public int value;

    public Brownies(int id, int year, int badges, int stars) {
        this.iD = id;
        this.year = year;
        this.badges = badges;
        this.stars = stars;
        value = stars;
    }

    public int compareTo(int anothervalue) {
        if (this.value < anothervalue) {
            return 1;
        } else if (this.value > anothervalue) {
            return -1;
        } else {
            return 0;
        }
    }


//    public void searched(){
//        compared = !compared;
//    }

    public int getValue(){
        return value;
    }

    public void display(int x, int w, int h, boolean isMid){
        if (isMid) {
            Main.app.fill(95, 150, 186, 150); // Highlight current box
        } else if (compared) {
            Main.app.fill(155, 176, 189, 80); // Slightly different fill for "compared"
        } else {
            Main.app.fill(255, 255, 255); // Default fill
        }

        Main.app.rect(x, w, h, h);
        Main.app.fill(0,0,0);

    }
}
