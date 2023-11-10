public class Date {
    int date;
    String dateString;
    Date (String in) {
        this.dateString = in;
        this.date = Integer.parseInt(in.substring(6, 10)) * 10000 + Integer.parseInt(in.substring(0,2)) * 100 + Integer.parseInt(in.substring(3, 5));
    }




}
