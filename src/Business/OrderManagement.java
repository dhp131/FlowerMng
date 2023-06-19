package Business;

import Control.Menu;
import Model.Flower;
import Model.Order;
import Model.OrderDetail;
import MyUses.Uses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Quản lí order
 *
 * @author Hp
 */
public class OrderManagement implements Serializable {

    private final List<Order> orders;

    /**
     * Constructs a new OrderManagement object with an empty list of orders.
     */
    public OrderManagement() {
        this.orders = new ArrayList<>();
    }

    /**
     * Prompts the user to enter information for a new order and adds it to this
     * OrderManagement object.
     *
     * @param fM The FlowerManagement object used to manage flowers.
     */
    public void addOrder(FlowerManagement fM) {
        String orderId;
        String orderDate;
        String customerName;

        boolean check = true;
        do {
            //Check mã id được yêu cầu nhập vô
            orderId = Uses.getStringreg("Enter the Order ID (XXXX): ", "^\\d{4}$", "ID is not null", "ID is not format (XXXX)");
            if (getOrderById(orderId) != null) {
                System.out.println("ID already exists. Please enter a unique ID.");
            } else {
                check = false; // nếu chưa có mã code thì sẽ thoát khỏi vòng lặp và được add thêm vào chương trình
            }
        } while (check);

        orderDate = Uses.getDate("Enter import date (dd/MM/yyyy): ", "dd/MM/yyyy");
        customerName = Uses.getStringNonBlank("Enter the customer name: ", "Name is not blank!");
        Order order = new Order(orderId, orderDate, customerName);
        addOrderDetails(order, fM);
        orders.add(order);
        System.out.println("Order added successfully.");
    }

    /**
     * Adds details to an order.
     *
     * @param order The order to add details to.
     * @param fM The FlowerManagement object used to retrieve flower
     * information.
     */
    public void addOrderDetails(Order order, FlowerManagement fM) {
        String orderDetailID;
        int quantity;
        double flowerCost;

        System.out.println("------------------------------------------------------");
        System.out.println("Order details: ");

        int detailCount = 0;
        boolean addMoreDetails = true;
        while (addMoreDetails) {
            String flowerId = Uses.getStringreg("Enter the flower's ID (FXXXX): ", "^F\\d{4}$", "ID is not null", "ID is not format (F0000)");
            if (!flowerId.isEmpty()) {
                Flower flower = fM.getFlowerById(flowerId);
                if (flower != null) {
                    quantity = Uses.getInt("Enter quantity: ", 0);
                    flowerCost = quantity * flower.getUnitPrice();
                    orderDetailID = order.getId() + "-" + detailCount++;
                    OrderDetail detail = new OrderDetail(orderDetailID, flower, quantity, flowerCost);
                    order.addDetail(detail);
                } else {
                    System.out.println("The flower does not exist.");
                }
            }
            addMoreDetails = Menu.getYesOrNo("Add another detail?");
        }
    }

    /**
     * Displays a list of orders within a specified date range. The method
     * prompts the user to enter a start and end date in the format dd/mm/yyyy.
     * It then prints out a table of orders that fall within the specified date
     * range, along with their details such as order ID, order date, customer's
     * name, flower count, and order total.
     */
    public void displayOrders() {
        String startDate;
        String endDate;
        if (!orders.isEmpty()) {
            int totalFlowerCount = 0;
            double totalOrderTotal = 0;
            startDate = Uses.getDate("Enter startDate [dd/mm/yyyy]: ", "dd/MM/yyyy");
            endDate = Uses.getDate("Enter endDate [dd/mm/yyyy]: ", "dd/MM/yyyy");
            System.out.println("------------------");
            System.out.println("LIST ORDERS FROM " + startDate + " TO " + endDate);
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("|  No. | Order ID  |  Order Date  |    Customer's Name   | Flower Count | Order Total |");
            System.out.println("---------------------------------------------------------------------------------------");

            int index = 1; //đánh dấu vị trí
            for (Order order : orders) {
                if (Uses.toDate(order.getDate(), "dd/MM/yyyy").compareTo(Uses.toDate(startDate, "dd/MM/yyyy")) >= 0
                        && Uses.toDate(order.getDate(), "dd/MM/yyyy").compareTo(Uses.toDate(endDate, "dd/MM/yyyy")) <= 0) {
                    int flowerCount = order.getoList().stream()
                            .mapToInt(OrderDetail::getQuantity)
                            .sum();
                    double orderTotal = order.getoList().stream()
                            .mapToDouble(OrderDetail::getFlowerCost)
                            .sum();
                    totalFlowerCount += flowerCount;
                    totalOrderTotal += orderTotal;
                    String str = String.format("|%6d", index++);
                    System.out.print(str);
                    order.showInfo();
                    System.out.println("---------------------------------------------------------------------------------------");
                }
            }
            System.out.printf("|      | Total     |              |                      | %12d | %11.3f |\n", totalFlowerCount, totalOrderTotal);
        } else {
            System.out.println("Order's list empty!");
        }
    }

    /**
     * Sorts the list of orders based on a specified field and order. The method
     * prompts the user to enter a field to sort by and an order (ascending or
     * descending). It then sorts the list of orders based on the specified
     * field and order, and displays the sorted list of orders.
     */
    public void sort() {
        String field = getField();
        String order = getOrder();
        Comparator<Order> comparator = getComparator(field);

        if (order.equalsIgnoreCase("DESC")) {
            comparator = comparator.reversed();
        }
        List<Order> sortedOrders = new ArrayList<>(orders);
        sortedOrders.sort(comparator);
        displaySort(sortedOrders, field, order);
    }

    /**
     * Prompts the user to select a field to sort by. The method displays a menu
     * of available fields to sort by, including Order ID, Order Date, Customer
     * Name, and Order Total. The user can select one of these options and the
     * method returns the selected field.
     *
     * @return The selected field to sort by.
     */
    private String getField() {
        Menu sField = new Menu("Sorted by (Order id || Order date || Customer name || Order total):");
        sField.addOption("Order id");
        sField.addOption("Order date");
        sField.addOption("Customer name");
        sField.addOption("Order total");
        sField.printMenu();
        int choice = sField.getUserChoice();
        switch (choice) {
            case 1:
                return "Order id";
            case 2:
                return "Order date";
            case 3:
                return "Customer name";
            case 4:
                return "Order total";
            default:
                System.out.println("Invalid field");
                return null;
        }
    }

    /**
     * Returns a Comparator object for sorting orders based on a specified
     * field.
     *
     * @param field The field to sort by.
     * @return A Comparator object for sorting orders based on the specified
     * field.
     */
    private Comparator<Order> getComparator(String field) {
        if (field == null) {
            return null;
        }
        switch (field) {
            case "Order id":
                return Comparator.comparing(Order::getId);
            case "Order date":
                return Comparator.comparing(Order::getDate);
            case "Customer name":
                return Comparator.comparing(Order::getCustomerName);
            case "Order total":
                return Comparator.comparingDouble(new ToDoubleFunctionImpl());
            default:
                System.out.println("Invalid field");
                return null;
        }
    }

    /**
     * Prompts the user to select a sort order (ascending or descending). The
     * method displays a menu of available sort orders (ASC or DESC), and the
     * user can select one of these options.
     *
     * @return The selected sort order.
     */
    private String getOrder() {
        Menu sOrder = new Menu("Sort order (ASC|DESC):");
        sOrder.addOption("ASC");
        sOrder.addOption("DESC");
        sOrder.printMenu();
        int choice = sOrder.getUserChoice();
        String order = choice == 1 ? "ASC" : "DESC";

        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
            System.out.println("Invalid sort order.");
            return null;
        }
        return order;
    }

    /**
     * Displays a sorted list of orders.
     *
     * @param sortedOrders The sorted list of orders to display.
     * @param field The field that the orders were sorted by.
     * @param order The sort order (ascending or descending).
     */
    private void displaySort(List<Order> sortedOrders, String field, String order) {
        System.out.println("------------------");
        System.out.println("LIST OF ORDERS");
        System.out.println("Sorted by: " + field);
        System.out.println("Sort order: " + order);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("|  No. | Order ID  |  Order Date  |    Customer's Name   | Flower Count | Order Total |");
        System.out.println("---------------------------------------------------------------------------------------");

        int totalFlowerCount = 0;
        double totalOrderTotal = 0;
        int index = 1;
        for (Order o : sortedOrders) {
            int flowerCount = o.getoList().stream()
                    .mapToInt(OrderDetail::getQuantity).sum();
            double orderTotal = o.getoList().stream()
                    .mapToDouble(OrderDetail::getFlowerCost).sum();
            totalFlowerCount += flowerCount;
            totalOrderTotal += orderTotal;
            String str = String.format("|%6d", index++);
            System.out.print(str);
            o.showInfo();
            System.out.println("---------------------------------------------------------------------------------------");
        }
        System.out.printf("|      | Total     |              |                      | %12d | %11.3f |\n", totalFlowerCount, totalOrderTotal);
    }

    /**
     * A ToDoubleFunction implementation for calculating the total cost of an
     * order. This class implements the applyAsDouble method to calculate the
     * sum of the flower costs for all OrderDetails in an Order object.
     */
    private static class ToDoubleFunctionImpl implements ToDoubleFunction<Order> {

        @Override
        public double applyAsDouble(Order order) {
            return order.getoList().stream()
                    .mapToDouble(OrderDetail::getFlowerCost)
                    .sum();
        }
    }

    /**
     * Returns the order with the given id or null if it does not exist.
     *
     * @param id
     * @return
     */
    private Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public boolean isFlowerInOrder(Flower flower) {
        for (Order o : orders) {
            List<OrderDetail> ods = o.getoList();
            for (OrderDetail od : ods) {
                if (od.getFlowerId().equals(flower.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

}
