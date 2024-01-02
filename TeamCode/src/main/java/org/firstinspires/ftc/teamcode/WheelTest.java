package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@Autonomous(name = "WheelTest", group = "AUTONOMOUS")
public class WheelTest extends LinearOpMode {
    DcMotor motorRightFront = null;
    DcMotor motorRightBack = null;
    DcMotor motorLeftFront = null;
    DcMotor motorLeftBack = null;
    @Override
    public void runOpMode() throws InterruptedException {
        motorRightFront = hardwareMap.get(DcMotor.class, "Right Front Motor");
        motorRightBack = hardwareMap.get(DcMotor.class, "Right Back Motor");
        motorLeftFront = hardwareMap.get(DcMotor.class, "Left Front Motor");
        motorLeftBack = hardwareMap.get(DcMotor.class, "Left Back Motor");

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);
        }
    }
}