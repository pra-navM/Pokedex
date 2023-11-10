import java.util.*;

public class Album {
    int NUMBER, max, numCards = 0;
    ArrayList<Card> cardList = new ArrayList<Card>();
    Date cDate;
    Scanner s = new Scanner(System.in);
    Album(int n, int m, String d) {
        this.NUMBER = n;
        this.max = m;
        this.cDate = new Date(d);
    }
    
    public void displayAll() {
        System.out.println("\nAlbum #: " + NUMBER + "\nMax cards: " + max + "\n# of cards: " + numCards + "\nCreation Date:" + this.cDate.dateString);
        for(int i = 0; i<this.cardList.size(); i++) {
            System.out.println("\n\tCard #: " + (i+1));
            System.out.println("\tName: " + this.cardList.get(i).name);
            System.out.println("\tDate of purchase/trade: " + this.cardList.get(i).day.dateString);
        }
    }
    public void displayCard() {
        System.out.println("Please enter the number of the card you would like displayed: ");
        int disNum; // declared for compilation
        while(true) {
            try {
                disNum = Integer.parseInt(s.nextLine());
                break;
            } catch(NumberFormatException e) {
                System.out.println("Try again!");
            }
        }
        System.out.println(this.cardList.get(disNum-1).toString());
    }
    public void addCard() {
        String nName, nType, nDate, nAttack1, nAttack2;
        int nHP;
        System.out.println("Please enter the name of the card");
        nName = s.nextLine();
        System.out.println("Please enter the type of the card: ");
        nType = s.nextLine();
        System.out.println("Please enter the date that the card was purchased with the format mm/dd/yyyy");
        while(true) {
            try {
                nDate = s.nextLine();
                if(nDate.charAt(2)!='/' || nDate.charAt(5)!='/') {
                    System.out.println(nDate.charAt(-1));
                }
                new Date(nDate);
                break;
            } catch (NumberFormatException e) {
                System.out.println("FOLLOW THE FORMAT!");
            }
        }
        System.out.println("Please enter the HP of the card: ");
        while(true) {
            try {
                nHP = Integer.parseInt(s.nextLine());
                break;
            } catch(NumberFormatException e) {
                System.out.println("HP must be an Integer!");
            }
        }
        cardList.add(new Card(nName, nHP, nType, nDate));
        System.out.println("How many attacks would you like to add: ");
        while(true) {
            try{
                int j = Integer.parseInt(s.nextLine());
                for(int i = 0; i<j; i++) {
                    System.out.println("Please enter the name and description seperated by a '-' (description optional): ");
                    nAttack1 = s.nextLine();
                    System.out.println("Please enter the DMG of the attack: ");
                    nAttack2 = s.nextLine();
                    this.cardList.get(this.cardList.size()-1).addAttack(nAttack1, nAttack2);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter an Integer!");
            }
        }
        System.out.println(this.cardList.get(this.cardList.size()-1).toString());
        this.numCards++;
    }
    public void removeCard() {
        int option;
        System.out.println("Please enter a number indicating what you would like to do: \n\t1. Remove first card\n\t2. Remove last card\n\t3. Remove by card name\n\t4. Remove by HP\n");
        while(true) {
            try {
                option = s.nextInt();
                if(option<1 || option > 4) {
                System.out.println("Please enter a number between 1 and 4!");
                } else {
                break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter an Integer!");
            }
        }
        int removedCards=0;
        switch(option) {
            case 1: // remove first
                this.cardList.remove(0);
                this.numCards--;
                System.out.println("This is the new list: ");
                displayAll();
                break;
            case 2: // remove last
                this.cardList.remove(this.cardList.size()-1);
                this.numCards--;
                System.out.println("This is the new list: ");
                displayAll();
                break;
            case 3: // remove by name
                System.out.println("Please enter the Name of the card(s) you would like to remove: ");
                String rName = s.nextLine();
                int RESULT =0;
                while(RESULT!=-1) {
                    RESULT = binarySearch(rName, 1);
                    this.cardList.remove(RESULT);
                    this.numCards--;
                    removedCards++;
                }
                System.out.printf("Removed cards: %d", removedCards);
                System.out.println("This is the new list: ");
                displayAll();
                break;
            case 4: // remove by hp
                System.out.println("Please enter the HP of the card(s) you would like to remove: ");
                String rHP = "";
                while(true) {
                    try {
                        rHP = s.nextLine();
                        Integer.parseInt(rHP);
                        break;
                    } catch(NumberFormatException e) {
                        System.out.println("HP must be an Integer!");
                    }   
                }
                int RESULT1 =0;
                while(RESULT1!=-1) {
                    RESULT1 = binarySearch(rHP, 2);
                    this.cardList.remove(RESULT1);
                    this.numCards--;
                    removedCards++;
                }
                System.out.printf("Removed cards: %d", removedCards);
                System.out.println("This is the new list: ");
                displayAll();
                break;
        }
    }
    public void editAttack(int NUM) {
        System.out.println(this.cardList.get(NUM).toString());
        System.out.print("\nPlease indicate which attack you want to edit using the attack number: ");
        int aNum, opt; //declared outside of loops for syntax
        while(true) {
            try {
                aNum = this.s.nextInt();
                System.out.println(this.cardList.get(NUM).attacks.get(aNum-1).toString());
                break;
            } catch(NumberFormatException e) {
                System.out.println("Please enter a number that is within the number of attacks!");
            }
        }
        System.out.print("\nPlease indicate using a number what you want to edit (1 - Name, 2 - Description, 3 - DMG): ");
        while(true) {
            try {
                opt = this.s.nextInt();
                break;
            } catch(NumberFormatException e) {
                System.out.println("Please enter a number between 1 and 3!");
            }
        }
        System.out.println("Please enter what you would like to replace it with: ");
        String n = this.s.nextLine();
        this.cardList.get(NUM).attacks.get(aNum-1).edit(opt, n);
        System.out.println("Here are your changes: ");
        System.out.println(this.cardList.get(NUM).toString());
    }
    public void sortCards(int option) {
        switch(option) {
            case 1: //name
                Collections.sort(this.cardList, new sortByName());
                System.out.println("New sorted order: ");
                displayAll();
                break;
            case 2: //HP
                Collections.sort(this.cardList, new sortByHP());
                System.out.println("New sorted order: ");
                displayAll();
                break;
            case 3: //date
                Collections.sort(this.cardList, new sortByDate());
                System.out.println("New sorted order: ");
                displayAll();
                break;
        }
    }
    public Card getCard(int NUM) {
        return this.cardList.get(NUM);
    }
    public void readCard(Card a) {
        this.cardList.add(a);
        numCards++;
    }

    public int binarySearch(String data, int BINOPTION) {
        int l = 0, r = this.cardList.size()-1;
        switch(BINOPTION) {
            case 1:
                ArrayList<Card> temp = new ArrayList<>(cardList);
                Collections.sort(temp, new sortByName());
                while(l<=r) {
                    int m = l+(r-1) /2;
                    int res = data.compareTo(temp.get(m).name);
                    if(res==0) {
                        return m;
                    }
                    if(res>0) {
                        l=m+1;
                    } else {
                        r = m-1;
                    }
                }
                return -1;
            case 2: // by hp
                ArrayList<Card> temp1 = new ArrayList<>(cardList);
                Collections.sort(temp1, new sortByHP());
                int hps = Integer.parseInt(data);
                while(l<=r) {
                    int m = l+(r-1) /2;
                    if(temp1.get(m).HP==hps) {
                        return m;
                    }
                    if(temp1.get(m).HP<hps) {
                        l=m+1;
                    } else {
                        r = m-1;
                    }
                }
                return -1;
        }
        return -1; // unreachable code required to compile
    }
}
class sortByAlbumDate implements Comparator<Album> { // helper class to sort for binary search
    public int compare(Album a, Album b) {
        return a.cDate.date -b.cDate.date;
    }
}
class sortByAlbumNum implements Comparator<Album> {
    public int compare(Album a, Album b) {
        return a.NUMBER - b.NUMBER; 
    }
}

