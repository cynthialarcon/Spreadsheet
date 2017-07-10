
/**
 * Write a description of class SheetListDriver here.
 * 
 * @author (Jason Fell - 9920196, Briain O Driscoll - 16143167, Elles Van Timmeren - 16053222, Cynthia Alarcon Campos - 16068858)
 * @version (a version number or a date)
 */
public class SheetListDriver{
    public static void main(String[] args){
        //All of our variables needed
        boolean addSheet = false;
        int removeSheetName = 0;
        String removeSheetIndex = "";
        int moveSheetNameToIndex = 0;
        String moveSheetIndexToIndex = "";
        String moveSheetIndexToEnd = "";
        int moveSheetNameToEnd = 0;
        int renameSheetName = 0;
        int indexOfSheetName = 0;
        String sheetNameAtIndex = "";
        String sheetNamesListDisplay = "";
        int lengthOfList = 0;
        
        SheetList list = new SheetList();//Instanciating list object        
        
        
        addSheet = list.add();//invoking add method from SheetList
        if(addSheet){
           System.out.println("New Sheet added to List");
        }else{
           System.out.println("New Sheet not added to List");
        }
      
        
        removeSheetName = list.remove("Sheet1");//invoking remove method from SheetList
        System.out.println("The index of the Sheet removed was " + removeSheetName);

        removeSheetIndex = list.remove(0);//Arbitrary value, invoking remove method from SheetList
        if(removeSheetIndex != null){
           System.out.println("Sheet name removed is " + removeSheetIndex);
        }else{
           System.out.println("Sheet name was not removed hence empty string " + "'" + removeSheetIndex + "'");
        }

        moveSheetNameToIndex = list.move("Sheet4", "Sheet5", true);//invoking move method from SheetList with three parameters
        System.out.println("Index of Sheet moved is " + moveSheetNameToIndex);

        moveSheetIndexToIndex = list.move(0, 3, false);//invoking move method from SheetList with three parameters
        System.out.println("Index of index moved is " + moveSheetIndexToIndex);

        moveSheetIndexToEnd = list.moveToEnd(0);//invoking moveToEnd method from SheetList with one parameter
        System.out.println("Name of Sheet moved to end is " + moveSheetIndexToEnd);

        moveSheetNameToEnd = list.moveToEnd("Sheet1");//invoking moveToEnd method from SheetList with one parameter
        System.out.println("Index of Sheet moved to end is " +  moveSheetNameToEnd);

        renameSheetName = list.rename("Sheet2", "abc 3");//invoking rename method from SheetList with two parameters
        System.out.println("Index of renamed Sheet is " + renameSheetName);

        indexOfSheetName = list.index("Sheet1");//invoking index method from SheetList with one parameter
        System.out.println("Index of Sheet name is " + indexOfSheetName);

        sheetNameAtIndex = list.sheetName(2);//invoking sheetName method from SheetList with one parameter
        System.out.println("Name of Sheet at specified index is " + sheetNameAtIndex);

        list.Display();//invoking Display method from SheetList with no parameters
        

        lengthOfList = list.length();//invoking length method from SheetList with no parameters
        System.out.println("Length of list is " + lengthOfList);
    }
}
