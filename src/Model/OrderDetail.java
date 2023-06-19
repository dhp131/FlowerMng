package Model;

import java.io.Serializable;

public class OrderDetail implements Serializable, Comparable<OrderDetail> {

    String id;
    Flower flowerId;
    int quantity;
    double flowerCost;

    /**
     * Constructs a new OrderDetail object with the given id, flower id,
     * quantity, and flower cost.
     *
     * @param id The unique identifier for this order detail.
     * @param flowerId The Flower object associated with this order detail.
     * @param quantity The quantity of the flower in this order detail.
     * @param flowerCost The cost of the flower in this order detail.
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
     * Calculates and returns the cost of the flower in this OrderDetail object.
     *
     * @return The cost of the flower in this OrderDetail object.
     */
    public double getFlowerCost() {
        return flowerId.getUnitPrice() * quantity;
    }

    /**
     * Returns a string representation of this OrderDetail object in a
     * comma-separated format.
     *
     * @return A string representation of this OrderDetail object.
     */
    @Override
    public String toString() {
        String str = String.format("%s,%s,%d",
                id, flowerId.getId(), quantity);
        return str;
    }

    /**
     * Compares this OrderDetail object with the specified OrderDetail object
     * for order.
     *
     * @param o The OrderDetail object to be compared.
     * @return A negative integer, zero, or a positive integer as this
     * OrderDetail object is less than, equal to, or greater than the specified
     * OrderDetail object.
     */
    @Override
    public int compareTo(OrderDetail o) {
        return this.id.compareTo(o.getId());
    }

}
