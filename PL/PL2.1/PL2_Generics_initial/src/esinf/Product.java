/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.util.Objects;

/**
 *
 * @author DEI-ISEP
 */
public class Product implements Comparable<Product> {

    private String identification;
    private int quantity;
    private long price;

    Product(String identification, int quantity, long price) {
        this.identification = identification;
        this.quantity = quantity;
        this.price = price;
    }

    Product(String identification) {
        this(identification, 0, 0);
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true   
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Product)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members  
        Product c = (Product) obj;

        System.out.println(this.identification);
        System.out.println(c.getIdentification());

        return (this.identification.equals(c.getIdentification()));
    }

    @Override
    public int hashCode() {
        return this.identification.hashCode();
    }

    @Override
    public int compareTo(Product o) {
        return this.identification.compareTo(o.getIdentification());
    }
}
