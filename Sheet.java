
/**
 * Write a description of class Sheet here.
 * 
 * @author (Jason Fell - 9920196, Briain O Driscoll - 16143167, Elles Van Timmeren - 16053222, Cynthia Alarcon Campos - 16068858) 
 * @version (a version number or a date)
 */
public class Sheet{
    private String name;//Declares sheetName private so to keep the class maintained.
    
    
    
    public Sheet(String name){//Creates Sheet objects with a name
        this.name = name;
    }
    
    public String getName() {//Get name - returns name of Sheet
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;//Sets new name for Sheet
    }
}
