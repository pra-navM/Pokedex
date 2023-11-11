public class Date {
    private int idate;
    private String sdate;
    Date (String in) {
        this.sdate = in;
        this.idate = Integer.parseInt(in.substring(6, 10)) * 10000 + Integer.parseInt(in.substring(0,2)) * 100 + Integer.parseInt(in.substring(3, 5));
    }

    public int getIdate(){
        return idate;
    }
    public String getSdate(){
        return sdate;
    }





}
