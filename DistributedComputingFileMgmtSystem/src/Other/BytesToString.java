package Other;

/**
 * Created by Aoife Sayers on 16/11/2017.
 */
public class BytesToString {
          public static void main(String[] argv) {

            String example = "This is an example";
            byte[] bytes = example.getBytes();

            System.out.println("Text : " + example);
            System.out.println("Text [Byte Format] : " + bytes);
            System.out.println("Text [Byte Format] : " + bytes.toString());

            String s = new String(bytes);
            System.out.println("Text Decryted : " + s);



    }
}
