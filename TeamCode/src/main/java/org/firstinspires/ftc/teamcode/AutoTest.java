/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name=" Auto", group="Pushbot")
@Disabled
public class AutoTest extends LinearOpMode {

    private int[] liftPos = {0, 475, 1150, 2050, 6000, 8000, 10000};
    int objPosition = 0;
    double leftBound = 240;
    double rightBound = 480;
    double leftPos = 0;
    boolean inLeft = false;
    boolean inMiddle = false;
    boolean inRight = false;
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor;
    private DcMotorEx LiftMotor;
    private CRServo CMotor1, CMotor2, intakeServo;

    private Servo LeftArm, RightArm;
    BNO055IMU imu;
    Orientation lastAngles = new Orientation();
    HolonomicDrive holonomicDrive;
    gyro Gyro;


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        FrontRightMotor = hardwareMap.get(DcMotor.class, "front_right_drive");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
        BackRightMotor = hardwareMap.get(DcMotor.class, "back_right_drive");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");
        LiftMotor = hardwareMap.get(DcMotorEx.class, "lift_motor");
        LeftArm = hardwareMap.get(Servo.class, "left_arm");
        RightArm = hardwareMap.get(Servo.class, "right_arm");
        intakeServo = hardwareMap.get(CRServo.class, "intake_servo");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        LiftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        LiftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        //LiftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        LiftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.loggingEnabled = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        // Setting our holonomic drive to use our 2 front and 2 back motors
        holonomicDrive = new HolonomicDrive(FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor);
        Gyro = new gyro(FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor, imu);


        waitForStart();

        // Create and store variable of its position with if statements
        if (leftPos < leftBound) {
            objPosition = 0;
        } else if (rightBound > leftPos) {
            objPosition = 2;
        } else if (leftPos > leftBound && rightBound < leftPos) {
            objPosition = 1;
        }
        // Execute movements
        runtime.reset();
        holonomicDrive.autoDrive(180, 0.5);
        while (opModeIsActive() && runtime.seconds() < 2) {

        }
        holonomicDrive.stopMoving();
        runtime.reset();


          //Gyro.rotate(-45, 0.3);
          telemetry.addData("Gyro ", Gyro.imu.getAngularOrientation());
          telemetry.update();

        FrontLeftMotor.setPower(-0.5);
        FrontRightMotor.setPower(-0.5);
        BackLeftMotor.setPower(-0.5);
        BackRightMotor.setPower(-0.5);
        while (opModeIsActive() && runtime.seconds() < 0.85) {

        }
        holonomicDrive.stopMoving();


        // Move Lift
        //if (inLeft) {
        LiftMotor.setTargetPosition(liftPos[3]);
        LiftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        LiftMotor.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2) {
        }
        //}
        //Deploy

        intakeServo.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 4) {
        }
        intakeServo.setPower(0);


        runtime.reset();
        holonomicDrive.autoDrive(270, 0.5);
        while (opModeIsActive() && runtime.seconds() < 1.75) {

        }
        holonomicDrive.stopMoving();
        sleep(500);

        runtime.reset();
        holonomicDrive.autoDrive(0, 0.9);
        while (opModeIsActive() && runtime.seconds() < 2) {

        }
        holonomicDrive.stopMoving();
        //LiftMotor.setPower(0);

        // Drive to the freight tower


        /** Now just make a test loop to lift the LiftMotor at 0.3 power for 0.5 seconds */


        // Release your cube


        // And park
    }
}