package nowcoder;
import java.util.*;

public class HJ11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb=new StringBuilder();
        while(sc.hasNext()) {
            String str = sc.next();
            if (str.equals("0")) {
                System.out.println("0");
                return;
            }
            for (int i = str.length() - 1; i >= 0; i--) {
                sb.append(str.charAt(i));

            }
            System.out.println(new String(sb));
            //System.out.println(sb.toString());
        }
    }
}
