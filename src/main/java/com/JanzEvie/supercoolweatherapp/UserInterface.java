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
    static String location = null;
    static boolean wantsCurrentTemp = false;
    static boolean wantsForecastToday = false;
    static boolean wantsSevenDayForecast = false;

    /*********************************************************
     * 							main						 *
     *********************************************************/
    public static void main( String[] args ) throws InterruptedException
    {
        //Create the frame
        JFrame frame = new JFrame();

        //Create and set up the main panel
        UserInterface mainPanel = new UserInterface();
        mainPanel.setSize( 1280, 1024 );
        mainPanel.setBackground( Color.WHITE );
        mainPanel.setLayout( null );
        mainPanel.setLayout( new BorderLayout() );

        //Set up the frame
        frame.setContentPane( mainPanel );
        frame.setSize( 1280, 1024 );
        frame.setTitle( "Super Cool Weather App" );
        frame.getContentPane().setLayout( null );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        //Create a set up location text field
        JTextField address = new JTextField( "Search address, location, zip..." , 50 );
        address.setBounds( 400, 100, 500, 50 );
        mainPanel.add( address );

        //Add an action listener to the text field
        address.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e ){ prepAddress( address ); }
        });

        //Add buttons to the panel
        JButton currentTemp = new JButton( "Current Temp" );
        JButton forecastToday = new JButton( "Today's Forecast" );
        JButton sevenDayForecast = new JButton( "Seven Day Forecast" );
        currentTemp.setBounds( 150, 200, 300, 50 );
        forecastToday.setBounds( 500, 200, 300, 50 );
        sevenDayForecast.setBounds( 850, 200, 300, 50 );
        mainPanel.add( currentTemp );
        mainPanel.add( forecastToday );
        mainPanel.add( sevenDayForecast );

        //Add an action listener to the text field
        currentTemp.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                displayCurrentTemp();
                mainPanel.repaint();
            }

        });

        //Make the window visible
        frame.setVisible( true );


    }//main

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

    }//paintComponent


    /*********************************************************
     *						prepAddress						 *
     *********************************************************/
    static public void prepAddress( JTextField address )
    {
        boolean valid = false;

        //Obtain a valid address from the user
        while( !valid )
        {
            try
            {
                location = address.getText();
                location = location.replaceAll(" ", "+");
                NwsParser.run( location );
                valid = true;

            }//try

            catch ( RuntimeErrorException e )
            {
                JOptionPane.showMessageDialog(null, "This is not a valid address. Please try again.", "Error Message", JOptionPane.INFORMATION_MESSAGE);
            }

        }//while

    }//prepAddress

}//com.JanzEvie.supercoolweatherapp.UserInterface
