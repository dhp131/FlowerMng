package Business;

import Control.Menu;
import Model.Flower;
import MyUses.Uses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Quản lí flower
 *
 * @author Hp
 */
public class FlowerManagement implements Serializable {

    private final Set<Flower> flowers;

    public FlowerManagement() {
        this.flowers = new LinkedHashSet<>();
    }

    /**
     * Add a flower
     */
    public void addFlower() {
        String id;
        String name;
        String importDate;
        Double unitPrice;
        String category;

        boolean check = true;
        do {
            //Check mã id được yêu cầu nhập vô
            id = Uses.getStringreg("Enter the flower's ID (FXXXX): ", "^F\\d{4}$", "ID is not null", "ID is not format (F0000)");
            if (getFlowerById(id) != null) {
                System.out.println("ID already exists. Please enter a unique ID.");
            } else {
                check = false; // nếu chưa có mã code thì sẽ thoát khỏi vòng lặp và được add thêm vào chương trình
            }
        } while (check);

        name = Uses.getStringlength("Enter flower's name: ", "Name must be between 3 and 50 characters.", 3, 50);
        importDate = Uses.getDate("Enter import date (dd/MM/yyyy): ", "dd/MM/yyyy");
        unitPrice = Uses.getDouble("Enter price: ");
        category = Uses.getString("Enter category: ");

        //tạo đối tượng flower
        Flower flower = new Flower(id, name, importDate, unitPrice, category);
        flowers.add(flower);
        System.out.println("Flower added successfully.");
    }

    /**
     * Find a flower by id || by name
     */
    public void findFlower() {
        Menu fFlower = new Menu("Find Flower (by ID | by Name):");
        fFlower.addOption("ID");
        fFlower.addOption("Name");
        fFlower.printMenu();
        int choice = fFlower.getUserChoice();

        String id = "";
        String name = "";
        if (choice == 1) {
            id = Uses.getStringreg("Enter the flower's ID (FXXXX): ", "^F\\d{4}$", "ID is not null", "ID is not format (F0000)");
            Flower flower = getFlowerById(id);
            if (flower != null) {
                displayFlower(Arrays.asList(flower));
            } else {
                System.out.println("The flower does not exist.");
            }
        } else {
            name = Uses.getStringlength("Enter flower's name: ", "Name must be between 3 and 50 characters.", 3, 50);
            List<Flower> flowers = getFlowerByName(name);
            if (!flowers.isEmpty()) {
                displayFlower(flowers);
            } else {
                System.out.println("The flower does not exist.");
            }
        }
    }

    /**
     * Display ds flower
     * @param flowers 
     */
    public void displayFlower(List<Flower> flowers) {
        System.out.println("------------------");
        System.out.println("LIST OF FLOWER");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| No.| Flower Id |  Flower Name  |  Import Date |  Unit Price  | Category |");
        System.out.println("---------------------------------------------------------------------------");

        int index = 1; //đánh dấu vị trí
        for (Flower flower : flowers) {
            String str = String.format("|%4d", index);
            System.out.print(str);
            flower.showInfo();
            System.out.println("---------------------------------------------------------------------------");
            index++;

        }
    }

    /**
     * Update a flower by name
     */
    public void updateFlower() {
        String upName = Uses.getStringlength("Enter flower's name: ", "Name must be between 3 and 50 characters.", 3, 50);
        List<Flower> flowers = getFlowerByName(upName);
        if (!flowers.isEmpty()) {
            Flower flower;
            if (flowers.size() == 1) {
                flower = flowers.get(0);
            } else {
                System.out.println("Multiple flowers found with the same name. Please choose one to update:");
                for (int i = 0; i < flowers.size(); i++) {
                    System.out.println((i + 1) + ". " + flowers.get(i));
                }
                int choice = Uses.getInt("Enter your choice: ", 1);
                flower = flowers.get(choice - 1);
            }
            System.out.println("Update the flower (enter blank to keep the old information)");

            // Enter new information to update - if no update is needed, press enter
            String id = Uses.getString("Enter new ID: ");
            String importDate = Uses.getString("Enter new import date: ");
            String price = Uses.getString("Enter new price: ");
            String category = Uses.getString("Enter new category: ");

            try {
                if (!id.isEmpty() && !id.matches("^F\\d{4}$")) {
                    throw new Exception("ID must follow format (FXXXX)");
                }
                if (!importDate.isEmpty() && !Uses.isValidDateFormat(importDate, "dd/MM/yyyy")) {
                    throw new Exception("Invalid date");
                }
                if (!price.isEmpty() && Double.parseDouble(price) < 0) {
                    throw new Exception("Price must be a positive real number");
                }
            } catch (Exception e) {
                System.out.println("Update failed!");
                return;
            }
            if (!id.isEmpty()) {
                flower.setId(id);
            }
            if (!importDate.isEmpty()) {
                flower.setImportDate(importDate);
            }
            if (!category.isEmpty()) {
                flower.setCategory(category);
            }
            if (!price.isEmpty()) {
                flower.setUnitPrice(Double.valueOf(price));
            }
            System.out.println("Flower updated successfully!!");
        } else {
            System.out.println("The flower does not exist!");
        }
    }

    /**
     * Delete a flower by id
     */
    public void deleteFlower() {
        String id = Uses.getStringreg("Enter the flower's ID (FXXXX): ", "^F\\d{4}$", "ID is not null", "ID is not format (F0000)");
        Flower flower = getFlowerById(id);
        if (flower != null) {
            if (Menu.getYesOrNo("Are you sure want to delete this flower?")) {
                flowers.remove(flower);
                System.out.println("Flower deleted successfully!");
            } else {
                System.out.println("The flower not deleted.");
            }
        } else {
            System.out.println("The flower does not exist!");
        }
    }

    /**
     * Returns the flower with the given name or null if it does not exist.
     *
     * @param name
     * @return
     */
    private List<Flower> getFlowerByName(String name) {
        List<Flower> result = new ArrayList<>();
        for (Flower flower : flowers) {
            if (flower.getName().contains(name)) {
                result.add(flower);
            }
        }
        return result;
    }

    /**
     * Returns the flower with the given id or null if it does not exist.
     *
     * @param id
     * @return
     */
    Flower getFlowerById(String id) {
        for (Flower flower : flowers) {
            if (flower.getId().equals(id)) {
                return flower;
            }
        }
        return null;
    }

    /**
     * checks if the flowers set is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return flowers.isEmpty();
    }

}
