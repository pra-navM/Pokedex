import java.util.*;
public class Card{
    private String name;
    private int HP;
    private String type;
    private Date day;
    private ArrayList<Attack> attacks = new ArrayList<Attack>();


    public String getName(){
        return name;
    }
    public int getHP(){
        return HP;
    }
    public String getType(){
        return type;
    }
    public Date getDay(){
        return day;
    }

    public ArrayList<Attack> getAttacks(){
        return attacks;
    }

    Card(String n, int h, String t, String d) {
        this.name = n;
        this.HP = h;
        this.type = t;
        this.day = new Date(d);
    }

    public void addAttack(String in1, String in2) {
        this.attacks.add(new Attack(in1, in2));
    }

    public String toString() { //method to display as string
        String ret = "Name: " + this.name + "\nHP: " + this.HP + "\nType: " + this.type + "\n# of Attacks: " + attacks.size() + "\n";
        for(int i = 0; i<attacks.size(); i++) {
            ret += "\n\t" + (i+1) + ".";
            ret+=attacks.get(i).toString();
        }
        return  ret + "\nDate of purchase/trade: " + this.day.getSdate();
    }
}

class sortByName implements Comparator<Card> {
    public int compare(Card a, Card b) {
        return a.getName().compareTo(b.getName());
    }
}
class sortByHP implements Comparator<Card> {
    public int compare(Card a, Card b) {
        return a.getHP() - b.getHP();
    }
}
class sortByDate implements Comparator<Card> {
    public int compare(Card a, Card b) {
        return a.getDay().getIdate() - b.getDay().getIdate();
    }
}

