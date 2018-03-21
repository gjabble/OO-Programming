import java.util.*;
/**
 * This class represents a portfolio for the user in the simulation,
 * it holds information about the shares they currently own and their
 * total value.
 * 
 * Author: Gurpal Jabble
 * ID: 160426234
 */
public class Portfolio{

    private String name;
    private double funds;
    private double stockValue;
    private double capital;
    private ArrayList<Stock> stocks;

    public Portfolio(String name, double funds){
        this.name = name;
        capital = funds;
        this.funds = funds;
        stockValue = 0;
        stocks = new ArrayList<Stock>();
    }

    public String getName(){
        return name;
    }

    public double getCapital(){
        return capital;
    }

    public double getFunds(){
        return funds;
    }

    public double getStockValue(){
        return stockValue;
    }

    public ArrayList<Stock> getStocks(){
        return stocks;
    }
    
    public void setStockValue(double x){
        stockValue = x;
    }
    
    public void setCapital(double x){
        capital = x;
    }
    
    public void setStocks(ArrayList<Stock> x){
        stocks = x;
    }

    public void updateCapitalBuy(double x){
        funds = funds - x;
        stockValue = stockValue + x;
        capital = funds + stockValue;
    }

    public void updateCapitalSell(double x){
        funds = funds + x;
        stockValue = stockValue - x;
        capital = funds + stockValue;
    }

    public void addStock(Stock s){
        stocks.add(s);
    }

    public void removeStock(Stock s){
        for(int i=0; i<stocks.size(); i++){
            if(s == stocks.get(i)){
                stocks.remove(i);
            }
        }
    }

    public void updateNextDay(Stock[] marketData){
        double temp = 0;
        for(int i=0; i<stocks.size(); i++){
            Stock s = stocks.get(i);
            for(int j=0; j<marketData.length;j++){
                marketData[j].updateValue();
                if(marketData[j].getName().equals(s.getName())){
                    s.setValue(marketData[j].getValue());
                }
            }
        }
        for(int i=0; i<stocks.size();i++){
            temp = temp + stocks.get(i).getValue()*stocks.get(i).getQuantity();
        }
        stockValue = temp;
        capital = funds + stockValue;
    }

}

