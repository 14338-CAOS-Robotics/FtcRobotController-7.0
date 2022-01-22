package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;

//This year we used a holonomic drive in order to be able to move in all directions.
//To look at all the math caclulations that contributed to the design of the code, check out the Engineering Notebook Section.

public class HolonomicDrive {

    //Set up Hardware Devices and Device Rotation Direction
    String motorRotationDirection;
    DcMotor FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor;
    //Motor Speed Variable for the slower setting
    double slow_speed = 0.1; //0.5
   // double top_speed = 1.0;

    //Basic Constructor to Set-Up the Holonomic Drive that sets the motor direction to clockwise.
    public HolonomicDrive(DcMotor FrontRight, DcMotor FrontLeft, DcMotor BackRight, DcMotor BackLeft){
        motorRotationDirection = "CLOCKWISE";
        FrontRightMotor = FrontRight;
        FrontLeftMotor = FrontLeft;
        BackRightMotor = BackRight;
        BackLeftMotor = BackLeft;
    }


//Constructor for Setting the MotorDirection
    //This one will set the motor direction to either "COUNTER-CLOCKWISE" or "CLOCKWISE"
    public HolonomicDrive(String motorDirection, DcMotor FrontRight, DcMotor FrontLeft, DcMotor BackRight, DcMotor BackLeft){
        if(motorDirection.equals("COUNTER-CLOCKWISE")){
            motorRotationDirection = "COUNTER-CLOCKWISE";
            FrontRightMotor = FrontRight;
            FrontLeftMotor = FrontLeft;
            BackRightMotor = BackRight;
            BackLeftMotor = BackLeft;

        }
        else {//"CLOCKWISE"
            motorRotationDirection = "CLOCKWISE";
            FrontRightMotor = FrontRight;
            FrontLeftMotor = FrontLeft;
            BackRightMotor = BackRight;
            BackLeftMotor = BackLeft;

        }
    }

    //If for some reason, you need to change the motor rotation direction
    public void setMotorRotationDirection(String motorRotationDirection) {
        this.motorRotationDirection = motorRotationDirection;
    }
// If you need to get the Motor Rotation Direction
    public String getMotorRotationDirection() {
        return motorRotationDirection;
    }

    public void teleopDrive(double x, double y, double z){
        if(this.getMotorRotationDirection().equals("CLOCKWISE")){
            double FrontRightMotorPower = Range.clip( (x - y + z), -0.6, 0.6);
            double FrontLeftMotorPower = Range.clip( (x + y + z), -0.6, 0.6);
            double BackRightMotorPower = Range.clip( (-x - y + z), -0.6, 0.6);
            double BackLeftMotorPower = Range.clip( (-x + y + z), -0.6, 0.6);

            FrontRightMotor.setPower(FrontRightMotorPower);
            FrontLeftMotor.setPower(FrontLeftMotorPower);
            BackRightMotor.setPower(BackRightMotorPower);
            BackLeftMotor.setPower(BackLeftMotorPower);
        }
        else{//COUNTER-CLOCKWISE
            double FrontRightMotorPower = Range.clip( -(x - y + z), -0.6, 0.6);
            double FrontLeftMotorPower = Range.clip( -(x + y + z), -0.6, 0.6);
            double BackRightMotorPower = Range.clip( -(-x - y + z), -0.6, 0.6);
            double BackLeftMotorPower = Range.clip( -(-x + y + z), -0.6, 0.6);

            FrontRightMotor.setPower(FrontRightMotorPower);
            FrontLeftMotor.setPower(FrontLeftMotorPower);
            BackRightMotor.setPower(BackRightMotorPower);
            BackLeftMotor.setPower(BackLeftMotorPower);


        }
    }
    public void teleopDrive(double x, double y, double z, double slow_trigger){
        if(this.getMotorRotationDirection().equals("CLOCKWISE")){
            double FrontRightMotorPower = Range.clip( (x - y + z), -1.0, 1.0);
            double FrontLeftMotorPower = Range.clip( (x + y + z), -1.0, 1.0);
            double BackRightMotorPower = Range.clip( (-x - y + z), -1.0, 1.0);
            double BackLeftMotorPower = Range.clip( (-x + y + z), -1.0, 1.0);

            FrontRightMotor.setPower(FrontRightMotorPower);
            FrontLeftMotor.setPower(FrontLeftMotorPower);
            BackRightMotor.setPower(BackRightMotorPower);
            BackLeftMotor.setPower(BackLeftMotorPower);
        }
        else{//COUNTER-CLOCKWISE
            double FrontRightMotorPower = Range.clip( -(x - y + z), -1.0, 1.0);
            double FrontLeftMotorPower = Range.clip( -(x + y + z), -1.0, 1.0);
            double BackRightMotorPower = Range.clip( -(-x - y + z), -1.0, 1.0);
            double BackLeftMotorPower = Range.clip( -(-x + y + z), -1.0, 1.0);

            FrontRightMotor.setPower(FrontRightMotorPower);
            FrontLeftMotor.setPower(FrontLeftMotorPower);
            BackRightMotor.setPower(BackRightMotorPower);
            BackLeftMotor.setPower(BackLeftMotorPower);
        }
       if (slow_trigger>0){
           if(this.getMotorRotationDirection().equals("CLOCKWISE")){
               double FrontRightMotorPower = Range.clip( (x - y + z), -slow_speed , slow_speed);
               double FrontLeftMotorPower = Range.clip( (x + y + z), -slow_speed , slow_speed);
               double BackRightMotorPower = Range.clip( (-x - y + z), -slow_speed , slow_speed);
               double BackLeftMotorPower = Range.clip( (-x + y + z), -slow_speed , slow_speed);

               FrontRightMotor.setPower(FrontRightMotorPower);
               FrontLeftMotor.setPower(FrontLeftMotorPower);
               BackRightMotor.setPower(BackRightMotorPower);
               BackLeftMotor.setPower(BackLeftMotorPower);
           }
           else{//COUNTER-CLOCKWISE
               double FrontRightMotorPower = Range.clip( -(x - y + z), -slow_speed , slow_speed);
               double FrontLeftMotorPower = Range.clip( -(x + y + z), -slow_speed , slow_speed);
               double BackRightMotorPower = Range.clip( -(-x - y + z), -slow_speed , slow_speed);
               double BackLeftMotorPower = Range.clip( -(-x + y + z), -slow_speed , slow_speed);

               FrontRightMotor.setPower(FrontRightMotorPower);
               FrontLeftMotor.setPower(FrontLeftMotorPower);
               BackRightMotor.setPower(BackRightMotorPower);
               BackLeftMotor.setPower(BackLeftMotorPower);
           }
       }
    }


    public void teleopDriveDEMO(double x, double y, double z){
        if(this.getMotorRotationDirection().equals("CLOCKWISE")){
            double FrontRightMotorPower = Range.clip( (x - y + z), -0.6, 0.6);
            double FrontLeftMotorPower = Range.clip( (x + y + z),-0.6, 0.6);
            double BackRightMotorPower = Range.clip( (-x - y + z), -0.6, 0.6);
            double BackLeftMotorPower = Range.clip( (-x + y + z), -0.6, 0.6);

            FrontRightMotor.setPower(FrontRightMotorPower);
            FrontLeftMotor.setPower(FrontLeftMotorPower);
            BackRightMotor.setPower(BackRightMotorPower);
            BackLeftMotor.setPower(BackLeftMotorPower);
        }
        else{//COUNTER-CLOCKWISE
            double FrontRightMotorPower = Range.clip( -(x - y + z), -0.6, 0.6);
            double FrontLeftMotorPower = Range.clip( -(x + y + z), -0.6, 0.6);
            double BackRightMotorPower = Range.clip( -(-x - y + z), -0.6, 0.6);
            double BackLeftMotorPower = Range.clip( -(-x + y + z), -0.6, 0.6);

            FrontRightMotor.setPower(FrontRightMotorPower);
            FrontLeftMotor.setPower(FrontLeftMotorPower);
            BackRightMotor.setPower(BackRightMotorPower);
            BackLeftMotor.setPower(BackLeftMotorPower);


        }
    }

    //Look at Software Section of Engineering Notebook to see how the Math was found.
    public void autoDrive(double directionDegrees, double maxSpeed){
        double plusX = (((Math.PI)/4.0) + ((Math.PI)/180 * directionDegrees));
        double minusX = (((Math.PI)/4.0) - ((Math.PI)/180 * directionDegrees)); //(((Math.PI)/4.0) + ((Math.PI)/180 * directionDegrees))
        double FrontRightMotorPower = -maxSpeed * Math.cos(plusX);
        double FrontLeftMotorPower = maxSpeed * Math.cos(minusX);
        double BackRightMotorPower = -maxSpeed * Math.cos(minusX);
        double BackLeftMotorPower = maxSpeed * Math.cos(plusX);

        FrontRightMotor.setPower(FrontRightMotorPower);
        FrontLeftMotor.setPower(FrontLeftMotorPower);
        BackRightMotor.setPower(BackRightMotorPower);
        BackLeftMotor.setPower(BackLeftMotorPower);
    }




    public void stopMoving(){
        FrontRightMotor.setPower(0);
        FrontLeftMotor.setPower(0);
        BackRightMotor.setPower(0);
        BackLeftMotor.setPower(0);
    }


    }

