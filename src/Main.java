import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet app;
    public boolean click = false;
    public boolean keypress = false;
    ArrayList<Integer> array = new ArrayList<Integer>();
    int low = 0;
    int high;
    int target = 0;
    int count = 0;
    int mid = 0;
    String txt = "";

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    public Main() {
        app = this;
    }

    public void settings(){
        size(900, 500);
    }

    public void setup() {
        //System.out.println(binarySearchIterative());

        for (int i = 0; i < 10; i++) {
            array.add(i);
        }

        ///high = array.size()-1;
        //System.out.println(binarySearchRecursive())
        high = array.size() - 1;
        low = 0;
        count = 0;
    }

    public void draw(){
        background(155, 176, 189);
        int size = (width-100)/array.size();

        fill(0);
        textSize(50);
        textAlign(CENTER, CENTER);
        text("BINARY SEARCH", width/2, 50);
        textSize(30);
        text("- press a number to change the target or keep it at 0 -", width/2, 110);
        text("- click to start! -", width/2, 140);


        for (int i = 0; i < array.size(); i++) {
            fill(255);
            Main.app.rect((i * size) + 50, 180, size, size);
            fill(155, 176, 189);
            text(array.get(i).toString(), ((i+1) * size)+10, 180+size/2);
        }

        fill(0);
        if (click){
            int result = binarySearchIterative();
            if (result == -1){
                System.out.println("TARGET IS NOT IN THE LIST!");
                txt = "TARGET IS NOT IN THE LIST!";
                click = !click;
            } else if (result == 2){
                System.out.println("NOT FOUND YET!");
                fill(95, 150, 186, 150);
                Main.app.rect((mid * size) + 50, 180, size, size);
                delay(1000);
                txt = "NOT FOUND YET!";
                click = !click;
            } else {
                System.out.println("TARGET FOUND AT INDEX " + result);
                txt = "TARGET FOUND AT INDEX " + result;
                count = 0;
                click = !click;
            }
        }
        text("STATUS OF SEARCH: " + txt, width/2, 300);
    }

    public void keyPressed() {
        reset();
        System.out.println("key pressed");
        keypress = !keypress;
        if (key != 'r'){
            target = key-48; //might b problem but whatever allison is always right
            System.out.println(target);
        } else {
            reset();
        }
    }

    public void mouseClicked() {
        System.out.println("mouse clicked");
        click = !click;
    }

    // use a recursive call
    private int binarySearchRecursive(){
        if (high >= low){
            int mid = (high + low)/2;
            System.out.print(mid);

            if (array.get(mid) == target){
                System.out.println("comparison count: " + count);
                return mid;
            } else if (array.get(mid) > target){
                count++;
                System.out.println("comparison count: " + count);
                high = mid-1;
                return binarySearchRecursive();
            } else {
                count++;
                System.out.println("comparison count: " + count);
                low = mid + 1;
                return binarySearchRecursive();
            }
        }
        System.out.println("comparison count: " + count);
        count = 0;
        return -1;
    }

    private int binarySearchIterative(){
        if (low <= high){
            mid = (low + high)/2;
            System.out.println(mid);
            int spot = array.get(mid);
            count++;
            if (spot == target) {
                System.out.println("final comparison count is: " + count);
                return spot; //spot where the key is at
            } else if (spot < target){
                low = mid++;
                return 2; // ignore everything lower
            } else {
                high = mid--;
                return 2; // ignore everything higher
            }
        }
        return -1; // not found
    }

    public void reset(){
        high = array.size()-1;
        low = 0;
        mid = 0;
        target = 0;
        count = 0;
        txt = "";
    }

}