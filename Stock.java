import java.util.*;
import javax.swing.*;

/**
 * This class represents a stock, which has a value and can be
 * bought and sold in the simulaton.
 * 
 * Author: Gurpal Jabble 
 * ID: 160426234
 */
public class Stock{

    private String name;
    private double value;
    private int quantity;
    private String commentary;

    public Stock(String s){
        name = s;
        Random r = new Random();
        value = r.nextInt(1000)+1;
        quantity = 0;
        commentary = "";
    }

    public double getValue(){
        return value;
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getCommentary(){
        return commentary;
    }
    
    public void setCommentary(String s){
        commentary = s;
    }

    public void buy(Portfolio p, int x){
        if(x<=0){
            JOptionPane.showMessageDialog(null, "Please enter an integer larger than 0");
        }
        else if(p.getFunds()-(x*value)>=0){ 
            p.updateCapitalBuy(x*value);
            quantity = quantity + x;
        }
        else{
            JOptionPane.showMessageDialog(null,"You can not afford to buy this quantity of shares.");
        }
    }

    public void sell(Portfolio p, int x){
        if(x<=0){
            JOptionPane.showMessageDialog(null, "Please enter an integer larger than 0");
        }
        else if(quantity - x <0){
            JOptionPane.showMessageDialog(null,"You can not sell more shares than you currently own.");
        }
        else if(quantity - x == 0){
            JOptionPane.showMessageDialog(null, "You have sold all the shares you owned in this company");
            p.updateCapitalSell(x*value);
            quantity = 0;
            p.removeStock(this);;
        }
        else if(quantity != 0){
            p.updateCapitalSell(x*value);
            quantity = quantity - x;
        }    
    }

    public void setValue(double x){
        value = x;
    }

    public double getTotalValue(){
        return value*quantity;
    }
    
    public void setQuantity(int x){
        quantity = x;
    }
    

    public void updateValue(){
        double temp = value;
        Random random = new Random();
        int number = random.nextInt(50 + 1 + 50)-50;
        while(value+number<=0){
            number = random.nextInt(50 + 1 + 50)-50;
        }
        value = value + number;
        double delta = value - temp;
        delta = (delta/temp)*100;
        commentary = ("The value of this stock changed by " + Double.toString(delta) + "%");
    }
}
