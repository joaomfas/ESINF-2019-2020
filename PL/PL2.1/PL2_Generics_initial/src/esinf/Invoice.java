/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author DEI-ISEP
 */
public class Invoice implements Comparable<Invoice> {

    private String reference;
    private LocalDate date;

    public Invoice(String reference, String date) {
        this.reference = reference;
        if (date != null) {
            String s[] = date.split("/");
            this.date = LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        }
    }

    public Invoice(String reference) {
        this(reference, "1900/01/01");
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true   
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Invoice)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members  
        Invoice c = (Invoice) obj;
        
        return (this.getReference().equals(c.getReference()));
    }

    @Override
    public int hashCode() {
        return this.reference.hashCode();
    }


    @Override
    public int compareTo(Invoice o) {
        return this.reference.compareTo(o.getReference());
    }
}
