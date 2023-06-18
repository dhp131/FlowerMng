package Control;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public Menu() {
        super();
    }

//menu sẽ lưu mảng các option để mình chọn
    private String title;
    //private static String optionList[] = new String[100]; --> có thể
    //private static int size = 0;

    private final ArrayList<String> options = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    //constructor nhận vào title 
    public Menu(String title) {
        this.title = title;
    }

    //getter
    public String getTitle() {
        return title;
    }

    //method: addOption: lưu những thực đơn trong menu
    public void addOption(String newOption) {
        options.add(newOption);
    }

    //method: in ra danh sách các option
    public void printMenu() {
        System.out.println(title);
        for (String s : options) {
            System.out.println(options.indexOf(s) + 1 + ". " + s);
        }
    }

    //method: gotChoice: lấy lựa chọn của người dùng
    //không cho người dùng lấy lựa chọn ngoài optionList
    public int getUserChoice() {
        while (true) {
            try {
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice < 1 || choice > options.size()) {
                    throw new Exception();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Option must be an integer number!");
            } catch (Exception e) {
                System.out.println("Option must be in [1," + options.size() + "]");
            }
        }
    }

    public static boolean getYesOrNo(String title) {
        Menu sub = new Menu(title);
        sub.addOption("Yes");
        sub.addOption("No");
        sub.printMenu();
        return sub.getUserChoice() == 1;
    }

    @Override
    public String toString() {
        String msg = String.format("[Menu] : %s", title);
        return msg;
    }

}
