package MyUses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Uses {

    public static Scanner sc = new Scanner(System.in);

    //Nhập chuỗi bình thường - no condition
    public static String getString(String inputMsg) {
        System.out.print(inputMsg);
        return sc.nextLine().trim();
    }

//    //Nhập chuỗi vào không có khoảng trống - non blank cách 1
//    public static String getStringNonBlank(String welcome, String msg) {
//        String result = ""; 
//        //Scanner sc = new Scanner(System.in);
//        do {
//            System.out.print(welcome);
//            result = sc.nextLine().trim();
//        } while (result.length()==0);
//        return result;
//    }
    //Nhập String vào không có khoảng trống - non blank cách 2
    public static String getStringNonBlank(String welcome, String msg) {
        boolean check = true;
        String result = "";
        //Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringreg(String welcome, String pattern, String msg, String msgreg) {
        while (true) {
            try {
                System.out.print(welcome);
                String inputStr = sc.nextLine();
                if (!inputStr.matches(pattern)) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(msgreg);
            }
        }
    }

//    //Nhập chuỗi theo 1 định dạng yêu cầu đề cho
//    public static String getStringreg(String welcome, String pattern, String msg, String msgreg) {
//        boolean check = true;
//        String result = "";
//        //Scanner sc = new Scanner(System.in);
//        do {
//            System.out.print(welcome);
//            result = sc.nextLine();
//            if (result.isEmpty()) {
//                System.out.println(msg);
//            } else if (!result.matches(pattern)) {
//                System.out.println(msgreg);
//            } else {
//                check = false;
//            }
//        } while (check);
//        return result;
//    }
    //Nhập chuỗi bị giới hạn độ dài
    public static String getStringlength(String inputMsg, String errorMsg, int minLength, int maxLength) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                int strLen = inputStr.length();
                if (strLen < minLength || strLen > maxLength) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static boolean getBoolean(String inputMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                boolean inputBool = Boolean.parseBoolean(sc.nextLine());
                return inputBool;
            } catch (Exception e) {
                System.out.println("True or False!!");
            }
        }
    }

    //Get an int between min max
    public static int getInt(String welcome, int max, int min) {
        boolean check = true;
        int number = 0;
        //Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must bigger than " + min);
                } else if (number > max) {
                    System.out.println("Number must smaller than " + max);
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!");
            }
        } while (check || number < min);

        return number;
    }

    //Nhập số bị giới hạn min
    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        //Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must bigger than " + min);
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!");
            }
        } while (check || number < min);

        return number;
    }

    public static float getFloat(String welcome, int max, int min) {
        boolean check = true;
        float number = 0f;
        //Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must bigger than " + min);
                } else if (number > max) {
                    System.out.println("Number must smaller than " + max);
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!");
            }
        } while (check || number < min);

        return number;
    }

    public static float getFloat(String welcome, float max, float min) {
        boolean check = true;
        float number = 0f;
        //Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must bigger than " + min);
                } else if (number > max) {
                    System.out.println("Number must smaller than " + max);
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!");
            }
        } while (check || number < min);

        return number;
    }

    //
    public static float getFloat(String welcome, int min) {
        boolean check = true;
        float number = 0f;
        //Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must bigger than " + min);
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!");
            }
        } while (check || number < min);

        return number;
    }

    public static double getDouble(String welcome) {
        boolean check = true;
        double number = 0;
        do {
            try {
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                check = false; // add this line
            } catch (NumberFormatException e) {
                System.out.println("Must be greater than 0");
            }
        } while (check);

        return number;
    }

    //ép kiểu nhập Date hợp lệ
    public static String getDate(String inputMsg, String dateFormat) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (checkValidDate(inputStr, dateFormat)) {
                    return inputStr;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid date: " + dateFormat);
            }
        }
    }

    private static boolean checkValidDate(String date, String dateFormat) {
        try {
            if (!isValidDateFormat(date, dateFormat)) {
                throw new IllegalArgumentException("Invalid date format");
            }
            int day, month, year;
            String[] dateParts = date.split("[- /.]");
            if (dateFormat.equals("dd/MM/yyyy")) {
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
            } else {
                day = Integer.parseInt(dateParts[1]);
                month = Integer.parseInt(dateParts[0]);
            }
            year = Integer.parseInt(dateParts[2]);

            //check năm nhuận
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    throw new IllegalArgumentException("Invalid date value");
                } else {
                    return true;
                }
            } else if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (day > 29) {
                        throw new IllegalArgumentException("Invalid date value");
                    } else {
                        return true;
                    }
                } else {
                    if (day > 28) {
                        throw new IllegalArgumentException("Invalid date value");
                    } else {
                        return true;
                    }
                }
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static final String DDMMYYYY_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    private static final String MMDDYYYY_REGEX = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";

    public static boolean isValidDateFormat(String date, String format) {
        String regex = (format.equals("dd/MM/yyyy")) ? DDMMYYYY_REGEX : MMDDYYYY_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static String toDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date toDate(String date, String format) {
        Date ret = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            ret = sdf.parse(date);
        } catch (ParseException e) {
        }
        return ret;
    }

}
