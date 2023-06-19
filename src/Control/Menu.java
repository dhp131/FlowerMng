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

    /**
     * Constructs a new Menu object with the given title.
     *
     * @param title The title of this menu.
     */
    public Menu(String title) {
        this.title = title;
    }

    /**
     * Returns the title of this Menu object.
     *
     * @return The title of this Menu object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Adds a new option to this Menu object.
     *
     * @param newOption The new option to be added to this Menu object.
     */
    public void addOption(String newOption) {
        options.add(newOption);
    }

    /**
     * Prints this Menu object to the console in a formatted manner.
     */
    public void printMenu() {
        System.out.println(title);
        for (String s : options) {
            System.out.println(options.indexOf(s) + 1 + ". " + s);
        }
    }

    //method: gotChoice: lấy lựa chọn của người dùng
    //không cho người dùng lấy lựa chọn ngoài optionList
    /**
     * Prompts the user to enter an option and returns the entered option.
     *
     * @return The option entered by the user.
     */
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

    /**
     * Prompts the user to enter a yes or no response and returns the entered
     * response as a boolean value.
     *
     * @param title The title of the menu to be displayed to the user.
     * @return True if the user entered "Yes", false otherwise.
     */
    public static boolean getYesOrNo(String title) {
        Menu sub = new Menu(title);
        sub.addOption("Yes");
        sub.addOption("No");
        sub.printMenu();
        return sub.getUserChoice() == 1;
    }

    /**
     * Returns a string representation of this Menu object.
     *
     * @return A string representation of this Menu object.
     */
    @Override
    public String toString() {
        String msg = String.format("[Menu] : %s", title);
        return msg;
    }

}
