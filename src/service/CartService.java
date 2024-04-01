package service;

import domain.Product;
import domain.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class CartService {
    ProductRepository productRepository = ProductRepository.getInstance();
    private static final Map<String, Integer> cartList = new HashMap<>();
    private final String EMPTY_CART = "장바구니가 비었습니다. 상품을 추가해주세요.\n";

    public CartService() {

    }

    public void showCartList() {
        System.out.println("[장바구니]");

        if(cartList.isEmpty()) {
            System.out.println(EMPTY_CART);
            return;
        }

        for(String name : cartList.keySet()) {
            System.out.println(name + " " + cartList.get(name));
        }
        System.out.println();
    }

    public void addProduct(Object[][] items) {
        System.out.println("[상품추가]");

        for(Object[] item : items) {
            String name = (String) item[0];
            int count = (int) item[1];

            if(!productRepository.getProducts().contains(new Product(name))) {
                System.out.println("[" + name + "] 상품은 존재하지 않습니다.");
                continue;
            }

            cartList.merge(name, count, Integer::sum);
            System.out.println("[" + name + "] 상품을 " + count + "개 담았습니다.");
        }
        System.out.println();
    }

    public void removeProduct(Object[][] removeItems) {
        System.out.println("[상품삭제]");

        if(cartList.isEmpty()) {
            System.out.println(EMPTY_CART);
            return;
        }

        for(Object[] item : removeItems) {
            String name = (String) item[0];
            int count = (int) item[1];

            if(!cartList.containsKey(name)) {
                System.out.println("[" + name + "] 상품이 장바구니에 존재하지 않습니다.");
                continue;
            }

            cartList.merge(name, count, ((i1, i2) -> Math.max(0, i1 - i2)));

            if(cartList.get(name) == 0) {
                cartList.remove(name);
                System.out.println("[" + name + "] 상품이 모두 삭제되었습니다.");
                continue;
            }

            System.out.println("[" + name + "] 상품 " + count + "개가 삭제되었습니다.");
        }
        System.out.println();
    }

    public void showTotalPrice() {
        System.out.println("[결제금액]");

        if(cartList.isEmpty()) {
            System.out.println(EMPTY_CART);
            return;
        }

        int totalPrice = 0;

        for(Product product : productRepository.getProducts()) {
            String name = product.getName();
            if(cartList.containsKey(name)) {
                totalPrice += cartList.get(name) * product.getPrice();
            }
        }

        System.out.println("결제금액은 " + totalPrice + "원 입니다.");
        System.out.println();
    }
}
