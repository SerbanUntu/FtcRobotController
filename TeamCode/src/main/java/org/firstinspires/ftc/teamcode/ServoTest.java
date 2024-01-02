package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "ServoTest", group = "TEST")
public class ServoTest extends LinearOpMode {
    Servo myServo = null;
    CRServo myCRServo = null;
    @Override
    public void runOpMode() throws InterruptedException {
        myServo = hardwareMap.get(Servo.class, "My Servo");
        myCRServo = hardwareMap.get(CRServo.class, "My CR Servo");
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        myServo.setDirection(Servo.Direction.FORWARD);
        myServo.setPosition(0);
        myCRServo.setDirection(CRServo.Direction.FORWARD);
        myCRServo.setPower(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);
            telemetry.addData("Status", "Running");
            telemetry.update();

            /*
            myServo.setPosition(1);
            myCRServo.setPower(1);
            sleep(2000);
            myServo.setPosition(0);
            myCRServo.setPower(0);
            sleep(2000);
            */

            if(currentGamepad1.a) {
                myServo.setPosition(myServo.getPosition() + 0.1);
            }
            if(currentGamepad1.b) {
                myServo.setPosition(myServo.getPosition() - 0.1);
            }
        }
    }
}