package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto", group = "AUTONOMOUS")
public class Auto extends LinearOpMode {
    DcMotor motorRightFront = null;
    DcMotor motorRightBack = null;
    DcMotor motorLeftFront = null;
    DcMotor motorLeftBack = null;
    DcMotor motorRight = null;
    DcMotor motorLeft = null;

    @Override
    public void runOpMode() throws InterruptedException {
        motorRightFront = hardwareMap.get(DcMotor.class, "Right Front Motor");
        motorRightBack = hardwareMap.get(DcMotor.class, "Right Back Motor");
        motorLeftFront = hardwareMap.get(DcMotor.class, "Left Front Motor");
        motorLeftBack = hardwareMap.get(DcMotor.class, "Left Back Motor");
        /*
         motorRight = hardwareMap.get(DcMotor.class, "Right Arm");
         motorLeft = hardwareMap.get(DcMotor.class, "Left Arm");
        */
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}