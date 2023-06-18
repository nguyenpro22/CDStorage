package data;

import java.time.Year;
import tool.Inputter;

public class CD {

    private String nameCD;
    private String typeCD;
    private String titleCD;
    private double priceCD;
    private String idCD;
    private Year publicYear;

    private static final String NAME_PATTERN = "game|movie|music";

    private static final String TYPE_PATTERN = "audio|video";

    private static final String NAME_FORMAT = "game/movie/music";
    private static final String TYPE_FORMAT = "audio/video";
    private static final String ID_PATTERN = "P\\d{4}";
    private static final String ID_FORMAT = "Pxxxx";
    private static final String YEAR_FORMAT = "19xx-"+Year.now();
    private static final int ATTRIBUTE_COUNT = 6;

    public CD() {
    }

    public static String getNAME_PATTERN() {
        return NAME_PATTERN;
    }

    public static String getNAME_FORMAT() {
        return NAME_FORMAT;
    }

    public static String getTYPE_PATTERN() {
        return TYPE_PATTERN;
    }

    public static String getTYPE_FORMAT() {
        return TYPE_FORMAT;
    }

    public static String getID_PATTERN() {
        return ID_PATTERN;
    }

    public static String getID_FORMAT() {
        return ID_FORMAT;
    }

    public static String getYEAR_FORMAT() {
        return YEAR_FORMAT;
    }

    public static int getATTRIBUTE_COUNT() {
        return ATTRIBUTE_COUNT;
    }

    public CD(String nameCD, String typeCD, String titleCD, float priceCD, String idCD, Year publicYear) {
        setNameCD(nameCD);
        setTypeCD(typeCD);
        setTitleCD(titleCD);
        setPriceCD(priceCD);
        setIdCD(idCD);
        setPublicYear(publicYear);
    }

    public String getTitleCD() {
        return titleCD;
    }

    public final void setTitleCD(String titleCD) {
        if (!titleCD.isBlank()) {
            this.titleCD = titleCD;
        }
    }

    public String getNameCD() {
        return nameCD;
    }

    public final void setNameCD(String nameCD) {
        if (nameCD.matches(NAME_PATTERN)) {
            this.nameCD = nameCD;
        }
    }

    public String getTypeCD() {
        return typeCD;
    }

    public final void setTypeCD(String typeCD) {
        if (typeCD.matches(TYPE_PATTERN)) {
            this.typeCD = typeCD;
        }
    }

    public double getPriceCD() {
        return priceCD;
    }

    public final void setPriceCD(double priceCD) {
        if (priceCD > 0) {
            this.priceCD = priceCD;
        }
    }

    public String getIdCD() {
        return idCD;
    }

    public final void setIdCD(String idCD) {
        if (idCD.matches(ID_PATTERN)) {
            this.idCD = idCD;
        }
    }

    public Year getPublicYear() {
        return publicYear;
    }

    public final void setPublicYear(Year publicYear) {
        if (publicYear.isAfter(Year.of(1900)) && publicYear.compareTo(Year.now()) <=0) {
            this.publicYear = publicYear;
        }
    }

    public int setAttribute(String[] lineParameters) {
        int idx = 0;
        if (lineParameters != null && lineParameters.length >= CD.ATTRIBUTE_COUNT) {
            setNameCD(lineParameters[idx++].trim());
            setTypeCD(lineParameters[idx++].trim());
            setTitleCD(lineParameters[idx++].trim());
            try {
                setPriceCD(Double.parseDouble(lineParameters[idx++].trim()));
            } catch (Exception e) {
                System.out.println("Error when setAttribute Unit Price!");
                return 0;
            }
            setIdCD(lineParameters[idx++].trim());
            try {
                setPublicYear(Year.of(Integer.parseInt(lineParameters[idx++].trim())));
            } catch (Exception e) {
                System.out.println("Error when setAttribute Publishing Year!");
                return 0;
            }
        }
        return idx;
    }

    public void inputInfor() {
        this.idCD = inputID(false);
        this.nameCD = inputName(false);
        this.typeCD = inputType(false);
        this.titleCD = inputTitle(false);
        this.priceCD = inputPrice(false);
        this.publicYear = inputYear(false);
    }

    public void updateId() {
        this.idCD = inputID(false);
    }

    public void update() {
        String name = inputName(true);
        if (!name.isBlank()) {
            setNameCD(name);
        }
        String type = inputType(true);
        if (!type.isBlank()) {
            setTypeCD(type);
        }
        String title = inputTitle(true);
        if (!title.isBlank()) {
            setTitleCD(title);
        }
        Double price = inputPrice(true);
        if(price != Math.PI){
            setPriceCD(price);
        }
        Year publicYear = inputYear(true);
        if(!publicYear.equals(Integer.MIN_VALUE)){
            setPublicYear(publicYear);
        }
    }

    private String inputName(boolean allowEmpty) {
        return Inputter.getString("Input Name Of CD: ",
                "Please Input a Name With Format (" + NAME_FORMAT + "): ", NAME_PATTERN, allowEmpty);
    }

    private String inputType(boolean allowEmpty) {
        return Inputter.getString("Input Type Of CD: ",
                "Please Input a Type With Format (" + TYPE_FORMAT + "): ", TYPE_PATTERN, allowEmpty);
    }

    private String inputTitle(boolean allowEmpty) {
        return Inputter.getString("Input title Of CD: ", "Please Input a title: ", allowEmpty);
    }

    private double inputPrice(boolean allowEmpty) {
        return Inputter.getAnDouble("Input price Of CD: ", "Please Input a price: ", allowEmpty);
    }

    private String inputID(boolean allowEmpty) {
        return Inputter.getString("Input ID Of CD: ",
                "Please Input a ID With Format (" + ID_FORMAT + "): ", ID_PATTERN, allowEmpty);
    }

    private Year inputYear(boolean allowEmpty) {
        return Year.of(Inputter.getAnInteger("Input Publishing Year: ",
                "Please Input A Year Within(" + YEAR_FORMAT + "): ",
                1900, Inputter.getYearFromDate(Inputter.getToday()), allowEmpty));
    }

    public void showInfor() {
        String str = String.format("%7s|%7s|%-20s|%6.2f|%5s|%5s", getNameCD(),
                getTypeCD(), getTitleCD(), getPriceCD(), getIdCD(), getPublicYear());
        System.out.println(str);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%.2f,%s,%s", getNameCD(),
                getTypeCD(), getTitleCD(), getPriceCD(), getIdCD(), getPublicYear());
    }

}
