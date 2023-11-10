public class Attack {
    String name;
    String description = "";
    String dmg;

    Attack (String nd, String d) {
        String strArr[] = nd.split("-");
        this.name = strArr[0].trim();
        if(strArr.length>1) {
            this.description = strArr[1].trim();
        } 
        this.dmg = d;
    }
    
    public String toString() { //method to display as a string
        return "\n\tName: " + this.name + "\n\tDescription: " + this.description + "\n\tDMG: " + this.dmg;
    }

    public void edit(int option, String n) {
        switch(option) {
            case 1:
                this.name = n;
                break;
            case 2:
                this.description = n;
                break;
            case 3:
                this.dmg = n;
        }
    }
}
