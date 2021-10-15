package za.ac.cput.factory;

import za.ac.cput.entity.rentalcart.Cart;

public class CartFactory {
    public static Cart createCart(String customerId, String equipmentId, double price, String processStatus,
                                  int quantity, double subTotal) {
        Cart cart = new Cart.Builder()
                .setCustomerId(customerId)
                .setEquipmentId(equipmentId)
                .setPrice(price)
                .setProcessStatus(processStatus)
                .setQuantity(quantity)
                .setSubtotal(subTotal)
                .build();

        return cart;
    }
}
