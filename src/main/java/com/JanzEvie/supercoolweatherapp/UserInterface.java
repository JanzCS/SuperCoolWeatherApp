package com.JanzEvie.supercoolweatherapp;//package com.JanzEvie.app;

import java.awt.*;
import java.awt.event.*;
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
    static Forecast now = null;
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
        setUpTextField(); //also sets up functionality of text field
        addTextFieldToApp();
        setUpButtons(); //also sets up functionality of buttons
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
                showCurrentTemp = false;
                showForecastToday = false;
                showSevenDayForecast = true;
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
                now = NwsParser.getCurrentWeather( location );
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

        //Reset display panel
        removeAll();
        revalidate();
        repaint();

        //Display desired weather information on display panel
        if( showCurrentTemp ) { paintCurrentTemp( g ); }
        else if( showForecastToday ) { paintForecastToday( g ); }
        else if( showSevenDayForecast ) { paintSevenDayForecast( g ); }

    }//paint

    /*********************************************************
     *					   paintCurrentTemp		    		 *
     *********************************************************/
    public void paintCurrentTemp( Graphics g )
    {
        JLabel day = new JLabel();
        day.setText( now.toString() );
        displayPanel.add( day );
        day.setBounds( 200, 150, 250, 25 );

    }//paintCurrentTemp

    /*********************************************************
     *				   paintForecastToday		    		 *
     *********************************************************/
    public void paintForecastToday( Graphics g )
    {
        JLabel day = new JLabel();
        day.setText( forecast[ 0 ].toString() );
        displayPanel.add( day );
        day.setBounds( 200, 150, 250, 25 );

        JLabel dayForecast = new JLabel();
        dayForecast.setText( forecast[ 0 ].detailedForecast );
        displayPanel.add( dayForecast );
        dayForecast.setBounds( 200, 200, 400, 25 );

        JLabel night = new JLabel();
        night.setText( forecast[ 1 ].toString() );
        displayPanel.add( night );
        night.setBounds( 200, 250, 250, 25 );

    }//paintForecastToday

    /*********************************************************
     *				   paintSevenDayForecast	    		 *
     *********************************************************/
    public void paintSevenDayForecast( Graphics g )
    {
        JLabel day0 = new JLabel();
        JLabel day1 = new JLabel();
        JLabel day2 = new JLabel();
        JLabel day3 = new JLabel();
        JLabel day4 = new JLabel();
        JLabel day5 = new JLabel();
        JLabel day6 = new JLabel();

        day0.setText( forecast[ 0 ].toString() );
        day1.setText( forecast[ 1 ].toString() );
        day2.setText( forecast[ 2 ].toString() );
        day3.setText( forecast[ 3 ].toString() );
        day4.setText( forecast[ 4 ].toString() );
        day5.setText( forecast[ 5 ].toString() );
        day6.setText( forecast[ 6 ].toString() );

        displayPanel.add( day0 );
        displayPanel.add( day1 );
        displayPanel.add( day2 );
        displayPanel.add( day3 );
        displayPanel.add( day4 );
        displayPanel.add( day5 );
        displayPanel.add( day6 );

        day0.setBounds( 200, 50, 250, 25 );
        day1.setBounds( 200, 100, 250, 25 );
        day2.setBounds( 200, 150, 250, 25 );
        day3.setBounds( 200, 200, 250, 25 );
        day4.setBounds( 200, 250, 250, 25 );
        day5.setBounds( 200, 300, 250, 25 );
        day6.setBounds( 200, 350, 250, 25 );

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
        mainPanel.add( displayPanel );
        displayPanel.setBounds( 350, 300, 580, 400 );
        displayPanel.setBackground( Color.WHITE );
        displayPanel.setLayout( null );
        displayPanel.setLayout( new BorderLayout() );
        displayPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK, 3 ) );

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
