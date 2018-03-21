import java.io.*;
import javax.swing.*;
import java.util.*;
/**
 * This class deals with loading user data from a file.
 * 
 * Author: Gurpal Jabble 
 * ID: 160426234
 */
public class Load{

    private Stock[] marketData;
    private ArrayList<Stock> stocks;
    private Portfolio p;

    public Load(){
        try{
            marketData = new Stock[3];
            BufferedReader inputStream = new BufferedReader(new FileReader("README.txt"));
            int stockLength = Integer.parseInt(inputStream.readLine());
            String name = inputStream.readLine();
            double funds = Double.parseDouble(inputStream.readLine());
            double stockvalue = Double.parseDouble(inputStream.readLine());
            double capital = Double.parseDouble(inputStream.readLine());
            stocks = new ArrayList<Stock>();
            for(int i=0; i<stockLength; i++){
                String s = inputStream.readLine();

                //load user stock list data
                if(s.equals("Company 1")){
                    Stock s1 = new Company1Stock(s);
                    double value = Double.parseDouble(inputStream.readLine());
                    s1.setValue(value);
                    int quantity = Integer.parseInt(inputStream.readLine());
                    s1.setQuantity(quantity);
                    stocks.add(s1);
                }
                else if(s.equals("Company 2")){
                    Stock s1 = new Company2Stock(s);
                    double value = Double.parseDouble(inputStream.readLine());
                    s1.setValue(value);
                    int quantity = Integer.parseInt(inputStream.readLine());
                    s1.setQuantity(quantity);
                    stocks.add(s1);
                }
                else if(s.equals("Company 3")){
                    Stock s1 = new Company3Stock(s);
                    double value = Double.parseDouble(inputStream.readLine());
                    s1.setValue(value);
                    int quantity = Integer.parseInt(inputStream.readLine());
                    s1.setQuantity(quantity);
                    stocks.add(s1);
                }
            }

            //load market data
            for(int j=0; j<3; j++){
                String s = inputStream.readLine();
                if(s.equals("Company 1")){
                    Stock s1 = new Company1Stock(s);
                    double value = Double.parseDouble(inputStream.readLine());
                    s1.setValue(value);
                    marketData[0] = s1;
                }
                else if(s.equals("Company 2")){
                    Stock s1 = new Company2Stock(s);
                    double value = Double.parseDouble(inputStream.readLine());
                    s1.setValue(value);
                    marketData[1] = s1;
                }
                else if(s.equals("Company 3")){
                    Stock s1 = new Company3Stock(s);
                    double value = Double.parseDouble(inputStream.readLine());
                    s1.setValue(value);
                    marketData[2] = s1;
                }                
            }
            inputStream.close();

            p = new Portfolio(name, funds);
            if(stockLength != 0){
                p.setStocks(stocks);
            }
            p.setStockValue(stockvalue);
            p.setCapital(capital);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "You have no save data to load");
        }

    }
    
    public Portfolio getPortfolio(){
        return p;
    }

    public Stock[] getMarketData(){
        return marketData;
    }
}
