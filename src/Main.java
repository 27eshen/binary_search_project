import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet app;
    public boolean click = false;
    public boolean sPressed = false;
    ArrayList<Brownies> array = new ArrayList<Brownies>();
    int low = 0;
    int high;
    int target = 0;
    int count = 0;
    int mid = -1;
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
        loadTable();

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
        textSize(25);
        text("- press 's' to start -", width/2, 95);
        text("- press a number to change the target or keep it at 0 -", width/2, 120);
        text("- click to sort! -", width/2, 145);


        for (int i = 0; i < array.size(); i++) {
            boolean isMid = (i == mid);
            array.get(i).display((i * size) + 50,180, size, isMid);

            textAlign(CENTER);
            text(array.get(i).getValue(), ((i+1) * size), 180+size/2);
        }

        text("STATUS OF SEARCH: " + txt, width/2, 300);
    }

    public void keyPressed() {
        System.out.println("Key pressed: " + key);

        if (key == 's') {
            System.out.println("s pressed");
            selectionSort();
            sPressed = true;
            return;
        }

        if (key >= '0' && key <= '9') { // Only process numeric keys
            target = key - 48; // Convert character to integer
            System.out.println("Target set to: " + target);

            if (target < array.get(0).getValue() || target > array.get(array.size() - 1).getValue()) {
                System.out.println("Invalid target: " + target + ". Out of bounds.");
                txt = "INVALID TARGET: OUT OF BOUNDS!";
                target = 0; // Reset to default
            }
        } else if (key == 'r') {
            reset();
        } else {
            System.out.println("Invalid key pressed. Ignoring.");
        }
    }

    public void mouseClicked() {
        System.out.println("mouse clicked");
        click = !click;
        if (!sPressed){
            txt = "ERROR - SORT THE ARRAY FIRST!";
            return;
        }
        if (low <= high) { // Continue the search only if it's in progress
            binarySearchIterative();
        } else {
            System.out.println("Search complete. No further clicks allowed.");
        }
        redraw();
    }

    private void binarySearchIterative() {
        System.out.println("target = " + target);
        for (Brownies b : array) {
            b.compared = false;
        }

        if (low <= high) {
            mid = (low + high) / 2; // Calculate mid-point
            System.out.println("mid index: " + mid);
            array.get(mid).compared = true; // Highlight current mid

            int result = array.get(mid).compareTo(target);
            if (result == -1) { // Target is smaller
                high = mid - 1;
                txt = "NOT FOUND YET! Target is smaller than " + array.get(mid).getValue();
                System.out.println(txt);
            } else if (result == 1) { // Target is larger
                low = mid + 1;
                txt = "NOT FOUND YET! Target is larger than " + array.get(mid).getValue();
                System.out.println(txt);
            } else { // Target found
                txt = "TARGET FOUND AT INDEX " + mid + ". CLICK 'r' TO RESET.";
                System.out.println(txt);
                low = high + 1;
            }
        } else {
            txt = "TARGET IS NOT IN THE LIST!";
        }
    }

    public void selectionSort() {
        for (int i = 0; i < array.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).getValue() < array.get(minIndex).getValue()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array.get(minIndex).getValue();
                array.get(minIndex).value = array.get(i).getValue();
                array.get(i).value = temp;
            }
        }
    }

    public void reset() {
        high = array.size() - 1;
        low = 0;
        target = 0;
        count = 0;
        mid = -1;
        txt = "RESET COMPLETE. PRESS 's' TO SORT.";
        click = false;
        sPressed = false;

        loadTable();

        for (Brownies b : array) {
            b.compared = false;
        }
    }

    public void loadTable(){
        array.clear();
        Table table = loadTable("src/data/data.csv", "header");
        for (int i = 0; i < 10; i++) {
            int id = table.getInt(i, "iD");
            int year = table.getInt(i,"year");
            int badges = table.getInt(i, "badges");
            int value = table.getInt(i, "stars"); // Replace "stars" with the desired column
            array.add(new Brownies (id, year, badges, value));
        }
    }


//    private int binarySearchRecursive(){
//        if (high >= low){
//            int mid = (high + low)/2;
//            System.out.print(mid);
//
//            if (array.get(mid).getValue() == target){
//                System.out.println("comparison count: " + count);
//                return mid;
//            } else if (array.get(mid).getValue() > target){
//                count++;
//                System.out.println("comparison count: " + count);
//                high = mid-1;
//                return binarySearchRecursive();
//            } else {
//                count++;
//                System.out.println("comparison count: " + count);
//                low = mid + 1;
//                return binarySearchRecursive();
//            }
//        }
//        System.out.println("comparison count: " + count);
//        count = 0;
//        return -1;
//    }

}