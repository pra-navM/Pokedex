import java.util.*;
import java.io.*;

//Pranav Manjunath
//11/10/2023
//This program is a pokedex that stores, sorts, and removes pokemon cards/albums by various criteria

public class Driver {

    public static void main(String[] main) {
        Scanner s = new Scanner(System.in);
        ArrayList<Album> deck = new ArrayList<Album>();
        System.out.println("Welcome to Pranav's pokemon card deck manager!");
        while(true) {
            System.out.println("Please indicate what you would like to do by entering an integer: \n\t 1. Access deck related options\n\t 2. Access album related options\n\t 3. Exit");
            String answer = s.nextLine();
            boolean boo2 = true;
            switch(answer) { //different cases based on what answer is
                case "1": //deck related options
                    while(boo2) {
                        System.out.println("\nPlease indicate what you would like to do by entering an integer: \n\t1. Display a list of all albums \n\t2. Display information on a particular album \n\t3. Add an album");
                        System.out.println("\t4. Remove an album (2 options) \n\t5. Show statistics \n\t6. Return back to main menu"); //prints out list of options
                        String answer2 = s.nextLine();
                        switch(answer2) { //switch moves between album related options
                            case "1": //display
                                displayAlbums(deck);
                                break;
                            case "2"://display specific album
                                displayAlbums(deck);
                                if(deck.size()>0) {
                                    System.out.println("Please pick an album to display with the album number");
                                    while(true) {
                                        try {
                                            String albInfo = s.nextLine();
                                            Integer.parseInt(albInfo);
                                            albumInfo(albInfo, deck);
                                            break;
                                        } catch(NumberFormatException | IndexOutOfBoundsException e) {
                                            System.out.println("Error, please enter again");
                                        }

                                    }
                                }
                                break;
                            case "3": //add
                                System.out.println("Please enter the name of the Album file: ");
                                Scanner inFile;
                                while(true) {
                                    try {
                                        String fileNmae = s.nextLine();
                                        inFile = new Scanner(new File(fileNmae + ".txt"));
                                        break;
                                    } catch(NumberFormatException | FileNotFoundException e) {
                                        System.out.println("Please enter a valid file name to a .txt file");
                                    }
                                }
                                addAlbum(inFile,deck);
                                break;
                            case "4": // remove
                                removeAlbum(s,deck);
                                break;
                            case "5": // show statistics
                                showStatistics(deck);
                                break;
                            case "6": // exit
                                boo2 = false;
                                break;
                            default:
                                System.out.println("Please enter and integer between 1 and 6");
                        }
                    }
                    break;
                case "2"://album related options

                    if(deck.size()==0) {
                        System.out.println("Add an album first!");
                    } else {
                        displayAlbums(deck);
                        int MUM;
                        if(deck.size()==1) {
                            MUM = deck.get(0).getNUMBER();
                            System.out.println("You only have one album which has been selected, which is this deck: ");
                            albumInfo(""+MUM,deck);
                        } else {
                            System.out.println("Please select an album by album number");
                            while(true) {
                                try{
                                    MUM = Integer.parseInt(s.nextLine());
                                    int deckCHECK = binarySearch(""+MUM,1,deck);
                                    deck.get(deckCHECK);
                                    System.out.println("Selected Album: ");
                                    albumInfo(""+MUM,deck);
                                    break;
                                } catch(NumberFormatException | IndexOutOfBoundsException e) {
                                    System.out.println("Please enter a number of an existing album");
                                }
                            }
                        }
                        while(boo2) {
                        int deckCHECK = binarySearch(""+MUM,1,deck);
                        System.out.println("Please indicate what you would like to do by entering an integer: \n\t1. Display all cards (in the last sorted order) \n\t2. Display information on a particular card \n\t3. Add a card");
                        System.out.println("\t4. Remove a card (4 options) \n\t5. Edit attack \n\t6. Sort cards (3 options) \n\t7. Return back to main menu");
                        String answer2 = s.nextLine();
                        switch(answer2) {

                            case "1": //display all in last sorted order
                                deck.get(deckCHECK).displayAll();
                                break;
                            case "2": //display information on a particular card
                                deck.get(deckCHECK).displayCard();
                                break;
                            case "3": // add a card
                                deck.get(deckCHECK).addCard();
                                break;
                            case "4": // remove a card(4 options)
                                deck.get(deckCHECK).removeCard();
                                break;
                            case "5": // edit attack
                                deck.get(deckCHECK).displayAll();
                                System.out.println("Please select a card with the card number: ");
                                int cardMUM;
                                while(true) {
                                    try{
                                    cardMUM = Integer.parseInt(s.nextLine());
                                    deck.get(deckCHECK).getCard(cardMUM-1);
                                    System.out.println("Selected Card: ");
                                    break;
                                    } catch(NumberFormatException | IndexOutOfBoundsException e) {
                                        System.out.println("Please enter a number of an existing card");
                                    }
                                }
                                deck.get(deckCHECK).editAttack(cardMUM-1);
                                break;
                            case "6": // sort cards (3 options)
                                System.out.println("Please enter an integer indicating what you would like to sort by: \n\t1. Name\n\t2. HP\n\t3. Date");
                                while(true) {
                                    try{
                                        int choice = Integer.parseInt(s.nextLine());
                                        if(choice<4 && choice>0) {
                                            deck.get(deckCHECK).sortCards(choice);
                                            break;
                                        }
                                        else throw new NumberFormatException();
                                    } catch(NumberFormatException | IndexOutOfBoundsException e) {
                                        System.out.println("Please enter an integer between 1 and 3! ");
                                    }
                                }
                                break;
                            case "7": // exit
                                boo2 = false;
                                break;
                            default:
                            System.out.println("Please enter and integer between 1 and 7");
                            }
                        }
                    }
                    
                    break;
                case "3": //EXIT
                    System.out.println("Thanks for using my deck manager!");
                    System.exit(0);
                default: //did not enter an integer from 1-3
                    System.out.println("Please enter an integer from 1-3!");
            }
        }

    }

    public static void displayAlbums(ArrayList<Album> deck) {
        if(deck.size()>0) {
            for(int i =0; i<deck.size(); i++) {
                System.out.println("\n\tAlbum #: " + deck.get(i).getNUMBER());
                System.out.println("\tDate of creation: " + deck.get(i).getcDate().getSdate());
            }
        } else {
            System.out.println("you have no albums to display!");
        }
    }

    public static void albumInfo(String albumNumber, ArrayList<Album> deck) {
        int deck1 = binarySearch(albumNumber, 1,deck);
        System.out.println("\n\tAlbum #: " + albumNumber);
        System.out.println("\tAlbum creation date: " + deck.get(deck1).getcDate().getSdate());
        System.out.println("\tAlbum capacity: " + deck.get(deck1).getMax());
        System.out.println("\t# of cards: " + deck.get(deck1).getNumCards());
        int totalHP = 0;
        for(int i = 0; i< deck.get(deck1).getNumCards(); i++) {
            totalHP+= deck.get(deck1).getCard(i).getHP();
        }
        System.out.println("\tTotal HP: " + totalHP);
    }

    public static void addAlbum(Scanner inFile, ArrayList<Album> deck) {
        int aNUMBER = Integer.parseInt(inFile.nextLine());
        if(binarySearch("" + aNUMBER, 1,deck)!=-1) {
            System.out.println("Duplicate album numbers are not allowed!");
            return;
        }
        String newdate = inFile.nextLine();
        int newamax = Integer.parseInt(inFile.nextLine());
        deck.add(new Album(aNUMBER, newamax, newdate));
        String numCards = inFile.nextLine();
        for(int i = 0; i<Integer.parseInt(numCards); i++) {
            deck.get(deck.size()-1).readCard(new Card(inFile.nextLine(), Integer.parseInt(inFile.nextLine()), inFile.nextLine(), inFile.nextLine()));
            String numAttacsk = inFile.nextLine();
            for(int j = 0; j<Integer.parseInt(numAttacsk); j++) {
                String in1 = inFile.nextLine(), in2 = inFile.nextLine();
                deck.get(deck.size()-1).getCard(deck.get(deck.size()-1).getNumCards()-1).addAttack(in1, in2);
            }
        }
        deck.get(deck.size()-1).displayAll();
    }
    public static void removeAlbum(Scanner s,ArrayList<Album> deck) {
        
        int option;
        int removedAlbums = 0;
        while(true) {
            System.out.println("Please indicate using an integer what you would like to remove by: (1 - Album #, 2 - Album date)");
            while(true) {
                try {
                    option = Integer.parseInt(s.nextLine());
                    break;
                }   catch (NumberFormatException e) {
                    System.out.println(" Give proper format");
                }
            }
            switch(option) {
            case 1:
                System.out.println("please enter the number of the album you would like to remove: ");
                String removeData = "";
                while(true) {
                    try{
                        removeData = s.nextLine();
                        Integer.parseInt(removeData);
                        break;
                    } catch(Exception e) {
                        System.out.println("enter an integer");
                    }
                }
                int RESULT = binarySearch(removeData, option, deck);
                if(RESULT!=-1) {
                    deck.remove(RESULT);
                    removedAlbums++;
                }
                System.out.println("Removed albums: " + removedAlbums);
                System.out.println("This is the new list: ");
                displayAlbums(deck);
                return;
            case 2:
                System.out.println("please enter the date of the album you would like to remove with the format mm/dd/yyyy: ");
                String removeData1 = "";
                while(true) {
                    try {
                        removeData1 = s.nextLine();
                        if(removeData1.charAt(2)!='/' || removeData1.charAt(5)!='/') {
                            System.out.println(removeData1.charAt(-1));
                        }
                        new Date(removeData1);
                        break;
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        System.out.println("Invalid format, please re-enter");
                    }
                }
                int RESULT1 = 0;
                while(true) {
                    RESULT1 = binarySearch(removeData1, option, deck);
                    if(RESULT1==-1) {
                        break;
                    }
                    deck.remove(RESULT1);
                    removedAlbums++;
                }
                System.out.println("Removed albums: " + removedAlbums);
                System.out.println("This is the new list: ");
                displayAlbums(deck);
                return;
            default:
                System.out.println("Please enter an Integer between 1 and 2!");
                break;
            }
        }
    }
    public static void showStatistics(ArrayList<Album> deck) {
        if(deck.size()>0) {
            double averageSHP=0, averageTHP=0;
            int tCards=0, tMax=0;
            for(int i= 0; i<deck.size(); i++) {
                System.out.println("Album #: " + deck.get(i).getNUMBER());
                System.out.println("\tCards/Max: " + deck.get(i).getNumCards() + " / " + deck.get(i).getMax());
                tCards+=deck.get(i).getNumCards();
                tMax+=deck.get(i).getMax();
                for(int j = 0; j<deck.get(i).getNumCards(); j++) {
                    averageSHP+=deck.get(i).getCard(j).getHP();
                }
                averageSHP = averageSHP/deck.get(i).getNumCards();
                System.out.println("\tAverage HP: " + averageSHP);
                averageTHP+=averageSHP;
                averageSHP=0;
            }
            System.out.println("\n\tTotal Cards/Total Max: " + tCards + " / " + tMax);
            System.out.println("\tAverage Total HP: " + averageTHP);
        } else {
            System.out.println("No albums in deck!");
        }
    }
    public static int binarySearch(String data, int BINOPTION, ArrayList<Album> deck) {
        int l = 0, r = deck.size()-1;
        ArrayList<Album> temp = new ArrayList<>(deck);
        switch(BINOPTION) {
            case 1:  // search by number
                Collections.sort(temp, new sortByAlbumNum());
                int nData = Integer.parseInt(data);
                while(l<=r) {
                    int m = l+(r-1) /2;
                    if(temp.get(m).getNUMBER()==nData) {
                        return m;
                    }
                    if(temp.get(m).getNUMBER()<nData) {
                        l=m+1;
                    } else {
                        r = m-1;
                    }
                }
                return -1;
            case 2: // search by date
                Collections.sort(temp, new sortByAlbumDate());
                while(l<=r) {
                    int m = l+(r-1) /2;
                    int num1 = Integer.parseInt(data.substring(6, 10)) * 10000 + Integer.parseInt(data.substring(0,2)) * 100 + Integer.parseInt(data.substring(3, 5));
                    if(temp.get(m).getcDate().getIdate() == num1) {
                        return m;
                    }
                    if(temp.get(m).getcDate().getIdate() < num1) {
                        l=m+1;
                    } else {
                        r = m-1;
                    }
                }

                return -1;
        }
        return 0; // to compile
    }
}