public class test {
    public static void main(String[] args) {
        String a = "12/34/5678";
        int b = Integer.parseInt(a.substring(6, 10)) * 10000; 
        b+= Integer.parseInt(a.substring(0, 2)) * 100;
        b+= Integer.parseInt(a.substring(3, 5));
        System.out.println(b);
    }
}
