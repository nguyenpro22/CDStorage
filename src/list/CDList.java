package list;

import data.CD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CDList extends ArrayList<CD> {

    public CDList() {
        readFile();
    }

    public ArrayList<CD> getFanssionList() {
        return this;
    }

    public boolean addCD(CD cd) {
        for (CD item : this) {
            if (item.getIdCD().equals(cd.getIdCD())) {
                System.out.println("The ID is Existed!, Input again!!");
                return false;
            }
        }
        return this.add(cd);

    }

    @Override
    public boolean add(CD cd) {
        int idx = indexOf(cd);
        if (idx >= 0) {
            remove(idx);
        }
        return super.add(cd);
    }

    public CD updateCD(String id) {
        CD cd = searchByID(id);
        if (cd != null) {
            cd.update();
        }
        return cd;
    }

    public CD searchByID(String id) {
        for (CD item : this) {
            if (item.getIdCD().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    public CD deleteCD(CD cd) {
        return this.remove(this.indexOf(cd));
    }

    private int indexOf(CD cd) {
        int idx = 0;
        for (CD item : this) {
            if (item.getIdCD().equals(cd.getIdCD())) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    public void showList() {
        for (CD item : this) {
            item.showInfor();
        }
    }

    public boolean saveToFile() {
        File f = new File("CDStorage.txt");
        try {
            try (PrintWriter writer = new PrintWriter(f)) {
                writer.print("");
            }
            OutputStream outputStream = new FileOutputStream(f);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            for (CD item : this) {
                outputStreamWriter.write(item.toString());
                outputStreamWriter.write("\n");
            }
            outputStreamWriter.flush();//lưu xong mới ngừng
            return true;
        } catch (IOException e) {
            System.out.println("Can not Save");
            return false;
        }
    }

    private boolean readFile() {
        this.clear();
        File f = new File("CDStorage.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                //xử lý line
                CD cd = new CD();
                StringTokenizer stLine = new StringTokenizer(line, ",");
                String[] lineParameters = new String[CD.getATTRIBUTE_COUNT()];
                for (int i = 0; i < CD.getATTRIBUTE_COUNT(); i++) {
                    lineParameters[i] = stLine.nextToken().trim();
                }
                if (cd.setAttribute(lineParameters) != 0) {
                    this.add(cd);
                }
                line = reader.readLine();
            }

            return true;
        } catch (IOException e) {
            System.out.println("File Error Exception");
            return false;
        } catch (NumberFormatException e) {
            System.err.println("Wrong number format. Check \"" + f + "\" file again. Exiting...\n");
            System.exit(1);
            return false;
        }
    }

    public void sortByTitle() {
        Collections.sort(this, (CD c1, CD c2) -> c1.getTitleCD()
                .compareToIgnoreCase(c2.getTitleCD()));
    }

    public List<CD> filterName(String type) {
        List<CD> list = this.stream()
                .filter(item -> item.getNameCD().toLowerCase().matches(type.toLowerCase())).toList();
        return list;
    }

    public List<CD> filterTitle(String title) {
        List<CD> list = this.stream()
                .filter(item -> item.getTitleCD().toLowerCase().matches(title.toLowerCase())).toList();
        return list;
    }
}
