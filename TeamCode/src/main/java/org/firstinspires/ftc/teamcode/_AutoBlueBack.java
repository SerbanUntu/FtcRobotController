package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "_AutoBlueBack")
public class _AutoBlueBack extends LinearOpMode {

    DcMotor motorRightFront = null;
    DcMotor motorLeftFront = null;
    DcMotor motorLeftBack = null;
    DcMotor motorRightBack = null;
    Rev2mDistanceSensor sensorLeft = null;
    Rev2mDistanceSensor sensorRight = null;
    RevColorSensorV3 sensorColour = null;
    @Override
    public void runOpMode() throws InterruptedException {

        motorLeftFront = hardwareMap.get(DcMotor.class, "Left Front Motor");
        motorRightFront = hardwareMap.get(DcMotor.class, "Right Front Motor");
        motorLeftBack = hardwareMap.get(DcMotor.class, "Left Back Motor");
        motorRightBack = hardwareMap.get(DcMotor.class, "Right Back Motor");

        motorLeftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLeftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            move(Direction.FORWARD, 1500, 1);
            sleep(30000);
        }
    }
    public enum Direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    public enum Rotation {
        CLOCKWISE,
        COUNTERCLOCKWISE
    }

    public enum Team {
        BLUE,
        RED
    }

    public enum StartingLocation {
        BACKSTAGE,
        PUBLIC
    }

    public void move(Direction direction, long milliseconds, double power) {
        switch (direction) {
            case FORWARD: {
                telemetry.addData("Moving", "Forward");
                telemetry.update();
                motorLeftBack.setPower(power);
                motorLeftFront.setPower(power);
                motorRightFront.setPower(power);
                motorRightBack.setPower(power);
                break;
            }
            case BACKWARD: {
                telemetry.addData("Moving", "Backward");
                telemetry.update();
                motorLeftBack.setPower(-power);
                motorLeftFront.setPower(-power);
                motorRightFront.setPower(-power);
                motorRightBack.setPower(-power);
                break;
            }
            case LEFT: {
                telemetry.addData("Moving", "Left");
                telemetry.update();
                motorLeftBack.setPower(power);
                motorLeftFront.setPower(-power);
                motorRightFront.setPower(power);
                motorRightBack.setPower(-power);
                break;
            }
            case RIGHT: {
                telemetry.addData("Moving", "Right");
                telemetry.update();
                motorLeftBack.setPower(-power);
                motorLeftFront.setPower(power);
                motorRightFront.setPower(-power);
                motorRightBack.setPower(power);
                break;
            }
        }
        sleep(milliseconds);
        motorLeftBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
        motorRightBack.setPower(0);
    }

    public void rotate(Rotation rotation, long milliseconds, double power) {
        switch (rotation) {
            case CLOCKWISE: {
                telemetry.addData("Rotating", "Clockwise");
                telemetry.update();
                motorLeftBack.setPower(power);
                motorLeftFront.setPower(power);
                motorRightFront.setPower(-power);
                motorRightBack.setPower(-power);
                break;
            }
            case COUNTERCLOCKWISE: {
                telemetry.addData("Rotating", "Counterclockwise");
                telemetry.update();
                motorLeftBack.setPower(-power);
                motorLeftFront.setPower(-power);
                motorRightFront.setPower(power);
                motorRightBack.setPower(power);
                break;
            }
        }
        sleep(milliseconds);
        motorLeftBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
        motorRightBack.setPower(0);
    }
}