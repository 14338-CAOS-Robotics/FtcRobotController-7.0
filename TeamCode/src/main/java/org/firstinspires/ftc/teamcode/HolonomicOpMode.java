package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Holonomic OpMode", group="Iterative Opmode")
//@Disabled
public class HolonomicOpMode extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor  FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor;
    private DcMotorEx LiftMotor;
    private CRServo CMotor1, CMotor2, intakeServo;
    private int[] liftPos = {0, 475, 1150, 2050, 2190, 2500, 2900};
    private int currentLiftPosition = 0;
    private Servo LeftArm, RightArm, cappingMotor;
    HolonomicDrive holonomicDrive;
    boolean YIsPressed = false;
    boolean XIsPressed = false;
    boolean BIsPressed = false;
    boolean AIsPressed = false;
    boolean RBIsPressed = false;
    boolean LBIsPressed = false;
    boolean DPADLeftIsPressed = false;
    boolean DPADRightIsPressed = false;
    boolean LTIsPressed = false;
    boolean RTIsPressed = false;
    boolean BackIsPressed = false;
    boolean closed = false;
    boolean up = false;

    boolean B2IsPressed = false;
    boolean LB2IsPressed = false;
    boolean RB2IsPressed = false;
    boolean X2IsPressed = false;
    private boolean Y2IsPressed = false;
    private boolean A2IsPressed = false;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        FrontRightMotor  = hardwareMap.get(DcMotor.class, "front_right_drive");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
        BackRightMotor  = hardwareMap.get(DcMotor.class, "back_right_drive");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");
        LiftMotor = hardwareMap.get(DcMotorEx.class, "lift_motor");
        LeftArm = hardwareMap.get(Servo.class, "left_arm");
        RightArm = hardwareMap.get(Servo.class, "right_arm");
        intakeServo = hardwareMap.get(CRServo.class, "intake_servo");
        CMotor1 = hardwareMap.get(CRServo.class, "carousel_motor_1");
        CMotor2 = hardwareMap.get(CRServo.class, "carousel_motor_2");
        cappingMotor = hardwareMap.get(Servo.class, "capping_servo");

        holonomicDrive = new HolonomicDrive(FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        LiftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        LiftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        //LiftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        LiftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        //LiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double x = -gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double z = -gamepad1.right_stick_x;
        double RightJoyY1 = -gamepad1.right_stick_y;

        boolean B_1Button = gamepad1.b;
        boolean A_1Button = gamepad1.a;
        boolean RB_1Button = gamepad1.right_bumper;
        boolean X_1Button = gamepad1.x;
        boolean Y_1Button = gamepad1.y;
        boolean LB_1Button = gamepad1.left_bumper;
        boolean DPL_1Button = gamepad1.dpad_left;
        boolean DPR_1Button = gamepad1.dpad_right;
        boolean backButton1 = gamepad1.back;

        boolean B_2Button = gamepad2.b;
        boolean A_2Button = gamepad2.a;
        boolean RB_2Button = gamepad2.right_bumper;
        boolean X_2Button = gamepad2.x;
        boolean Y_2Button = gamepad2.y;
        boolean LB_2Button = gamepad2.left_bumper;
        boolean backButton2 = gamepad1.back;



        // Raise Arms or Lower Arms
//        if(Y_1Button == true && YIsPressed == false){
//            YIsPressed = true;
//            holonomicDrive.raiseArms();
//
//        } else if(Y_1Button == true) {
//            YIsPressed == false;
//            holonomicDrive.resetArms();
//        }
//        if(A_1Button == true && AIsPressed == false){
//            AIsPressed = true;
//            holonomicDrive.lowerArms();
//
//        } else if(Y_1Button == true) {
//            AIsPressed == false;
//            holonomicDrive.resetArms();
//        }

        // Control arms holding the element

        // Open wheels and close wheels
        if(B_2Button == true && closed == false && B2IsPressed == false){
            closed = true;
            B2IsPressed = true;
            LeftArm.setPosition(0.5);
            RightArm.setPosition(0);
        }
        else if(B_2Button == false){
            B2IsPressed = false;
        }
        if(B_2Button == true && closed == true && B2IsPressed == false){
            closed = false;
            B2IsPressed = true;
            LeftArm.setPosition(0);
            RightArm.setPosition(0.5);
        }
        else if(B_1Button == false){
            B2IsPressed = false;
        }
        //lift capping servo
//        if(DPADLeftIsPressed) {
//
//        }
        //Close
        if(X_1Button == true && up == false && XIsPressed == false) {
            XIsPressed = true;
            up = true;
            cappingMotor.setPosition(0.435);
        }
        else if(X_1Button == false) {
            XIsPressed = false;
        }
        if(X_1Button == true && up == true && XIsPressed == false) {
            XIsPressed = true;
            up = false;
            cappingMotor.setPosition(0);
        }
        else if(X_1Button == false) {
            XIsPressed = false;
        }

        //spins the carousel servos
        if(LB_2Button == true && LB2IsPressed == false){
            LB2IsPressed = true;
            CMotor2.setPower(0.5);
            CMotor1.setPower(-0.5);

        }
        else if(LB_2Button == false){
            LB2IsPressed = false;
        }
        if(RB_2Button == true && RB2IsPressed == false) {
            RB2IsPressed = true;
            CMotor2.setPower(0);
            CMotor1.setPower(0);
        }
        else if(RB_2Button == false) {
            RB2IsPressed = false;
        }


        //spins the intake motor to either intake items, or spits out items
        if(Y_2Button == true && Y2IsPressed == false) {
            Y2IsPressed = true;
            intakeServo.setPower(0.5);
        }
        else if(Y_2Button == false) {
            Y2IsPressed = false;
        }
        if(A_2Button == true && A2IsPressed == false) {
            A2IsPressed = true;
            intakeServo.setPower(-0.5);
        }
        else if(A_2Button == false) {
            A2IsPressed = false;
        }
        if(X_2Button == true && X2IsPressed == false) {
            X2IsPressed = false;
            intakeServo.setPower(0);
        }
        else if(X_2Button == false) {
        }



        // Make the lift go UP
        if(Y_1Button && !YIsPressed && currentLiftPosition < (liftPos.length - 1)){
            YIsPressed = true;
            currentLiftPosition += 1;
            LiftMotor.setTargetPosition(liftPos[currentLiftPosition]);
            LiftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            LiftMotor.setPower(0.5);

        } else if(!Y_1Button) {
            YIsPressed = false;

        }

        //Make the lift go down
        if(A_1Button && !AIsPressed && currentLiftPosition > 0){
            AIsPressed = true;
            currentLiftPosition -= 1;
            LiftMotor.setTargetPosition(liftPos[currentLiftPosition]);
            LiftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            LiftMotor.setPower(-0.5);

        } else if(!A_1Button) {
            AIsPressed = false;
        }

        //make lift go to pos 0
        if(backButton1 && !BackIsPressed && currentLiftPosition > 0){
            BackIsPressed = true;
            currentLiftPosition = 0;
            LiftMotor.setTargetPosition(liftPos[currentLiftPosition]);
            LiftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            LiftMotor.setPower(-0.5);

        } else if(!backButton1) {
            BackIsPressed = false;
        }


        //failsafe to test if encoder control is or isn't working
/*    if(RightJoyY1 > 0) {
        LiftMotor.setTargetPosition(100000);
        LiftMotor.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        LiftMotor.setPower(RightJoyY1);
    }
    else if(RightJoyY1 < 0){
        LiftMotor.setTargetPosition(-10000);
        LiftMotor.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        LiftMotor.setPower(RightJoyY1);
    }*/


        holonomicDrive.teleopDrive(x,y,z);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addLine("Current Lift position: " + currentLiftPosition + "\nCurrent Lift Ticks: " + liftPos[currentLiftPosition]);
        telemetry.addLine("Current Lift Motor Ticks: "  + LiftMotor.getCurrentPosition());
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}