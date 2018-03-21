import java.io.*;
import javax.swing.*;
import java.util.*;
/**
 * This class deals with saving the users progress. 
 * 
 * Author: Gurpal Jabble
 * ID: 160426234
 */
public class Save{

    
    public Save(Portfolio p, Stock[] marketData){
        try{
            PrintWriter outputStream = new PrintWriter(new FileWriter("README.txt"));
            ArrayList<Stock> stocks = p.getStocks();
            outputStream.println(stocks.size());
            outputStream.println(p.getName());
            outputStream.println(p.getFunds());
            outputStream.println(p.getStockValue());
            outputStream.println(p.getCapital());
            for(int i=0; i<stocks.size(); i++){
                outputStream.println(stocks.get(i).getName());
                outputStream.println(stocks.get(i).getValue());
                outputStream.println(stocks.get(i).getQuantity());
            }
            for(int j=0; j<marketData.length; j++){
                outputStream.println(marketData[j].getName());
                outputStream.println(marketData[j].getValue());
            }
            outputStream.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "something went wrong");
        }
    }
}
