
package ui;

import java.util.ArrayList;
import tool.Inputter;

public class Menu {
    public String title;
    public ArrayList<String> optionList = new ArrayList<>();   

    public Menu(String title) {
        this.title = title;
    }

    public void addNewOption(String newOption){
        optionList.add(newOption);
    }
    
    public void print(){
        int count = 1;
        System.out.println("--------"+title+"--------");
        for (String option : optionList) {
            System.out.println(count + "." + option);
            count++;
        }
    }
    public int getChoice(){
        int choice = Inputter.getAnInteger("Input your choice: ", 
                "You're entered wrong format\nPlease Input(1-"+(optionList.size())+"): ",
                1, optionList.size(), false);
        return choice;
    }
}
