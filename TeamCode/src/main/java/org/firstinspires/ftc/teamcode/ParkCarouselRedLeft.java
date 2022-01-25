package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name = "Red Carousel Left-Side Park", group = "Red")
public class ParkCarouselRedLeft extends LinearOpMode {

    private DcMotor FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor;
    private CRServo CMotor1, CMotor2;

    HolonomicDrive holonomicDrive;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        FrontRightMotor = hardwareMap.get(DcMotor.class, "front_right_drive");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
        BackRightMotor = hardwareMap.get(DcMotor.class, "back_right_drive");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");
        CMotor1 = hardwareMap.get(CRServo.class, "carousel_motor_1");
        CMotor2 = hardwareMap.get(CRServo.class, "carousel_motor_2");

        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        holonomicDrive = new HolonomicDrive(FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor);

        waitForStart();

        // Move away from the wall
        runtime.reset();
        holonomicDrive.autoDrive(0, 0.6);
        while (opModeIsActive() && runtime.seconds() < 1) {}
        holonomicDrive.stopMoving();

        // Strafe towards the carousel
        runtime.reset();
        holonomicDrive.autoDrive(270, 0.6);
        while (opModeIsActive() && runtime.seconds() < 1) {}
        holonomicDrive.stopMoving();

        // Back into the carousel
        runtime.reset();
        holonomicDrive.autoDrive(180, 0.4);
        while (opModeIsActive() && runtime.seconds() < 1.75) {}
        holonomicDrive.stopMoving();

        // Spin the carousel
        runtime.reset();
        CMotor1.setPower(1);
        CMotor2.setPower(1);
        while (opModeIsActive() && runtime.seconds() < 10) {}

        CMotor1.setPower(0);
        CMotor2.setPower(0);


        // Drive into the parking zone
        // Assuming the robot will tilt 15 degrees when reversing into the carousel
        runtime.reset();
        holonomicDrive.autoDrive(345, 0.6);
        while (opModeIsActive() && runtime.seconds() < 2) {}
        holonomicDrive.stopMoving();




    }
}