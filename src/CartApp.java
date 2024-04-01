import domain.Product;
import domain.ProductRepository;
import service.CartService;

public class CartApp {
    static ProductRepository productRepository = ProductRepository.getInstance();

    public static void main(String[] args) {
        testCodeForProduct();
        testCodeForCart();
    }

    private static void testCodeForProduct() {
        productRepository.saveProduct(new Product("귤", 1000));
        productRepository.saveProduct(new Product("자두", 2000));
        productRepository.saveProduct(new Product("토마토", 3000));
        productRepository.saveProduct(new Product("토마토", 5000));

        productRepository.showProductList();
    }

    private static void testCodeForCart() {
        CartService myCart = new CartService();

        // 장바구니 비었을 때
        myCart.showCartList();
        myCart.showTotalPrice();
        myCart.removeProduct(removeItemDataForTest());

        // 상품추가
        myCart.addProduct(itemDataForTest());
        myCart.showCartList();

        // 상품삭제
        myCart.removeProduct(removeItemDataForTest());
        myCart.showCartList();

        // 결제금액 확인
        myCart.showTotalPrice();
    }

    private static Object[][] itemDataForTest() {
        return new Object[][] {
                new Object[]{"귤", 2},
                new Object[]{"복숭아", 1},
                new Object[]{"자두", 3},
                new Object[]{"귤", 1}
        };
    }

    private static Object[][] removeItemDataForTest() {
        return new Object[][] {
                new Object[]{"귤", 1},
                new Object[]{"자두", 4},
                new Object[]{"자두", 2}
        };
    }
}
