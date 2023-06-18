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
     *
     * @param id
     * @param date
     * @param customerName
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
     *
     * @param od
     */
    public void addDetail(OrderDetail od) {
        oList.add(od);
        count += od.getQuantity();
        total += od.getFlowerCost();
    }

    /**
     *
     * @return
     */
    private String toStringDetail() {
        String str = "";
        for (OrderDetail od : oList) {
            str += String.format("%s,", od.toString());
        }
        return str.substring(0, str.length() - 1);
    }

    public void showInfo() {
        String str = String.format("| %-9s | %12s | %-20s | %12d | %11.3f |", this.id, this.date, this.customerName, this.count, this.total);
        System.out.println(str);
    }

    /**
     * return String
     *
     * @return
     */
    @Override
    public String toString() {
        String str = String.format("%s,%s,%s,%s", id, date,
                customerName, toStringDetail());
        return str;
    }

}
