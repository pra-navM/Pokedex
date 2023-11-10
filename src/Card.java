import java.util.*;
public class Card{
    String name;
    int HP;
    String type;
    Date day;
    ArrayList<Attack> attacks = new ArrayList<Attack>();

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
        return  ret + "\nDate of purchase/trade: " + this.day.dateString;
    }
}

class sortByName implements Comparator<Card> {
    public int compare(Card a, Card b) {
        return a.name.compareTo(b.name);
    }
}
class sortByHP implements Comparator<Card> {
    public int compare(Card a, Card b) {
        return a.HP - b.HP;
    }
}
class sortByDate implements Comparator<Card> {
    public int compare(Card a, Card b) {
        return a.day.date - b.day.date;
    }
}

