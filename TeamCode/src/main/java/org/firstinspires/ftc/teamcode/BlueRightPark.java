package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;



@Autonomous(name = "Blue Right-Side Park", group = "Blue")
public class BlueRightPark extends LinearOpMode {

    private DcMotor FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor;

    HolonomicDrive holonomicDrive;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        FrontRightMotor = hardwareMap.get(DcMotor.class, "front_right_drive");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
        BackRightMotor = hardwareMap.get(DcMotor.class, "back_right_drive");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");

        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        holonomicDrive = new HolonomicDrive(FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor);

        waitForStart();

        // Drive forwards
        runtime.reset();
        holonomicDrive.autoDrive(50, 0.75);
        while (opModeIsActive() && runtime.seconds() < 1) {}
        holonomicDrive.stopMoving();


//        // Then strafe right into the parking zone
//        runtime.reset();
//        holonomicDrive.autoDrive(45, 0.5);
//        while (opModeIsActive() && runtime.seconds() < 1) {}
//        holonomicDrive.stopMoving();
//        runtime.reset();
        stop();


    }
}