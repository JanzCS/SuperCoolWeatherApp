package com.JanzEvie.supercoolweatherapp;//package com.JanzEvie.app;

import java.awt.*;
import java.awt.event.*;
import javax.management.RuntimeErrorException;
import javax.swing.*;

public class UserInterface extends JPanel
{
    /*********************************************************
     *					Member variables					 *
     *********************************************************/
    static JFrame frame = null;
    static JPanel mainPanel = null;
    static UserInterface displayPanel = null;
    static JTextField address = null;
    static JButton currentTemp = null;
    static JButton forecastToday = null;
    static JButton sevenDayForecast = null;
    static Forecast[] forecast = null;
    static String location = null;
    static boolean showCurrentTemp = false;
    static boolean showForecastToday = false;
    static boolean showSevenDayForecast = false;

    /*********************************************************
     * 							main						 *
     *********************************************************/
    public static void main( String[] args ) throws InterruptedException
    {
        //Initialize all of the objects for the java applet
        frame = new JFrame();
        mainPanel = new JPanel();
        displayPanel = new UserInterface();
        address = new JTextField( "Search address, location, zip..." , 50 );
        currentTemp = new JButton( "Current Temp" );
        forecastToday = new JButton( "Today's Forecast" );
        sevenDayForecast = new JButton( "Seven Day Forecast" );

        //Set up the applet
        setUpMainPanel();
        setUpDisplayPanel();
        setUpFrame();
        setUpTextField();
        addTextFieldToApp();
        setUpButtons();
        addButtonsToApp();

        //Make the window visible
        frame.setVisible( true );

    }//main

    /*********************************************************
     *				   addButtonFunctionality	    		 *
     *********************************************************/
    static public void addButtonFunctionality()
    {
        //"Current Temp" button
        currentTemp.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                showCurrentTemp = true;
                showForecastToday = false;
                showSevenDayForecast = false;
                displayPanel.repaint();
            }
        });

        //"Today's Forecast" button
        forecastToday.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                showCurrentTemp = false;
                showForecastToday = true;
                showSevenDayForecast = false;
                displayPanel.repaint();
            }
        });

        //"Seven Day Forecast" button
        sevenDayForecast.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                showCurrentTemp = true;
                showForecastToday = false;
                showSevenDayForecast = false;
                displayPanel.repaint();
            }
        });

    }//addButtonFunctionality

    /*********************************************************
     *			    		addButtonsToApp		    		 *
     *********************************************************/
    static public void addButtonsToApp()
    {
        mainPanel.add( currentTemp );
        mainPanel.add( forecastToday );
        mainPanel.add( sevenDayForecast );

    }//addButtonsToApp


    /*********************************************************
     *				   addTextFieldFunctionality	   		 *
     *********************************************************/
    static public void addTextFieldFunctionality()
    {
        address.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                getAddress( address ); }
        });

    }//addTextFieldFunctionality


    /*********************************************************
     *			    		addTextFieldToApp	    		 *
     *********************************************************/
    static public void addTextFieldToApp()
    {
        mainPanel.add( address );

    }//addTextFieldToApp


    /*********************************************************
     *						getAddress						 *
     *********************************************************/
    static public void getAddress( JTextField address )
    {
        boolean valid = false;

        //Obtain a valid address from the user
        while( !valid )
        {
            valid = false;

            try {
                location = address.getText();
                location = location.replaceAll(" ", "+");
                forecast = NwsParser.getSevenDayForecast( location );
            }

            catch ( RuntimeException e ) {
                JOptionPane.showMessageDialog(null, "This is not a valid address. Please try again.", "Error Message", JOptionPane.INFORMATION_MESSAGE);
            }

            valid = true;

        }//while

    }//getAddress


    /*********************************************************
     *							paint						 *
     *********************************************************/
    @Override
    public void paint( Graphics g )
    {
        //Call the constructor for the original method
        super.paint( g );

        //Display desired weather information
        if( showCurrentTemp ) { paintCurrentTemp( g ); }
        else if( showForecastToday ) { paintForecastToday( g ); }
        else if( showSevenDayForecast ) { paintSevenDayForecast( g ); }

    }//paint


    /*********************************************************
     *					   paintCurrentTemp		    		 *
     *********************************************************/
    public void paintCurrentTemp( Graphics g )
    {

    }//paintCurrentTemp


    /*********************************************************
     *				   paintForecastToday		    		 *
     *********************************************************/
    public void paintForecastToday( Graphics g )
    {


    }//paintForecastToday


    /*********************************************************
     *				   paintSevenDayForecast	    		 *
     *********************************************************/
    public void paintSevenDayForecast( Graphics g )
    {


    }//paintSevenDayForecast


    /*********************************************************
     *					    setUpButtons		    		 *
     *********************************************************/
    static public void setUpButtons()
    {
        currentTemp.setBounds( 150, 200, 300, 50 );
        forecastToday.setBounds( 500, 200, 300, 50 );
        sevenDayForecast.setBounds( 850, 200, 300, 50 );
        addButtonFunctionality();

    }//setUpButtons


    /*********************************************************
     *					    setUpDisplayPanel				 *
     *********************************************************/
    static public void setUpDisplayPanel()
    {
        mainPanel.add( displayPanel ); //add the game panel to the main panel
        displayPanel.setBounds( 350, 300, 580, 400 );
        displayPanel.setBackground( Color.BLACK );
        displayPanel.setLayout( null );
        displayPanel.setLayout( new BorderLayout() );
        displayPanel.setBorder( BorderFactory.createLineBorder( Color.RED, 3 ) );

    }//setUpDisplayPanel


    /*********************************************************
     *					    setUpFrame		    			 *
     *********************************************************/
    static public void setUpFrame()
    {
        frame.setContentPane( mainPanel );
        frame.setSize( 400, 400 );
        frame.setTitle( "Super Cool Weather App" );
        frame.getContentPane().setLayout( null );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }//setUpFrame

    /*********************************************************
     *					    setUpMainPanel					 *
     *********************************************************/
    static public void setUpMainPanel()
    {
        mainPanel.setSize(1280, 1024);
        mainPanel.setBackground( Color.WHITE );
        mainPanel.setLayout( null );
        mainPanel.setLayout( new BorderLayout() );

    }//setUpMainPanel

    /*********************************************************
     *					    setUpTextField	    			 *
     *********************************************************/
    static public void setUpTextField()
    {
        address.setBounds( 400, 100, 500, 50 );
        addTextFieldFunctionality();

    }//setUpTextField

}//com.JanzEvie.supercoolweatherapp.UserInterface
