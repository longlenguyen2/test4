
package model;

import java.util.ArrayList;
import jdk.nashorn.internal.parser.TokenType;

public class Cart {    
    private ArrayList<Item> cart;
    public Cart() {
        cart = new ArrayList<Item>();
    }
    public Cart(ArrayList<Item> cart) {
        this.cart = cart;
    }
    public Item getItem(int i) {
        return cart.get(i);
    }    
    //thêm sản phẩm
    public void insertToCart(Item item) {   
       if (this.contains(item)) {
            for (Item cart1 : cart) {
                if (cart1.getProduct().getProductID() == item.getProduct().getProductID()) {
                    cart1.setQuantity(item.getQuantity()+cart1.getQuantity());   
                    break;
                }
            }                     
       } else {
            cart.add(item);            
       }
    } 
    public boolean contains(Item item){
        boolean bl = false;
        if (cart.isEmpty())
            bl = false;
        else
            for (Item cart1 : cart) {
                if (cart1.getProduct().getProductID() == item.getProduct().getProductID()) {
                    bl = true;
                    break;
                } else {
                    bl = false;
                }
            }
        return bl;
    }
    // xóa sản phẩm
    public void removeToCart(Item item) {
            cart.remove(item);        
    }
    //sửa số lượng sản phẩm
    public void alterToCart(int productID, int quantity){
        for (Item cart1 : cart) {
                if (cart1.getProduct().getProductID() == productID) {
                    cart1.setQuantity(quantity);   
                    break;
                }
        }  
    }    
//    public static void main(String[] args) {
//        Product pro = new Product(1, 1, "n1", 1, "1");
//        Product pro1 = new Product(2, 2, "n2", 2, "2");
//        Product pro2 = new Product(3, 3, "n3", 1, "3");
//        Product pro3 = new Product(4, 4, "n4", 2, "4");
//        Item item= new Item(pro,10);
//        Item item1= new Item(pro1,20);
//        Item item2= new Item(pro2,30);
//        Item item3= new Item(pro3,40);
//        Cart cart = new Cart();
//        cart.insertToCart(item);
//        cart.insertToCart(item1);
//        cart.insertToCart(item2);
//        cart.insertToCart(item3);
//        cart.alterToCart(1, 100);
//        for(int i = 0; i< cart.countCart();i++)
//        {
//            System.out.println(cart.getItem(i).getQuantity()+"-"+cart.getItem(i).getProduct().getProductName());         
//        }
//    }    
    //tính tổng số sản phẩm
    public int countCart(){
        return cart.size();
    }
    //tính tổng tiên
    public double countMoney() {
        int count = 0;
        for (Item item : cart) {
            count += item.getProduct().getProductPrice() * item.getQuantity();
        }
        return count;                
    }    

}
    


