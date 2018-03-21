import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
/**
 * This class will implement the graphical user interface for the stock market
 * simulation and will mainly run the simulation.
 * 
 * Author: Gurpal Jabble 
 * ID: 160426234
 */
public class StockMarketGUI extends JFrame{

    //simulation variables
    private Portfolio p;
    private Stock[] marketData;
    private boolean day1;
    private boolean loadData;
    private Load userData;
    private Save userData1;

    //intro frame components
    private JFrame introFrame;
    private JPanel introPanel;
    private JPanel introPanel1;
    private JLabel intro1;
    private JLabel intro2;
    private JLabel intro3;
    private JLabel nameintro;
    private JTextField nameintro1;
    private JLabel fundsintro;
    private JTextField fundsintro1;
    private JButton introsubmit;
    private JButton introload;

    //portfolio frame components
    private JFrame portfolioFrame;
    private JPanel portfolioPanel;
    private JLabel nameportfolio;
    private JTextField nameportfolio1;
    private JLabel fundsportfolio;
    private JTextField fundsportfolio1;
    private JLabel stockvalueportfolio;
    private JTextField stockvalueportfolio1;
    private JLabel capitalportfolio;
    private JTextField capitalportfolio1;
    private JButton nextDay;
    private JButton save;
    private JFrame portfolioFrame2;
    private DefaultTableModel model1;
    private JTable table1;
    private JScrollPane sp1;

    //market frame components
    private JFrame marketFrame;
    private JPanel marketPanel;
    private JTable table;
    private JScrollPane sp;
    private JButton buybutton;
    private JButton sellbutton;

    //buy frame components
    private JFrame buyframe;
    private JPanel buypanel;
    private JTextField buyamount;
    private JComboBox<String> dropdown;
    private JButton buysubmit;
    private JLabel buylabel;

    //sell frame components
    private JFrame sellframe;
    private JPanel sellpanel;
    private JTextField sellamount;
    private JComboBox<String> dropdown2;
    private JButton sellsubmit;
    private JLabel selllabel;

    //stock market run components
    private JFrame f1;
    private JFrame f2;
    private JFrame f3;

    //constructor for introduction screen to simulator.
    public StockMarketGUI(){
        day1 = true;
        marketData = new Stock[3];
        Stock s1 = new Company1Stock("Company 1");
        Stock s2 = new Company2Stock("Company 2");
        Stock s3 = new Company3Stock("Company 3");
        marketData[0] = s1;
        marketData[1] = s2;
        marketData[2] = s3;
        introFrame = new JFrame("Stock Market Simulator");
        introPanel = new JPanel(new GridLayout(3,1));
        introPanel1 = new JPanel(new GridLayout(3,2));
        intro1 = new JLabel("             Welcome to this Stock Market simulator");
        intro2 = new JLabel("Start a new run by entering your name and initial funds");
        intro3 = new JLabel("                             Or load a previous run");
        nameintro = new JLabel("Name:");
        nameintro1 = new JTextField();
        fundsintro = new JLabel("Funds:");
        fundsintro1 = new JTextField();
        introsubmit = new JButton("Submit");
        introsubmit.addActionListener(new ButtonClickListener());
        introload = new JButton("Load");
        introload.addActionListener(new ButtonClickListener());
        introPanel.add(intro1);
        introPanel.add(intro2);
        introPanel.add(intro3);
        introPanel1.add(nameintro);
        introPanel1.add(nameintro1);
        introPanel1.add(fundsintro);
        introPanel1.add(fundsintro1);
        introPanel1.add(introsubmit);
        introPanel1.add(introload);
        introFrame.add("North",introPanel);
        introFrame.add("South",introPanel1);

        introFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        introFrame.pack();
        introFrame.setLocationRelativeTo(null);
        introFrame.setVisible(true); 
    }

    public void stockMarketRun(){
        f1 = marketFrame();
        f2 = portfolioFrame();
        if(day1){
            JOptionPane.showMessageDialog(null,"Hello and welcome to this Stock Market simulator");
            JOptionPane.showMessageDialog(null,"The aim of this simulator is to buy and sell shares in an attempt to make a profit");
            JOptionPane.showMessageDialog(null,"In order to do this, click buy and sell on the Market Window and track your progress on your Portfolio Window");
            JOptionPane.showMessageDialog(null,"Clicking Next Day will change the share prices, the changes are documented on the Market Window");
            JOptionPane.showMessageDialog(null,"The goal is to increase your Capital value as high as you can. Good Luck!");
        }
    }
    //constructs the portfolio frame displaying the users name and current values. As well as the option to move to the
    //next day and save.
    public JFrame portfolioFrame(){
        portfolioFrame = new JFrame("Portfolio");
        portfolioPanel = new JPanel(new GridLayout(5,2));
        nameportfolio = new JLabel("Name:");
        nameportfolio1 = new JTextField(p.getName());
        nameportfolio1.setEditable(false);
        fundsportfolio = new JLabel("Funds:");
        fundsportfolio1 = new JTextField(Double.toString(p.getFunds()));
        fundsportfolio1.setEditable(false);
        stockvalueportfolio = new JLabel("Stock Value:");
        stockvalueportfolio1 = new JTextField(Double.toString(p.getStockValue()));
        stockvalueportfolio1.setEditable(false);
        capitalportfolio = new JLabel("Capital Value:");
        capitalportfolio1 = new JTextField(Double.toString(p.getCapital()));
        capitalportfolio1.setEditable(false);
        nextDay = new JButton("Next Day");
        nextDay.addActionListener(new ButtonClickListener());
        save = new JButton("Save");
        save.addActionListener(new ButtonClickListener());

        String[] columns = {"Company Name","Quantity Owned","Value"};
        model1 = new DefaultTableModel(columns, 0);
        for(int i=0; i<p.getStocks().size();i++){
            String name = p.getStocks().get(i).getName();
            double quantity = p.getStocks().get(i).getQuantity();
            double value = p.getStocks().get(i).getTotalValue();
            Object[] rowData = {name,quantity,value};
            model1.addRow(rowData);
        }
        table1 = new JTable(model1);
        sp1 = new JScrollPane(table1);

        portfolioPanel.add(nameportfolio);
        portfolioPanel.add(nameportfolio1);
        portfolioPanel.add(fundsportfolio);
        portfolioPanel.add(fundsportfolio1);
        portfolioPanel.add(stockvalueportfolio);
        portfolioPanel.add(stockvalueportfolio1);
        portfolioPanel.add(capitalportfolio);
        portfolioPanel.add(capitalportfolio1);
        portfolioPanel.add(nextDay);
        portfolioPanel.add(save);
        portfolioFrame.add("North",portfolioPanel);
        portfolioFrame.add("South",sp1);
        portfolioFrame.pack();
        portfolioFrame.setLocationRelativeTo(null);
        portfolioFrame.setVisible(true);

        return portfolioFrame;
    }
    //constructs a frame with information about companies and stock values.
    public JFrame marketFrame(){
        marketFrame = new JFrame("Market");
        marketPanel = new JPanel();
        buybutton = new JButton("Buy Shares");
        buybutton.addActionListener(new ButtonClickListener());
        sellbutton = new JButton("Sell Shares");
        sellbutton.addActionListener(new ButtonClickListener());
        marketPanel.add(buybutton);
        marketPanel.add(sellbutton);

        Object[] columns = {"Company Name","Share Price","Comments"};
        Object[][] rowData = {{marketData[0].getName(),marketData[0].getValue(),marketData[0].getCommentary()},
                {marketData[1].getName(),marketData[1].getValue(),marketData[1].getCommentary()},
                {marketData[2].getName(),marketData[2].getValue(),marketData[2].getCommentary()}};
        table = new JTable(rowData,columns);
        sp = new JScrollPane(table);

        marketFrame.add("North",sp);
        marketFrame.add("South",marketPanel);
        marketFrame.pack();
        marketFrame.setLocationRelativeTo(null);
        marketFrame.setVisible(true);
        return marketFrame;
    }

    public void Buy(){
        buyframe = new JFrame("Buy");
        buypanel = new JPanel();
        String[] choices = new String[marketData.length];
        for(int i=0; i<marketData.length; i++){
            choices[i] = marketData[i].getName();
        }
        dropdown = new JComboBox<String>(choices);
        dropdown.setEditable(false);
        buysubmit = new JButton("Submit");
        buysubmit.addActionListener(new ButtonClickListener());
        buyamount = new JTextField(5);
        buylabel = new JLabel("Amount:");
        buypanel.add(buylabel);
        buypanel.add(buyamount);
        buypanel.add(buysubmit);
        buyframe.add("North",dropdown);
        buyframe.add("South",buypanel);
        buyframe.pack();
        buyframe.setLocationRelativeTo(null);
        buyframe.setVisible(true);
    }

    public void Sell(){
        sellframe = new JFrame("Sell");
        sellpanel = new JPanel();
        String[] choices2 = new String[marketData.length];
        for(int i=0; i<marketData.length; i++){
            choices2[i] = marketData[i].getName();
        }
        dropdown2 = new JComboBox<String>(choices2);
        sellsubmit = new JButton("Submit");
        sellsubmit.addActionListener(new ButtonClickListener());
        sellamount = new JTextField(5);
        selllabel = new JLabel("Amount:");
        sellpanel.add(selllabel);
        sellpanel.add(sellamount);
        sellpanel.add(sellsubmit);
        sellframe.add("North",dropdown2);
        sellframe.add("South",sellpanel);
        sellframe.pack();
        sellframe.setLocationRelativeTo(null);
        sellframe.setVisible(true);
    }

    public void buySubmit(){
        String selected = (String) dropdown.getSelectedItem();
        boolean exists = false;
        try{
            //check if its first time buying this share
            for(int i=0; i<p.getStocks().size(); i++){
                if(p.getStocks().get(i).getName().equals(selected)){
                    p.getStocks().get(i).buy(p,Integer.parseInt(buyamount.getText())); 
                    exists = true;
                }
            }
            //if first time, adds stock to portfolio
            if(exists == false){
                int x = Integer.parseInt(buyamount.getText());
                for(int i=0; i<marketData.length; i++){
                    if(marketData[i].getName().equals(selected)){
                        Stock s = marketData[i];
                        if(p.getFunds()-s.getValue()*x>=0 && x!=0){
                            p.addStock(s);
                            p.getStocks().get(p.getStocks().size()-1).buy(p,x);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "You can not buy this quantity of shares");
                        }
                    }
                }
            }
            buyframe.dispose();
            portfolioFrame.dispose();
            portfolioFrame();
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter an integer value");
        }
    }

    public void sellSubmit(){
        String selected = (String) dropdown2.getSelectedItem();
        boolean exists = false;
        try{
            for(int i=0; i<p.getStocks().size(); i++){
                if(p.getStocks().get(i).getName().equals(selected)){
                    p.getStocks().get(i).sell(p,Integer.parseInt(sellamount.getText()));
                    exists = true;
                }
            }
            if(exists == false){
                JOptionPane.showMessageDialog(null, "You can't sell shares you don't own");
            }
            sellframe.dispose();
            portfolioFrame.dispose();
            portfolioFrame();
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please enter an integer value");
        }

    }

    public void introSubmit(){
        try{
            double x = Double.parseDouble(fundsintro1.getText());
            if(x<5000){
                x = 5000;
                JOptionPane.showMessageDialog(null,"Since you entered a low amount of initial funds, we defaulted it to 5000.");
            }
            p = new Portfolio(nameintro1.getText(),x);
            introFrame.dispose();
            stockMarketRun();
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please enter a number for funds.");
        }
    }

    //this handles what the program needs to do when buttons are clicked.
    public class ButtonClickListener implements ActionListener{

        public void actionPerformed(ActionEvent evt){
            Object source = evt.getSource();
            if(source == introsubmit){
                introSubmit();
            }
            else if(source == introload){
                loadData = true;
                userData = new Load();
                p = userData.getPortfolio();
                marketData = userData.getMarketData();
                introFrame.dispose();
                stockMarketRun();

            }
            else if(source == nextDay){
                day1 = false;
                for(int i=0; i<marketData.length; i++){
                    marketData[i].updateValue();
                }
                p.updateNextDay(marketData);
                marketFrame.dispose();
                portfolioFrame.dispose();
                stockMarketRun();
            }
            else if(source == buybutton){
                Buy();
            }
            else if(source == sellbutton){
                Sell();
            }
            else if(source == buysubmit){
                buySubmit();
            }
            else if(source == sellsubmit){
                sellSubmit();
            }
            else if(source == save){
                JOptionPane.showMessageDialog(null,"Data is being saved");
                userData1 = new Save(p,marketData);
            }

        }
    }

    public static void main(String[] args){
        StockMarketGUI g = new StockMarketGUI();
    }
}

