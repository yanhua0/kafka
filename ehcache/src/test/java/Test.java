import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args){
    Map<String,String> s=new HashMap<>();
    s.put("2","3");
    String ss=s.get("2");
    s.remove("2");
    System.out.println(ss);
    System.out.println(s.get("2"));
    }
}
