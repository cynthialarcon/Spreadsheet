
/**
 * Write a description of class SheetList here.
 * 
 * @author (Jason Fell - 9920196, Briain O Driscoll - 16143167, Elles Van Timmeren - 16053222, Cynthia Alarcon Campos - 16068858) 
 * @version (a version number or a date)
 */
public class SheetList{
    //Declaring all of our variables that is needed
    private Sheet[] list;
    private Sheet[] temp;
    private int nextPosition = 0;
    private int initialSheets = 3;
    private int initialSheetsNumbers = 1;
    private int sheetNumbers = 4;
    
    //Our constructor that declares SheetList to be an array of Sheet with size 256
    public SheetList(){
        this.list = new Sheet[256];
        this.temp = new Sheet[1];//this is used as a temporary sheet while moving sheets in the array.
    }

    
    
    //our method add, adds a new Sheet name to the list
    public boolean add(){
        String names = "";
        //Initialises 3 sheets at the start
        for(int i = 0; i < this.initialSheets; i++){
            this.list[i] = new Sheet("");//Creates sheet with empty string
            this.list[i].setName("Sheet" + this.initialSheetsNumbers);//sets new name for empty string
            this.initialSheetsNumbers++;//a counter for sheet numbers
            System.out.println("Name of the Sheets are " + this.list[i].getName());//prints to screen the name of the sheets
        }
        for(int i = 3; i < this.list.length; i++){
            this.list[i] = new Sheet("");//initialises the array list with empty strings as names
        }
        if (this.nextPosition < this.list.length) { // if SheetList list isnt full
            int i;
           
           while (!(this.list[this.nextPosition].getName().equals("")) ){ //Loops till it finds next available index
                this.nextPosition++;//counter, increase nextPosition by one every time condition is false
           }
           //loops once to create one sheet
            for(i = this.nextPosition; i < this.nextPosition + 1; i++){
            this.list[i].setName("Sheet" + sheetNumbers);//setting name for sheet list                     
            System.out.println("Sheet added with name " + this.list[i].getName());
            sheetNumbers++;//counter for sheet numbers, keep adding one at the end for new number when add is invoked again
            return true;//new sheet name created so return true
           }   
        }
        return false;//return false if sheet name in list not created
    }
    
    //method to remove a sheet name
    public int remove(String sheetName){   
        int indexOfsheetName = -1;//Not an index in the array hence -1
        Sheet temp;//temp is a sheet that holds list sheet until it gets removed
        
        
        for(int i = 0; i < this.list.length; i++){
                
            if(this.list[i].getName().equalsIgnoreCase(sheetName)){//getName() calls Sheet class for Sheet name.
                indexOfsheetName = i;
                temp = this.list[indexOfsheetName];//holds sheet from list in temporary sheet
                this.list[indexOfsheetName] = null;//Deletes the object at index 'indexOfsheetName'.
                System.out.println("Sheet name deleted from List");
            }
        }
        if((indexOfsheetName < this.list.length - 1)){// < 255 - < one before last
             for(int j = indexOfsheetName + 1; j < this.list.length + 1; j++){
                 this.list[indexOfsheetName] = this.list[indexOfsheetName + 1];//Shifting all the objects from right to left after one gets deleted.
             }
             return indexOfsheetName;//return index of the sheet name removed
        }else if(indexOfsheetName == this.list.length - 1){// = 255 - last element
             return indexOfsheetName;//return index of the sheet name removed
        }
            
        return -1;//Default return
    }
        
      
    //method to remove specified index returning Sheet name associated
    public String remove(int index){
        int indexOfsheetName1 = -1;
        Sheet temp;//temporary sheet holder
        //checking if the index is in the list
        for(int i = 0; i < this.list.length; i++){
            if(index == i){
                indexOfsheetName1 = index; //index is in range of list
            }else{
                return "";//if index not in range then return empty string
            }   
        }
        temp = this.list[indexOfsheetName1];//temporary sheet holder, sheet from list.
        this.list[indexOfsheetName1] = null;//Deletes the object at index 'indexOfsheetName1'.
        System.out.println("Sheet index deleted from List");
        if(indexOfsheetName1 < this.list.length - 1){// <= 254 
            for(int j = indexOfsheetName1; j < this.list.length; j++){
                this.list[indexOfsheetName1] = this.list[indexOfsheetName1 + 1];//Shifting all the objects from right to left after one gets deleted.
            }
            return this.list[indexOfsheetName1].getName();//return sheet name to main
        }else if(indexOfsheetName1 == this.list.length - 1){//Last element in list.
            return this.list[indexOfsheetName1].getName();//return sheet name to main
        }

        return ""; 
    }

    public int move(String from, String to, boolean before){
        int fromIndex = -1; //cant be in the list range of indexes
        int toIndex = -1;//cant be in the list range of indexes
        Sheet temp;//temporary sheet holder
        
        for(int i = 0; i < this.list.length; i++){//Checks if both sheetNames, from and to, exist.
            if(this.list[i].getName().equalsIgnoreCase(from)){//comparison condition
                fromIndex = i;//indexes sheet names
            }else{
                return -1;//index of sheet name not found
            }
            if(this.list[i].getName().equalsIgnoreCase(to)){
                toIndex = i;//indexes sheet names
            }else{
                return -1;//index of sheet name not found
            }
        }
        if((toIndex - fromIndex > 1) && (toIndex - fromIndex <= 255)){//If there is at least 1 space between 'from' and 'to' and 'to'.
            if(before){//Before, before = true.
                temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list
                this.list[fromIndex] = null;
                for(int i = fromIndex + 1; i < toIndex; i++){//Shifting elements from left to right, fromIndex+1 to fromIndex etc. 
                    this.list[i-1] = this.list[i];//Shifting elements from right to left
                }
                this.list[toIndex-1] = temp;////use temp to temporarily store sheet at fromIndex from list.Between 2 and before 254
                return toIndex-1;
            }else{//After 254
                temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list
                this.list[fromIndex] = null;//deletes sheet at index fromIndex
                for(int i = fromIndex + 1; i < toIndex + 1; i++){
                    this.list[i-1] = this.list[i];//Shifting elements from right to left
                }
                this.list[toIndex] = temp;//use temp to temporarily store sheet at fromIndex from list.Between 2 and after 254
                return toIndex;//return index at toIndex
             }

        }else if((toIndex - fromIndex == 1) && (toIndex <= 255)){//side by side
            if(!before){
                temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list
                this.list[fromIndex] = null;//deletes sheet at index fromIndex
                for(int i = fromIndex + 1; i < toIndex + 1; i++){//Shifting elements from right to left
                    this.list[i-1] = this.list[i];
                }
                this.list[toIndex] = temp;//use temp to temporarily store sheet at toIndex from list
                return toIndex;//returns toIndex to main
            }
        }
        if((fromIndex - toIndex > 1) && (toIndex >= 0)){//If there is at least 1 space between 'from' and 'to' and 'to' not end element.
            if(before){//Before toIndex
                temp = this.list[fromIndex];//
                this.list[fromIndex] = null;//deletes sheet at index fromIndex
                for(int i = toIndex; i < fromIndex; i++){//Shifting elements from left to right, fromIndex+1 to fromIndex etc. 
                    this.list[i+1] = this.list[i];//shifting elements left to right
                }
                this.list[toIndex] = temp;//use temp to temporarily store sheet at fromIndex from list
                return toIndex;//returns toIndex to main 
            }else{//After toIndex
                temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list
                this.list[fromIndex] = null;//Deletes sheet at fromIndex
                for(int i = toIndex + 1; i < fromIndex; i++){
                    this.list[i+1] = this.list[i];//shifting elements left to right
                }
                this.list[toIndex+1] = temp;//use temp to temporarily store sheet at toIndex+ 1 from list
                return toIndex+1;//returns toIndex+1 to main
            }
        }else if((fromIndex - toIndex == 1) && (toIndex >= 0)){//Checking if fromIndex and toIndex are side by side
            if(before){
                temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list
                this.list[fromIndex] = null;
                for(int i = toIndex; i < fromIndex; i++){
                    this.list[i+1] = this.list[i];////shifting elements left to right
                }
                this.list[toIndex] = temp;//use temp to temporarily store sheet at toIndex from list
                return toIndex;//returns toIndex to main
            }
        }
        return -1;//default return
    }  

    public String move(int from, int to, boolean before){
        int fromIndex = -1; 
        int toIndex = -1;
        Sheet temp;//temporary sheet holder
        
        if((from >= 0 && from <= 255) && (to >= 0 && to <= 255)){//checks if from and to are in correct range
            fromIndex = from; 
            toIndex = to;
            //moves a specified sheet to end of the list of sheets in the array
            if((toIndex - fromIndex > 1) && (toIndex - fromIndex <= 255)){//If there is at least 1 space between 'from' and 'to'.
                if(before){//Before, before = true.
                    temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list
                    this.list[fromIndex] = null;
                    for(int i = fromIndex + 1; i < toIndex; i++){//Shifting elements from right to left, fromIndex+1 to fromIndex etc. 
                        this.list[i-1] = this.list[i];//Shifting elements from right to left
                    }
                    this.list[toIndex-1] = temp;//use temp to temporarily store sheet at fromIndex from list.Between 2 and before 254
                    return this.list[toIndex-1].getName();
                }else{//After 254
                    temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list.
                    this.list[fromIndex] = null;
                    for(int i = fromIndex + 1; i < toIndex + 1; i++){
                        this.list[i-1] = this.list[i];//Shifting elements from right to left
                    }
                    this.list[toIndex] = temp;//use temp to temporarily store sheet at toIndex from list.Between 2 and after 254
                    return this.list[toIndex].getName();//Return sheet name of toIndex
                }

            }else if((toIndex - fromIndex == 1) && (toIndex <= 255)){//
                if(!before){
                    temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list.
                    this.list[fromIndex] = null;
                    for(int i = fromIndex + 1; i < toIndex + 1; i++){//Shifting elements from right to left
                        this.list[i-1] = this.list[i];//Shifting elements from right to left
                    }
                    this.list[toIndex] = temp;//use temp to temporarily store sheet at fromIndex from list.
                    return this.list[toIndex].getName();//Return sheet name of toIndex
                }
            }
            
            if((fromIndex - toIndex > 1) && (toIndex >= 0)){//If there is at least 1 space between 'from' and 'to'.
                if(before){//Before toIndex
                    temp = this.list[fromIndex];
                    this.list[fromIndex] = null;
                    for(int i = toIndex; i < fromIndex; i++){//Shifting elements from left to right, fromIndex+1 to fromIndex etc. 
                        this.list[i+1] = this.list[i];//Shifting elements from left to right.
                    }
                    this.list[toIndex] = temp;////use temp to temporarily store sheet at fromIndex from list.
                    return this.list[toIndex].getName();
                }else{//After toIndex
                    temp = this.list[fromIndex];
                    this.list[fromIndex] = null;
                    for(int i = toIndex + 1; i < fromIndex; i++){
                        this.list[i+1] = this.list[i];//Shifting elements from left to right.
                    }
                    this.list[toIndex+1] = temp;//use temp to temporarily store sheet at fromIndex from list.
                    return this.list[toIndex+1].getName();
                }
            
            }else if((fromIndex - toIndex == 1) && (toIndex >= 0)){//
                if(before){
                    temp = this.list[fromIndex];
                    this.list[fromIndex] = null;
                    for(int i = toIndex; i < fromIndex; i++){
                        this.list[i+1] = this.list[i];//Shifting elements from left to right
                    }
                    this.list[toIndex] = temp;//use temp to temporarily store sheet at fromIndex from list.
                    return this.list[toIndex].getName();//Returns Sheet name of toIndex
                }
            }
        }
        return "";//Default return - empty string
    }
    
    
    public String moveToEnd(int from){
        int fromIndex = -1; //cant be index that is in list
        int toIndex = this.list.length - 1;//cant be index that is in list
        Sheet temp;//stores temporary sheet
        
        for(int i = 0; i < this.list.length; i++){//Checks if both sheetNames, from and to, exist.
            if(from == i){
                fromIndex = i;//checks if fromIndex is in range of list
            }else{
                return "";//returns empty string to main
            }
        }
        
        if((from >= 0 && from < this.list.length - 1) && (fromIndex != toIndex)){
            temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list.
            this.list[fromIndex] = null;//deletes sheet at fromIndex
            for(int i = fromIndex + 1; i < toIndex + 1; i++){//Shifting elements from right to left
                this.list[i-1] = this.list[i];//shifting elements right to left
            }
            this.list[toIndex] = temp;//let sheet at toIndex equal temp sheet which came from fromIndex
            return this.list[toIndex].getName();//return string sheet name at toIndex
        }
        return "";//Default return, empty string to main program
    }
    
    
    public int moveToEnd(String from){
        int fromIndex = -1;//cant be index that is in list 
        int toIndex = this.list.length - 1;//End of the index list.
        Sheet temp;//stores temporary sheet
        for(int i = 0; i < this.list.length; i++){//Checks if both sheetNames, from, exist.
            if(this.list[i].getName().equalsIgnoreCase(from)){
                fromIndex = i;//index of sheet name
            }else{
                return -1;//sheet name not in the list,return to main
            }
        }
        if((fromIndex >= 0 && fromIndex < 255) && (fromIndex != toIndex)){//fromIndex != toIndex covers entering the same index-only one element in list!
            //So if fromIndex and toIndex are the same it does nothing and returns -1 at end!
            temp = this.list[fromIndex];//use temp to temporarily store sheet at fromIndex from list. 
            this.list[fromIndex] = null;//deletes sheet at fromIndex
            for(int i = fromIndex + 1; i < toIndex + 1; i++){//Shifting elements from right to left
                this.list[i-1] = this.list[i];//shifting elements from right to left
            }
            this.list[toIndex] = temp;//let sheet at toIndex equal temp sheet which came from fromIndex
            return toIndex;//return to main with value toIndex
        }
        return -1;
    }

	//changes the current name of the sheet to a new name. 
    public int rename(String currentName, String newName){
        String sheetNameCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
        int indexOfNewName = -1;
        //Validates currentName characters, if it is not in sheetNameCharacters then it is invalid.
        for(int i = 0; i < currentName.length(); i++){//Validates currenName name characters, if it is not in sheetNameCharacters then it is invalid.
            char ch = currentName.charAt(i);
            for(int a = 0; a < sheetNameCharacters.length(); a++){//Need different index variables when one loop inside another. i and a
                char ch1 = sheetNameCharacters.charAt(a);
                if(ch != ch1){
                    return -1;//invalid name
                }
            }
        }
        //Validates newName characters, if it is not in sheetNameCharacters then it is invalid.
        for(int i = 0; i < newName.length(); i++){//Validates newName name characters, if it is not in sheetNameCharacters then it is invalid.
            char ch2 = newName.charAt(i);
            for(int b = 0; b < sheetNameCharacters.length(); b++){
                char ch3 = sheetNameCharacters.charAt(b);
                if(ch2 != ch3){
                    return -1;//invalid name
                }
            }
        }
        //Checks if currentName is in the array and newName is not. if so: currentName becomes newName (name change) and the index(position) of newName is returned.
        for(int i = 0; i < this.list.length; i++){
            if(this.list[i].getName().equalsIgnoreCase(currentName) && !(this.list[i].getName().equalsIgnoreCase(newName))){
                currentName = newName;//change name
                indexOfNewName = i;
                return indexOfNewName;//return to main
            }
        }
        return -1; 
    }
    // given a Sheetname, goes through list to find the specified SheetName and, when found, returns the position of specified SheetName. if not found returns -1 
    public int index(String sheetName){
        for(int i = 0; i < this.list.length; i++){
            if(this.list[i].getName().equalsIgnoreCase(sheetName)){//ive changed .equals to .equalsIgnoreCase.
                return i;
            } 
        }
        return -1;
    }
     //goes through list one position at a time until it gets to position stated in index, then returns the sheetname at this position, otherwise it returns -1
    public String sheetName(int index){
        for(int i = 0; i < this.list.length; i++){
            if(this.list[i] == this.list[index]){
                return this.list[index].getName();
            } 
        }
        return "";
    }
    //displays all the names of the sheets currently in list
    public void Display(){
        String displayOutput = "";
        for(int i = 0; i < this.list.length; i++){
            displayOutput += this.list[i].getName() + "\n";
        }
        System.out.println(displayOutput);
    }
    //gives the length of list aka number of sheets in the array list
    public int length(){
        return this.list.length;
    }
}