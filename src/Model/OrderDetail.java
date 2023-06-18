package Model;

import java.io.Serializable;

public class OrderDetail implements Serializable, Comparable<OrderDetail> {

    String id;
    Flower flowerId;
    int quantity;
    double flowerCost;

    /**
     *
     * @param id
     * @param flowerId
     * @param quantity
     * @param flowerCost
     */
    public OrderDetail(String id, Flower flowerId, int quantity, double flowerCost) {
        this.id = id;
        this.flowerId = flowerId;
        this.quantity = quantity;
        this.flowerCost = flowerCost;
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
    public Flower getFlowerId() {
        return flowerId;
    }

    /**
     *
     * @param flowerId
     */
    public void setFlowerId(Flower flowerId) {
        this.flowerId = flowerId;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public double getFlowerCost() {
        return flowerId.getUnitPrice() * quantity;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String str = String.format("%s,%s,%d",
                id, flowerId.getId(), quantity);
        return str;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(OrderDetail o) {
        return this.id.compareTo(o.getId());
    }

}
