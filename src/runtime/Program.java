package runtime;

import list.CDList;
import java.util.Scanner;
import tool.Inputter;
import data.CD;
import java.util.List;
import ui.Menu;

public class Program {

    private static final Program instance = new Program();
    private static final CDList list = new CDList();
    Scanner sc = new Scanner(System.in);

    public static Program getInstance() {
        return instance;
    }

    private void addNewCD() {
        if (list.size() > 700) {
            System.out.println("full storage.");
            return;
        }

        CD cd = new CD();
        cd.inputInfor();
        while (!list.addCD(cd)) {
            cd.updateId();
        }
        System.out.println("Save success!");
    }

    private void searchByTitle() {
        if (list.isEmpty()) {
            System.out.println("mistake occur.");
            return;
        }
        String input = Inputter.getString("search CD title: ",
                "Please input in right format: ", true);
        List a = list.filterTitle(input);
        if (a.isEmpty()) {
            System.out.println("not found or nothing.");
            return;
        }
        for (Object obj : list) {
            CD cd = (CD) obj;
            cd.showInfor();
        }
    }

    private void print() {
        if (list.isEmpty()) {
            System.out.println("number of CD here: " + list.size());
            return;
        }
        System.out.println("number of CD here: " + list.size());
        System.out.println("(name, type, title, price, ID, publishing year)");
        list.sortByTitle();
        for (CD item : list.getFanssionList()) {
            item.showInfor();
        }
    }

    private void update() {
        if (list.isEmpty()) {
            System.out.println("Can't update because List is empty!");
            return;
        }
        System.out.print("input CD ID to update: ");
        String idCD = sc.nextLine();
        CD cd = list.updateCD(idCD);
        if (cd == null) {
            System.out.println("Can't find any CD have ID like " + idCD);
            return;
        }
        System.out.println("After Update:");
        cd.showInfor();
        System.out.print("If you wanna save, press y to confirm, other to cancel: ");
        String confirm = sc.nextLine();
        if (confirm.equals("y")) {
            list.set(list.indexOf(cd), cd);
            System.out.println("Save successfully!");
            return;
        }
        System.out.println("Cancel by USER!");
    }

    private void searchByName() {
        String input = Inputter.getString("Input Name Of CD: ",
                "Please Input a Name With Format (" + CD.getNAME_FORMAT() + "): ",
                CD.getNAME_PATTERN(), false);
        //        list.listReadFile(input);
        List a = list.filterName(input);
        if (a.isEmpty()) {
            System.out.println("Can't find anyone similar to " + input);
            return;
        }
        for (Object obj : a) {
            CD cd = (CD) obj;
            cd.showInfor();
        }

    }

    private void delete() {
        System.out.print("put CD ID to remove: ");
        String idCD = sc.nextLine();
        if (list.searchByID(idCD) == null) {
            System.out.println("Can't delete CD because the ID is not exist");
            return;
        }
        String confirm = Inputter.getString("Input \"delete\" to confirm: ",
                "This field is required!!!", true).toUpperCase();
        if (confirm.equals("DELETE")) {
            list.deleteCD(list.searchByID(idCD));
            System.out.println("Remove successfully!");
            return;
        }
        System.out.println("Canceled by User!");
    }

    private void save() {
        list.saveToFile();
    }

    private void process() {
        Menu mn = new Menu("CD House Management");
        mn.addNewOption("Add CD to the catalog");
        mn.addNewOption("Search CD by CD title");
        mn.addNewOption("Display the catalog");
        mn.addNewOption("Update CD");
        mn.addNewOption("Print list CDs from file.");
        mn.addNewOption("Quit.");
        int choice;

        while (true) {
            mn.print();
            choice = mn.getChoice();
            switch (choice) {
                case 1 : {
                    addNewCD();
                    list.saveToFile();
                }
                case 2 : {
                    searchByTitle();
                }
                case 3 : {
                    searchByName();
                }
                case 4 : {
                    Menu mn4 = new Menu("Update Product");
                    mn4.addNewOption("Update Product");
                    mn4.addNewOption("Delete Product");
                    mn4.addNewOption("Quit");
                    mn4.print();
                    int tmp = mn4.getChoice();
                    switch (tmp) {
                        case 1 : {
                            update();
                            save();
                        }
                        case 2 : {
                            delete();
                            save();
                        }
                        case 3 : {
                            break;
                        }
                    }
                }
                case 5 : {
                    print();
                    save();
                }
                case 6 : {
                    System.err.println("End process!");
                    return;
                }
            }
            Menu checkExit = new Menu("CD House Management");
            checkExit.addNewOption("Continue");
            checkExit.addNewOption("Exit");
            checkExit.print();
            choice = checkExit.getChoice();
            switch (choice) {
                case 2 : {
                    System.err.println("End process!");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Program mng = Program.getInstance();
        mng.process();
    }
}
