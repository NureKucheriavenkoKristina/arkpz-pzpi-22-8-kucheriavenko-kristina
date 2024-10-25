public class Order {
    private double price;
    private boolean paid;
    private boolean shipped;
    private double discountRate;

    public Order(double price, double discountRate) {
        this.price = price;
        this.discountRate = discountRate;
        this.paid = false;
        this.shipped = false;
    }

    // Метод «Add Parameter»: тепер знижка передається параметром
    public double calculateTotal(double price, double discountRate) {
        return price - (price * discountRate);
    }

    // Метод «Decompose Conditional»: розділяє перевірки стану замовлення
    public void printOrderStatus() {
        if (isOrderReady()) {
            System.out.println("Order is paid and shipped.");
        } else if (isOrderPaid()) {
            System.out.println("Order is paid but not shipped.");
        } else {
            System.out.println("Order is not ready.");
        }
    }

    // Метод «Hide Method»: цей метод прихований і використовується лише для внутрішніх перевірок
    private boolean isOrderReady() {
        return isOrderPaid() && isOrderShipped();
    }

    // Метод «Hide Method» - прихований метод для перевірки, чи оплачене замовлення
    private boolean isOrderPaid() {
        return paid;
    }

    // Метод «Hide Method» - прихований метод для перевірки, чи відправлене замовлення
    private boolean isOrderShipped() {
        return shipped;
    }

    // Встановлює статус оплати
    public void payOrder() {
        if (!paid) {
            this.paid = true;
            System.out.println("Order has been paid.");
        } else {
            System.out.println("Order is already paid.");
        }
    }

    // Встановлює статус відправлення
    public void shipOrder() {
        if (paid && !shipped) {
            this.shipped = true;
            System.out.println("Order has been shipped.");
        } else if (!paid) {
            System.out.println("Order cannot be shipped until it is paid.");
        } else {
            System.out.println("Order is already shipped.");
        }
    }

    public static void main(String[] args) {
        // Створення замовлення з вказаною ціною та ставкою знижки
        double initialPrice = 200.0;
        double initialDiscountRate = 0.15; // знижка 15%
        Order order = new Order(initialPrice, initialDiscountRate);

        // Розрахунок та виведення загальної вартості з урахуванням знижки
        double total = order.calculateTotal(order.price, order.discountRate);
        System.out.println("Total price after discount: " + total);

        // Виведення початкового статусу замовлення
        order.printOrderStatus();

        // Оплата замовлення
        order.payOrder();

        // Повторне виведення статусу замовлення після оплати
        order.printOrderStatus();

        // Відправлення замовлення
        order.shipOrder();

        // Виведення фінального статусу замовлення
        order.printOrderStatus();
    }
}
