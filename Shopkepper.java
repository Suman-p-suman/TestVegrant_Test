import java.util.ArrayList;

import java.util.List;

class Product {
    String name;
    double unitPrice;
    double gstPercentage;
    int quantity;

    public Product(String name, double unitPrice, double gstPercentage, int quantity) {
        this.name=name;
        this.unitPrice=unitPrice;
        this.gstPercentage=gstPercentage;
        this.quantity=quantity;
    }

    public double calculateGSTAmount() {
        return (unitPrice*gstPercentage/100)*quantity;
    }

    public double calculateDiscountedPrice() {
        double discountedPrice=unitPrice*quantity;
        if (unitPrice>=500) {
            discountedPrice*=0.95;
        }
        return discountedPrice;
    }
}

public class Shopkepper {
    public static void main(String[] args) {
        List<Product> basket=new ArrayList<>();
        basket.add(new Product("Leather Wallet", 1100, 18, 1));
        basket.add(new Product("Umbrella", 900, 12, 4));
        basket.add(new Product("Cigarette", 200, 28, 3));
        basket.add(new Product("Honey", 100, 0, 2));
        Product maxGSTProduct = findProductWithMaxGST(basket);
        System.out.println("Product with maximum GST amount: " + maxGSTProduct.name);
        double totalAmountToPay = calculateTotalAmountToPay(basket);
        System.out.println("Total amount to be paid to the shopkeeper: Rs" + totalAmountToPay);
    }

    private static Product findProductWithMaxGST(List<Product> basket) {
        double maxGSTAmount=0;
        Product maxGSTProduct=null;

        for (Product product : basket) {
            double gstAmount=product.calculateGSTAmount();
            if (gstAmount>maxGSTAmount) {
                maxGSTAmount=gstAmount;
                maxGSTProduct=product;
            }
        }

        return maxGSTProduct;
    }

    private static double calculateTotalAmountToPay(List<Product> basket) {
        double totalAmount=0;
        for (Product product : basket) {
            totalAmount+=product.calculateDiscountedPrice()+product.calculateGSTAmount();
        }
        return totalAmount;
    }
}
