/*
This is GUI application for performing control over the Golf Cart
Uncomment the commented commands in the "buttonProperties()" method in order to display the received sensor data transmitted via MQTT from the Golf Cart on the correspondent GUI components.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class settingsGUI {
    String topicStartenOderAufhören = "GolfCart/StartOrStop";
    String topicDrivingDir = "GolfCart/DrivingDir";
    String topicSpeed = "GolfCart/Speed";
    String topicSteeringPos = "GolfCart/SteeringPosition";
    String startenOderAufhören;
    String drivingDir = "F";
    double speed = 5;
    double steeringPos = 0;
    double distFromObstacle = 0;
    JFrame frame = new JFrame();
    JButton button0 = new JButton("Start");
    JButton button1 = new JButton("Forward");
    JButton button2 = new JButton("Backward");
    JLabel spL = new JLabel("Speed:");
    JLabel speedDisplay = new JLabel(Double.toString(speed)+" km/h");
    JButton buttonSP = new JButton("+");
    JButton buttonSM = new JButton("-");
    JLabel steeringPosition = new JLabel("Steering Position");
    JLabel steeringPosAngle = new JLabel(Double.toString(steeringPos)+"\u00B0");
    JButton buttonSPP = new JButton("+");
    JButton buttonSPM = new JButton("-");
    JLabel distanceToObstacle = new JLabel("Distance to obstacle");
    JLabel distanceToObstacleDisplay = new JLabel(Double.toString(distFromObstacle)+" m");
    JButton buttonF = new JButton("Stop");

    settingsGUI(){
        GUI();
        buttonProperties();
    }
    public void GUI(){
        frame.setTitle("Golf Car Control");
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setBounds(700,100,600,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void buttonProperties(){
        button0.setBounds(260,100,80,40);
        frame.add(button0);
        button0.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                button0.setBackground(Color.red);
                buttonF.setBackground(null);
                startenOderAufhören = "S";
                String commandStart = startenOderAufhören;
                String commandDir = drivingDir;
                String commandSpeed = Double.toString(speed);
                String commandSteeringPos = Double.toString(steeringPos);
                publish.mqttPublisher(commandStart, topicStartenOderAufhören);
                publish.mqttPublisher(commandDir, topicDrivingDir);
                publish.mqttPublisher(commandSpeed, topicSpeed);
                publish.mqttPublisher(commandSteeringPos, topicSteeringPos);
                /* drivingDir = subscrDrivingDir.getDrivingDir();
                speed = Double.parseDouble(subscrSpeed.getSpeed());
                steeringPos = Double.parseDouble(subscrSteeringPos.getSteeringPos()); */
            }
        });

        button1.setBounds(100,200,150,40);
        frame.add(button1);
        button1.setBackground(Color.yellow);
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                button1.setBackground(Color.yellow);
                button2.setBackground(null);
                drivingDir = "F";
                String command = drivingDir;
                publish.mqttPublisher(command, topicDrivingDir);
            }
        });

        button2.setBounds(350,200,150,40);
        frame.add(button2);
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                button2.setBackground(Color.yellow);
                button1.setBackground(null);
                drivingDir = "B";
                String command = drivingDir;
                publish.mqttPublisher(command, topicDrivingDir);
            }
        });

        spL.setBounds(280, 280, 40, 20);
        frame.add(spL);

        speedDisplay.setBounds(277, 310, 50, 20);
        frame.add(speedDisplay);

        buttonSP.setBounds(220,305,42,42);
        frame.add(buttonSP);
        buttonSP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                speed++;
                String command = "G_"+Double.toString(speed);
                publish.mqttPublisher(command, topicSpeed);
                // speed = Double.parseDouble(subscrSpeed.getSpeed());
                speedDisplay.setText(Double.toString(speed)+" km/h");
            }
        });

        buttonSM.setBounds(340,305,40,40);
        frame.add(buttonSM);
        buttonSM.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                speed--;
                String command = "G_"+Double.toString(speed);
                publish.mqttPublisher(command, topicSpeed);
                // speed = Double.parseDouble(subscrSpeed.getSpeed());
                speedDisplay.setText(Double.toString(speed)+" km/h");
            }
        });

        steeringPosition.setBounds(255, 380, 100, 20);
        frame.add(steeringPosition);

        steeringPosAngle.setBounds(288, 410, 40, 20);
        frame.add(steeringPosAngle);

        buttonSPP.setBounds(220,405,42,42);
        frame.add(buttonSPP);
        buttonSPP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                steeringPos++;
                String command = "P_"+Double.toString(steeringPos);
                publish.mqttPublisher(command, topicSteeringPos);
                // steeringPos = Double.parseDouble(subscrSteeringPos.getSteeringPos());
                steeringPosAngle.setText(Double.toString(steeringPos)+"\u00B0");
            }
        });

        buttonSPM.setBounds(340,405,40,40);
        frame.add(buttonSPM);
        buttonSPM.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                steeringPos--;
                String command = "P_"+Double.toString(steeringPos);
                publish.mqttPublisher(command, topicSteeringPos);
                // steeringPos = Double.parseDouble(subscrSteeringPos.getSteeringPos());
                steeringPosAngle.setText(Double.toString(steeringPos)+"\u00B0");
            }
        });

        distanceToObstacle.setBounds(245, 480, 120, 20);
        frame.add(distanceToObstacle);

        // distToObstacle = Double.parseDouble(subscrDistToObstacle.getDistToObstacle());
        distanceToObstacleDisplay.setBounds(288, 510, 40, 20);
        frame.add(distanceToObstacleDisplay);

        buttonF.setBounds(260,580,80,40);
        frame.add(buttonF);
        buttonF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttonF.setBackground(Color.red);
                button0.setBackground(null);
                startenOderAufhören = "A";
                String commandStop = startenOderAufhören;
                publish.mqttPublisher(commandStop, topicStartenOderAufhören);
            }
        });
    }
}
