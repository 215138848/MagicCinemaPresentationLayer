package za.ac.cput.entity.rentalcart;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "rental_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String customerId;
    @NotNull
    private String equipmentId;
    @NotNull
    private double price;
    @NotNull
    private int quantity;
    @NotNull
    private double subtotal;
    @NotNull
    private String processStatus;

    public Cart() {}

    private Cart(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.equipmentId = builder.equipmentId;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.subtotal = builder.subtotal;
        this.processStatus = builder.processStatus;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", processStatus='" + processStatus + '\'' +
                '}';
    }

    public static class Builder {
        private int id, quantity;
        private String customerId, equipmentId, processStatus;
        private double price, subtotal;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setEquipmentId(String equipmentId) {
            this.equipmentId = equipmentId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setProcessStatus(String processStatus) {
            this.processStatus = processStatus;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setSubtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }

        public Builder copy(Cart cart) {
            this.id = cart.id;
            this.customerId = cart.customerId;
            this.equipmentId = cart.equipmentId;
            this.price = cart.price;
            this.quantity = cart.quantity;
            this.subtotal = cart.subtotal;
            this.processStatus = cart.processStatus;
            return this;
        }
    }

    public int getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getProcessStatus() {
        return processStatus;
    }
}
