package Control;

import Business.FlowerStore;

public class Program {

    public static void main(String[] args) {

        Menu menu = new Menu("\n-------------------FLOWER STORE MANAGEMENT------------------\n");
        menu.addOption("Add a flower");
        menu.addOption("Find a flower");
        menu.addOption("Update a flower");
        menu.addOption("Delete a flower");
        menu.addOption("Add an order");
        menu.addOption("Display orders");
        menu.addOption("Sort orders");
        menu.addOption("Save data");
        menu.addOption("Load data");
        menu.addOption("Quit");

        boolean check = true;
        FlowerStore FO = new FlowerStore();
        do {
            menu.printMenu();
            int choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    while (true) {
                        FO.addFlower();
                        if (!Menu.getYesOrNo("Do you want to countinue to add more flower?")) {
                            break;
                        }
                    }
                    check = false;
                    break;
                case 2:
                    FO.findFlower();
                    break;
                case 3:
                    FO.updateFlower();
                    check = false;
                    break;
                case 4:
                    FO.deleteFlower();
                    check = false;
                    break;
                case 5:
                    while (true) {
                        FO.addOrder();
                        if (!Menu.getYesOrNo("Do you want to countinue to add more orders?")) {
                            break;
                        }
                    }
                    check = false;
                    break;
                case 6:
                    FO.displayOrder();
                    break;
                case 7:
                    FO.sortOrder();
                    break;
                case 8:
                    FO.saveData();
                    check = true;
                    break;
                case 9:
                    FO.loadData();
                    break;
                case 10:
                    FO.quit();
                    break;
            }
        } while (true);
    }
}
