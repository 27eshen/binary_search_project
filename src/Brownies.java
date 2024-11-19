public class Brownies {
    private int iD;

    public int compareTo(Object anotherObject){
        Brownies y = (Brownies) anotherObject;
        if (this.iD < ((Brownies) anotherObject).iD){
            return -1;
        } else if (this.iD > ((Brownies) anotherObject).iD){
            return 1;
        } else {
            return 0;
        }
    }
}
