package ibf2024.assessment.paf.batch4.models;

import java.util.Date;
import java.util.List;

public class BeerOrder {
    
    private String orderId;
    private Date date;
    private int breweryId;
    private List<BeerOrderDetail> orders;

    public BeerOrder() {
    }

    public BeerOrder(String orderId, Date date, int breweryId, List<BeerOrderDetail> orders) {
        this.orderId = orderId;
        this.date = date;
        this.breweryId = breweryId;
        this.orders = orders;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public List<BeerOrderDetail> getOrders() {
        return orders;
    }

    public void setOrders(List<BeerOrderDetail> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "BeerOrder [orderId=" + orderId + ", date=" + date + ", breweryId=" + breweryId + ", orders=" + orders
                + "]";
    }
}
