package za.ac.cput.entity.rent;

import javax.persistence.*;

@Entity
@Table(name = "rent_equipment_status")
public class RentItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String client;
    private String equipment;
    private double cost;
    private String startDate;
    private String estimateEndDate;
    private String endDate;
    private int daysOverdue;
    private int quantity;
    private double finalCost;

    public RentItems() {}

    private RentItems(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.equipment = builder.equipment;
        this.cost = builder.cost;
        this.startDate = builder.startDate;
        this.estimateEndDate = builder.estimateEndDate;
        this.endDate = builder.endDate;
        this.daysOverdue = builder.daysOverdue;
        this.quantity = builder.quantity;
        this.finalCost = builder.finalCost;
    }

    @Override
    public String toString() {
        return "RentItems{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", equipment='" + equipment + '\'' +
                ", cost=" + cost +
                ", startDate='" + startDate + '\'' +
                ", estimateEndDate='" + estimateEndDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", daysOverdue=" + daysOverdue +
                ", quantity=" + quantity +
                ", finalCost=" + finalCost +
                '}';
    }

    public static class Builder {
        private int id, daysOverdue, quantity;
        private String client, equipment, startDate, estimateEndDate, endDate;
        private double cost, finalCost;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setClient(String client) {
            this.client = client;
            return this;
        }

        public Builder setEquipment(String equipment) {
            this.equipment = equipment;
            return this;
        }

        public Builder setCost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEstimateEndDate(String estimateEndDate) {
            this.estimateEndDate = estimateEndDate;
            return this;
        }

        public Builder setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setDaysOverdue(int daysOverdue) {
            this.daysOverdue = daysOverdue;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setFinalCost(double finalCost) {
            this.finalCost = finalCost;
            return this;
        }

        public RentItems build() {
            return new RentItems(this);
        }

        public Builder copy(RentItems rentItems) {
            this.id = rentItems.id;
            this.client = rentItems.client;
            this.equipment = rentItems.equipment;
            this.cost = rentItems.cost;
            this.startDate = rentItems.startDate;
            this.estimateEndDate = rentItems.estimateEndDate;
            this.endDate = rentItems.endDate;
            this.daysOverdue = rentItems.daysOverdue;
            this.quantity = rentItems.quantity;
            this.finalCost = rentItems.finalCost;
            return this;
        }
    }

    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getEquipment() {
        return equipment;
    }

    public double getCost() {
        return cost;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEstimateEndDate() {
        return estimateEndDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getFinalCost() {
        return finalCost;
    }
}
