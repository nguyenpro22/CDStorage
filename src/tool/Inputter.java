package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Inputter {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final int CURRENT_YEAR = getYearFromDate(getToday());

    //method ép người dùng nhập số nguyên
    public static int getAnInteger(String inputMsg, String errorMsg, boolean type) {
        Scanner sc = new Scanner(System.in);
        System.out.print(inputMsg);
        int number;
        String input;
        while (true) {
            try {
                input = (sc.nextLine());
                if (type && input.isBlank()) {
                    return Integer.MIN_VALUE;
                }
                number = Integer.parseInt(input);
                return number;
            } catch (Exception e) {
                System.out.print(errorMsg);
            }
        }
    }

    //method ép người dùng nhập số nguyên trong khoảng nào đó
    public static int getAnInteger(String inputMsg, String errorMsg,
            int lowerBound, int upperBound, boolean allow) {
        Scanner sc = new Scanner(System.in);
        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.print(inputMsg);
        int number;
        String input;
        while (true) {
            try {
                input = (sc.nextLine());
                if (allow && input.isBlank()) {
                    return Integer.MIN_VALUE;
                }
                number = Integer.parseInt(input);
                if (number > upperBound || number < lowerBound) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.print(errorMsg);
            }
        }
    }

    //method ép người dùng nhập số thực
    public static double getAnDouble(String inputMsg, String errorMsg, boolean allow) {
        Scanner sc = new Scanner(System.in);
        System.out.print(inputMsg);
        String input;
        Double number;
        while (true) {
            try {
                input = (sc.nextLine());
                if (allow && input.isBlank()) {
                    return Math.PI;
                }
                number = Double.parseDouble(input);
                return number;
            } catch (Exception e) {
                System.out.print(errorMsg);
            }
        }
    }

    //method ép người dùng nhập số nguyên trong khoảng nào đó
    public static double getAnDouble(String inputMsg, String errorMsg,
            double lowerBound, double upperBound, boolean allow) {
        Scanner sc = new Scanner(System.in);
        if (lowerBound > upperBound) {
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.print(inputMsg);
        Double number;
        String input;
        while (true) {
            try {
                input = (sc.nextLine());
                if (allow && input.isBlank()) {
                    return Math.PI;
                }
                number = Double.parseDouble(input);
                if (number > upperBound || number < lowerBound) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.print(errorMsg);
            }
        }
    }

    //method ép nhập String không bỏ trống
    public static String getString(String inputMsg, String errorMsg, boolean allow) {
        Scanner sc = new Scanner(System.in);
        System.out.print(inputMsg);
        while (true) {
            try {
                String str = sc.nextLine();
                if (allow && str.isBlank()) {
                    return str;
                }
                if (str.isEmpty() || str.isBlank()) {
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.print(errorMsg);
            }

        }

    }

    //method ép nhập String không bỏ trống và theo format
    public static String getString(String inputMsg, String errorMsg, String regex, boolean allow) {
        Scanner sc = new Scanner(System.in);
        System.out.print(inputMsg);
        while (true) {
            try {
                String str = sc.nextLine();
                if (allow && str.isBlank()) {
                    return str;
                }
                if (str.isEmpty() || !str.matches(regex)) {
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.print(errorMsg);
            }

        }

    }

    public static Date inputDate(String inputMsg, String errorMsg) {
        Scanner sc = new Scanner(System.in);
        Date date;
        System.out.print(inputMsg + "(" + DATE_FORMAT + "): ");
        do {
            try {
                SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                date = df.parse(sc.nextLine());
                if (!validDate(date)) {
                    throw new Exception();
                }
                return date;
            } catch (ParseException e) {
                System.out.print(errorMsg);
            } catch (Exception e) {
                System.out.println("Input date is not valid!");
                System.out.print("Input in right format:(before " + dateToStr(getToday()) + "): ");
            }
        } while (true);
    }

    public static Date getToday() {
        return new Date();
    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(DATE_FORMAT);
        try {
            return dF.parse(dateStr);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean validDate(Date date) {
        if (date == null) {
            return false;
        }
        return !(!validYear(date) || date.after(getToday()));
    }

    public static boolean validYear(Date date) {
        return (CURRENT_YEAR - getYearFromDate(date)) < 120 && getYearFromDate(date) <= CURRENT_YEAR;
    }

    public static int getYearFromDate(Date date) {
        return Integer.parseInt(dateToStr(date).substring(6));
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat dF = new SimpleDateFormat(DATE_FORMAT);
        return dF.format(date);
    }

    public static boolean inputBoolean(String msgInput, String msgError) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msgInput);
        while (true) {
            try {
                boolean a = Boolean.parseBoolean(sc.nextLine());
                return a;
            } catch (Exception e) {
                System.out.print(msgError);
            }
        }
    }
}
