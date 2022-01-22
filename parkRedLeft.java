@Autonomous(name = "park red left", group = "Concept")
public class tensorFlowAutoRight extends LinearOpMode {

    private DcMotor FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor;
    private DcMotorEx LiftMotor;
    private CRServo CMotor1, CMotor2, intakeServo;

    private Servo LeftArm, RightArm;
    Orientation lastAngles = new Orientation();
    HolonomicDrive holonomicDrive;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        FrontRightMotor = hardwareMap.get(DcMotor.class, "front_right_drive");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
        BackRightMotor = hardwareMap.get(DcMotor.class, "back_right_drive");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");
        LiftMotor = hardwareMap.get(DcMotorEx.class, "lift_motor");
        LeftArm = hardwareMap.get(Servo.class, "left_arm");
        RightArm = hardwareMap.get(Servo.class, "right_arm");
        intakeServo = hardwareMap.get(CRServo.class, "intake_servo");
        LiftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        LiftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        //LiftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        LiftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        holonomicDrive = new HolonomicDrive(FrontRightMotor, FrontLeftMotor, BackRightMotor, BackLeftMotor);

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                runtime.reset();
                holonomicDrive.autoDrive(0, 0.5);
                while (opModeIsActive() && runtime.seconds() < 1) {}
                holonomicDrive.stopMoving();
                runtime.reset();

                holonomicDrive.autoDrive(90, 0.5);
                while (opModeIsActive() && runtime.seconds() < 1) {}
                holonomicDrive.stopMoving();
                runtime.reset();
            }
        }
    }
}