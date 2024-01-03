package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoServoTest", group = "TEST")
public class AutoServoTest extends LinearOpMode {
    Servo myServo = null;
    CRServo myCRServo = null;
    @Override
    public void runOpMode() throws InterruptedException {
        myServo = hardwareMap.get(Servo.class, "My Servo");
        myCRServo = hardwareMap.get(CRServo.class, "My CR Servo");

        myServo.setDirection(Servo.Direction.FORWARD);
        myServo.setPosition(0);
        myCRServo.setDirection(CRServo.Direction.FORWARD);
        myCRServo.setPower(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            myServo.setPosition(1);
            myCRServo.setPower(1);
            sleep(2000);
            myServo.setPosition(0);
            myCRServo.setPower(0);
            sleep(2000);
        }
    }
}