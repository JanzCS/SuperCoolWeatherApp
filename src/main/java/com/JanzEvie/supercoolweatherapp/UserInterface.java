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
    static UserInterface mainPanel = null;
    static JTextField address = null;
    static JButton currentTemp = null;
    static JButton forecastToday = null;
    static JButton sevenDayForecast = null;
    static Forecast[] forecast = null;
    static String location = null;
    static boolean wantsCurrentTemp = false;
    static boolean wantsForecastToday = false;
    static boolean wantsSevenDayForecast = false;

    /*********************************************************
     * 							main						 *
     *********************************************************/
    public static void main( String[] args ) throws InterruptedException
    {
        //Initialize all of the objects for the java applet
        frame = new JFrame();
        mainPanel = new UserInterface();
        address = new JTextField( "Search address, location, zip..." , 50 );
        currentTemp = new JButton( "Current Temp" );
        forecastToday = new JButton( "Today's Forecast" );
        sevenDayForecast = new JButton( "Seven Day Forecast" );

        //Set up the applet
        setUpMainPanel();
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
                displayCurrentTemp();
                mainPanel.repaint();
            }
        });

        //"Today's Forecast" button
        forecastToday.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                displayForecastToday();
                mainPanel.repaint();
            }
        });

        //"Seven Day Forecast" button
        sevenDayForecast.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                displaySevenDayForecast();
                mainPanel.repaint();
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
            public void actionPerformed( ActionEvent e ){ prepAddress( address ); }
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
     *					displayCurrentTemp					 *
     *********************************************************/
    static public void displayCurrentTemp()
    {
        wantsCurrentTemp = true;
        wantsForecastToday = false;
        wantsSevenDayForecast = false;

    }//displayCurrentTemp


    /*********************************************************
     *					displayForecastToday				 *
     *********************************************************/
    static public void displayForecastToday()
    {
        wantsCurrentTemp = false;
        wantsForecastToday = true;
        wantsSevenDayForecast = false;

    }//displayForecastToday


    /*********************************************************
     *					displaySevenDayForecast				 *
     *********************************************************/
    static public void displaySevenDayForecast()
    {
        wantsCurrentTemp = false;
        wantsForecastToday = false;
        wantsSevenDayForecast = true;

    }//displaySevenDayForecast


    /*********************************************************
     *							paint						 *
     *********************************************************/
    //Gets called any time the window needs to be redrawn
    @Override
    public void paint( Graphics g )
    {
        //Call the constructor for the original method
        super.paint( g );

        //Paint the result window
        g.setColor( Color.BLACK );
        g.fillRect( 450, 300, 4, 400 );
        g.fillRect( 850, 300, 4, 400 );
        g.fillRect( 450, 300, 400, 4 );
        g.fillRect( 450, 700, 400, 4 );

        //Display the current temperature
        if( wantsCurrentTemp )
        {

        }//if

        //Display today's forecast
        else if( wantsForecastToday )
        {

        }//else if

        //Display the seven day forecast
        else if( wantsSevenDayForecast )
        {

        }//else if

    }//paint


    /*********************************************************
     *						prepAddress						 *
     *********************************************************/
    static public void prepAddress( JTextField address )
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

    }//prepAddress

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
     *					    setUpFrame		    			 *
     *********************************************************/
    static public void setUpFrame()
    {
        frame.setContentPane( mainPanel );
        frame.setSize( 1280, 1024 );
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
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        mainPanel.setLayout(new BorderLayout());

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
