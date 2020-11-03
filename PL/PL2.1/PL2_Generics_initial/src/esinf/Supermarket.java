/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author DEI-ISEP
 */
public class Supermarket implements Comparable<Supermarket> {

    Map<Invoice, Set<Product>> m;

    Supermarket() {
        m = new HashMap<>();
    }

    // Reads invoices from a list of String
    void getInvoices(List<String> l) throws Exception {
        Iterator it = l.iterator();
        Set<Product> products = new HashSet<>();
        Invoice inv = null;

        while (it.hasNext()) {
            String str = (String) it.next();
            String[] data = str.trim().split(",");
            if (data[0].equals("I")) {
                if (inv != null) {
                    m.put(inv, products);
                }
                products = new HashSet<>();
                String ref = data[1];
                String date = data[2];
                inv = new Invoice(ref, date);
            } else if (data[0].equals("P")) {
                String id = data[1];
                int qt = Integer.parseInt(data[2]);
                long price = Long.parseLong(data[3]);
                Product product = new Product(id, qt, price);
                products.add(product);
            }
            m.put(inv, products);
        }
    }

    // returns a set in which each number is the number of products in the r
    // invoice 
    Map<Invoice, Integer> numberOfProductsPerInvoice() {
        Map<Invoice, Integer> result = new HashMap<>();

        for (Map.Entry<Invoice, Set<Product>> entry : m.entrySet()) {
            Invoice key = entry.getKey();
            Set<Product> val = entry.getValue();
            int qt = val.size();
            result.put(key, qt);
        }

        return result;
    }

    // returns a Set of invoices in which each date is >d1 and <d2
    Set<Invoice> betweenDates(LocalDate d1, LocalDate d2) {
        Set<Invoice> result = new HashSet<>();
        
        for (Map.Entry<Invoice, Set<Product>> entry : m.entrySet()) {
            Invoice inv = entry.getKey();
            LocalDate invDate = inv.getDate();
            
            if(invDate.isAfter(d1) && invDate.isBefore(d2)){
                result.add(inv);
            }
        }
        return result;
    }

    // returns the sum of the price of the product in all the invoices
    long totalOfProduct(String productId) {
        
        long totalPrice = 0;

        for (Map.Entry<Invoice, Set<Product>> entry : m.entrySet()) {
            Set<Product> products = entry.getValue();
            for (Product prod : products) {
                if(prod.getIdentification().equals(productId)){
                    totalPrice += prod.getPrice()*prod.getQuantity();
                }
            }
        }
        
        return totalPrice;
    }

    // converts a map of invoices and troducts to a map which key is a product 
    // identification and the values are a set of the invoice references 
    // in which it appearss
    Map<String, Set<Invoice>> convertInvoices() {
        Map<String, Set<Invoice>> result = new HashMap<>();
        
        for (Map.Entry<Invoice, Set<Product>> entry : m.entrySet()) {
            Invoice inv = entry.getKey();
            Set<Product> products = entry.getValue();
            
            for (Product prod : products) {
                for (Map.Entry<String, Set<Invoice>> entry1 : result.entrySet()) {
                    String prodId = entry1.getKey();
                    Set<Invoice> invs = entry1.getValue();
                    if(!result.containsKey(prod.getIdentification())){
                        
                    }
                }
            }
        }
        
        return result;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true   
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Supermarket)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members  
        Supermarket c = (Supermarket) o;

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m);
        return hash;
    }

    @Override
    public int compareTo(Supermarket o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
