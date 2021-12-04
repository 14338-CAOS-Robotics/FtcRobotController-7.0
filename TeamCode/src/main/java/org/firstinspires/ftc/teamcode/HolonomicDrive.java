package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;

public class HolonomicDrive {
    String motorRotationDirection;
    DcMotor FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor;

    public HolonomicDrive(DcMotor FrontRight, DcMotor FrontLeft, DcMotor BackRight, DcMotor BackLeft){
        motorRotationDirection = "CLOCKWISE";
        FrontRightMotor = FrontRight;
        FrontLeftMotor = FrontLeft;
        BackRightMotor = BackRight;
        BackLeftMotor = BackLeft;
    }

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

    public void setMotorRotationDirection(String motorRotationDirection) {
        this.motorRotationDirection = motorRotationDirection;
    }

    public String getMotorRotationDirection() {
        return motorRotationDirection;
    }

    public void teleopDrive(double x, double y, double z){
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

    public void autoDrive(double directionDegrees, double maxSpeed){
        double plusX = (((Math.PI)/4.0) + ((Math.PI)/180 * directionDegrees));
        double minusX = (((Math.PI)/4.0) - ((Math.PI)/180 * directionDegrees)); //(((Math.PI)/4.0) + ((Math.PI)/180 * directionDegrees))
        double FrontRightMotorPower = maxSpeed * Math.cos(plusX);
        double FrontLeftMotorPower = -maxSpeed * Math.cos(minusX);
        double BackRightMotorPower = maxSpeed * Math.cos(minusX);
        double BackLeftMotorPower = -maxSpeed * Math.cos(plusX);

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
