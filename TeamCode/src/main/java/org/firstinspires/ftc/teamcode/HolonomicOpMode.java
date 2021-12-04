package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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
    private DcMotor  FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor, LiftMotor;
    private CRServo  LeftArm, RightArm, CMotor1, CMotor2;
    HolonomicDrive holonomicDrive;

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
        LiftMotor = hardwareMap.get(DcMotor.class, "lift_motor");
        LeftArm = hardwareMap.get(CrServo.class, "left_arm");
        RightArm = hardwareMap.get(CrServo.class, "right_arm");
        CMotor1 = hardwareMap.get(CrServo.class, "carousel_motor_1");
        CMotor2 = hardwareMap.get(CrServo.class, "carousel_motor_2");

        holonomicDrive = new HolonomicDrive(FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
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
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double z = gamepad1.right_stick_x;

        boolean Y_1Button = gamepad1.y;
        boolean YIsPressed = false;
        boolean X_1Button = gamepad1.x;
        boolean XIsPressed = false;
        boolean B_1Button = gamepad1.b;
        boolean BIsPressed = false;
        boolean A_1Button = gamepad1.a;
        boolean AIsPressed = false;
        boolean RB_1Button = gamepad1.right_bumper;
        boolean RBIsPressed = false;
        boolean LB_1Button = gamepad1.left_bumper;
        boolean LBIsPressed = false;
        boolean RT_1Button = gamepad1.right_trigger;
        boolean RTIsPressed = false;
        boolean LT_1Button = gamepad1.left_trigger;
        boolean RTIsPressed = false;
        if(Y_1Button == true && YIsPressed == false){
            YIsPressed = true;
            holonomicDrive.raiseArms();

        } else if(Y_1Button == true) {
            YIsPressed == false;
            holonomicDrive.resetArms();
        }
        if(A_1Button == true && AIsPressed == false){
            AIsPressed = true;
            holonomicDrive.lowerArms();

        } else if(Y_1Button == true) {
            AIsPressed == false;
            holonomicDrive.resetArms();
        }
        if(X_1Button == true && XIsPressed == false){
            XIsPressed = true;
            holonomicDrive.openBasket();

        } else if(Y_1Button == true) {
            XIsPressed == false;
        }
        if(B_1Button == true && BIsPressed == false){
            BIsPressed = true;
            holonomicDrive.closeBasket();

        } else if(Y_1Button == true) {
            BIsPressed == false;
        }
        if(RT_1Button == true && RTIsPressed == false){
            RTIsPressed = true;
            holonomicDrive.spinCarousel();

        } else if(Y_1Button == true) {
            RTIsPressed == false;
            holonomicDrive.stopSpinningCarousel();
        }
        holonomicDrive.teleopDrive(x,y,z);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}