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

    public OrderManagement() {
        this.orders = new ArrayList<>();
    }

    /**
     * Add order
     *
     * @param fM
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
     * Add orderDetail
     *
     * @param order
     * @param fM
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

    /**
     * Hthi ds order ra screen
     *
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
                    index++;
                    String str = String.format("|%6d", index);
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
     * Sort by field and by order
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
     * Sort by field
     *
     * @return
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
     * @param field
     * @return
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
     * Sort by order
     *
     * @return
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
     * Display Sort
     *
     * @param sortedOrders
     * @param field
     * @param order
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

}
