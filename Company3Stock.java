import java.util.*;

/**
 * This class represents a stock for a company whos value updates differently.
 * 
 * Author: Gurpal Jabble
 * ID: 160426234
 */
public class Company3Stock extends Stock{

    public Company3Stock(String s){
        super(s);
    }

    public void updateValue(){
        double temp = this.getValue();
        Random random = new Random();
        int number = random.nextInt(500 + 1 + 500)-500;
        while(this.getValue()+number<=0){
            number = random.nextInt(500 + 1 + 500)-500;
        }
        this.setValue(this.getValue()+number);
        this.setValue(this.getValue()+number);
        double delta = this.getValue() - temp;
        delta = (delta/temp)*100;
        this.setCommentary("The value of this stock changed by " + Double.toString(delta) + "%");
    }
}
