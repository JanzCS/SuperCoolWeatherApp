package com.JanzEvie.supercoolweatherapp;//package com.JanzEvie.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class UserInterface extends JPanel
{
    /*********************************************************
     *					Member variables					 *
     *********************************************************/
    static JFrame frame = null;
    static JPanel mainPanel = null;
    static UserInterface displayPanel = null;
    static JLabel heading = null;
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
        heading = new JLabel();
        address = new JTextField( "Search address, location, zip..." , 50 );
        currentTemp = new JButton( "Current Temp" );
        forecastToday = new JButton( "Today's Forecast" );
        sevenDayForecast = new JButton( "Seven Day Forecast" );

        //Set up the applet
        setUpMainPanel();
        setUpDisplayPanel();
        setUpHeading();
        setUpFrame();
        setUpTextField(); //also sets up functionality of text field
        setUpButtons(); //also sets up functionality of buttons

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
        g.drawString( "Temp: " + now.toString(), 200, 200);

    }//paintCurrentTemp

    /*********************************************************
     *				   paintForecastToday		    		 *
     *********************************************************/
    public void paintForecastToday( Graphics g )
    {
       g.drawString( forecast[ 0 ].toString(), 200,150 );
       g.drawString( forecast[ 0 ].detailedForecast, 100,200 );
       g.drawString( forecast[ 1 ].toString(), 200,250 );
       g.drawString( forecast[ 1 ].detailedForecast, 100,300 );

    }//paintForecastToday

    /*********************************************************
     *				   paintSevenDayForecast	    		 *
     *********************************************************/
    public void paintSevenDayForecast( Graphics g )
    {
        for( int i = 0; i < 14; i++ ) {
            g.drawString( forecast[ i ].toString(), 200,( 20 + 25 * ( i + 1 )));
            //g.drawImage( forecast[ i ].icon, 0, 0, this); // see javadoc for more info on the parameters
        }

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
        mainPanel.add( currentTemp );
        mainPanel.add( forecastToday );
        mainPanel.add( sevenDayForecast );

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
        frame.setSize( 1280, 1024 );
        frame.setTitle( "Super Cool Weather App" );
        frame.getContentPane().setLayout( null );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }//setUpFrame

    /*********************************************************
     *					    setUpHeading		   			 *
     *********************************************************/
    static public void setUpHeading()
    {
        //Set the heading's font
        Font font = new Font("Century", Font.BOLD,20);

        //Add the heading
        heading.setText( "Super Cool Weather App" );
        heading.setFont( font );
        heading.setBounds( 540, 50, 500, 25 );
        mainPanel.add( heading );

    }//setUpHeading

    /*********************************************************
     *					    setUpMainPanel					 *
     *********************************************************/
    static public void setUpMainPanel()
    {
        mainPanel.setSize(1280, 1024);
        mainPanel.setBackground( Color.LIGHT_GRAY );
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
        mainPanel.add( address );

    }//setUpTextField

}//com.JanzEvie.supercoolweatherapp.UserInterface
