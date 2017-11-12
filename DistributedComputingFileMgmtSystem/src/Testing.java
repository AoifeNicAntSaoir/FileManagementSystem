/**
 * Created by Aoife Sayers on 11/11/2017.
 */
public class Testing {
    public static void main(String[] args) {

        String string = "1, Aoife, mypassword";
        String[] parts = string.split(",");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556
        String part3 = parts[2];

        System.out.println(part1.trim());
        System.out.println(part2.trim());
        System.out.println(part3.trim());
    }
}
