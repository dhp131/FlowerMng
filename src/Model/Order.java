package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    String id;
    String date;
    String customerName;
    int count;
    double total;
    List<OrderDetail> oList;

    /**
     * Constructs a new Order object with the given id, date, and customer name.
     *
     * @param id The unique identifier for this order.
     * @param date The date this order was placed.
     * @param customerName The name of the customer who placed this order.
     */
    public Order(String id, String date, String customerName) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
        count = 0;
        total = 0;
        this.oList = new ArrayList<OrderDetail>();
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return
     */
    public double getTotal() {
        return total;
    }

    /**
     *
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     *
     * @return
     */
    public List<OrderDetail> getoList() {
        return oList;
    }

    /**
     *
     * @param oList
     */
    public void setoList(List<OrderDetail> oList) {
        this.oList = oList;
    }

    /**
     * Adds an OrderDetail object to this Order and updates the count and total
     * accordingly.
     *
     * @param od The OrderDetail object to be added to this Order.
     */
    public void addDetail(OrderDetail od) {
        oList.add(od);
        count += od.getQuantity();
        total += od.getFlowerCost();
    }

    /**
     * Returns a string representation of the OrderDetail objects in this Order
     * in a comma-separated format.
     *
     * @return A string representation of the OrderDetail objects in this Order.
     */
    private String toStringDetail() {
        String str = "";
        for (OrderDetail od : oList) {
            str += String.format("%s,", od.toString());
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * Prints the information of this Order object to the console in a formatted
     * manner.
     */
    public void showInfo() {
        String str = String.format("| %-9s | %12s | %-20s | %12d | %11.3f |", this.id, this.date, this.customerName, this.count, this.total);
        System.out.println(str);
    }

    /**
     * Returns a string representation of this Order object in a comma-separated
     * format.
     *
     * @return A string representation of this Order object.
     */
    @Override
    public String toString() {
        String str = String.format("%s,%s,%s,%s", id, date,
                customerName, toStringDetail());
        return str;
    }

}
