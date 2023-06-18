package Business;

import Control.Menu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Quản lí cửa hàng = Flower + Order
 *
 * @author Hp
 */
public class FlowerStore {

    FlowerManagement fList = new FlowerManagement();
    OrderManagement oList = new OrderManagement();
    
    Path flowerDataPath = Paths.get("src/Files/flower.dat").toAbsolutePath();
    Path orderDataPath = Paths.get("src/Files/order.dat").toAbsolutePath();

    public void addFlower() {
        fList.addFlower();
    }

    public void findFlower() {
        fList.findFlower();
    }

    public void updateFlower() {
        fList.updateFlower();
    }

    public void deleteFlower() {
        fList.deleteFlower();
    }

    public void addOrder() {
        // If there are no flowers in the list, an order cannot be created
        if (fList.isEmpty()) {
            System.out.println("Cannot add order because there are no flowers in the list.");
        } else {
            oList.addOrder(fList);
        }
    }

    public void displayOrder() {
        oList.displayOrders();
    }

    public void sortOrder() {
        oList.sort();
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(flowerDataPath.toString()))) {
            oos.writeObject(fList);
            System.out.println("Flowers data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving flowers data: " + e.getMessage());
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(orderDataPath.toString()))) {
            oos.writeObject(oList);
            System.out.println("Orders data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving orders data: " + e.getMessage());
        }
    }

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(flowerDataPath.toString()))) {
            fList = (FlowerManagement) ois.readObject();
            System.out.println("Flowers data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading flowers data: " + e.getMessage());
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(orderDataPath.toString()))) {
            oList = (OrderManagement) ois.readObject();
            System.out.println("Orders data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading orders data: " + e.getMessage());
        }
    }

    public void quit() {
        if (Menu.getYesOrNo("Do you want to save before quit program?")) {
            saveData();
        }
        System.out.println("Quit program.");
        System.exit(0);
    }
}
