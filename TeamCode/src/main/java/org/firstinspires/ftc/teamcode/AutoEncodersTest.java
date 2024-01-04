package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "AutoEncodersTest", group = "DEV")
public class AutoEncodersTest extends LinearOpMode {

    // CONSTANTS
    final double PI = 3.14159265359;
    final double WHEEL_DIAMETER_IN_MM = 100;
    final double TICKS_PER_SEC = 360; /* Angular velocity */
    final double INCH_TO_MM = 25.4;
    final double DEGREES_PER_MS = 0.16;

    // DERIVED CONSTANTS
    final double MM_PER_MS = (double) (TICKS_PER_SEC / 360_000.0 * WHEEL_DIAMETER_IN_MM * PI);
    final double MS_PER_MM = (double) (1.0 / MM_PER_MS);
    final double MS_PER_DEGREE = (double) (1.0 / DEGREES_PER_MS);

    DcMotorEx motorRightFront = null;
    //DcMotorEx motorRightBack = null;
    DcMotorEx motorLeftFront = null;
    DcMotorEx motorLeftBack = null;
    Rev2mDistanceSensor sensorLeft = null;
    Rev2mDistanceSensor sensorRight = null;
    RevColorSensorV3 sensorColour = null;
    @Override
    public void runOpMode() throws InterruptedException {
        motorRightFront = hardwareMap.get(DcMotorEx.class, "Right Front Motor");
        //motorRightBack = hardwareMap.get(DcMotorEx.class, "Right Back Motor");
        motorLeftFront = hardwareMap.get(DcMotorEx.class, "Left Front Motor");
        motorLeftBack = hardwareMap.get(DcMotorEx.class, "Left Back Motor");
        //sensorLeft = hardwareMap.get(Rev2mDistanceSensor.class, "Left Sensor");
        //sensorRight = hardwareMap.get(Rev2mDistanceSensor.class, "Right Sensor");
        //sensorColour = hardwareMap.get(RevColorSensorV3.class, "Colour Sensor");

        motorLeftBack.setDirection(DcMotorEx.Direction.REVERSE);
        motorLeftFront.setDirection(DcMotorEx.Direction.REVERSE);
        motorRightFront.setDirection(DcMotorEx.Direction.FORWARD);
        //motorRightBack.setDirection(DcMotorEx.Direction.FORWARD);

        motorLeftBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        //motorRightBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        motorLeftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorLeftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //motorRightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            /*

            double distLeft = sensorLeft.getDistance(DistanceUnit.MM);
            double distRight = sensorRight.getDistance(DistanceUnit.MM);

            if(distLeft < 400) {
                move(Direction.RIGHT, 500);
                sleep(1000);
                move(Direction.FORWARD, 500);
                break;
            }

            int x = sensorColour.blue();

            backstage(Team.BLUE, StartingPosition.BACKSTAGE);

            */

            motorRightFront.setVelocity(100);
            motorLeftFront.setVelocity(100);
            motorLeftBack.setVelocity(100);
            sleep(1000);
            motorRightFront.setVelocity(200);
            motorLeftFront.setVelocity(200);
            motorLeftBack.setVelocity(200);
            sleep(1000);
            motorRightFront.setVelocity(300);
            motorLeftFront.setVelocity(300);
            motorLeftBack.setVelocity(300);
            sleep(1000);
            motorRightFront.setVelocity(0);
            motorLeftFront.setVelocity(0);
            motorLeftBack.setVelocity(0);
            sleep(1000);
        }
    }

    private enum Direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    private enum Rotation {
        CLOCKWISE,
        COUNTERCLOCKWISE
    }

    private enum Team {
        BLUE,
        RED
    }

    private enum StartingPosition {
        BACKSTAGE,
        PUBLIC
    }

    private void backstage(Team team, StartingPosition position) {
        move(Direction.FORWARD, 24 /* INCHES */ * INCH_TO_MM);
        if(team == Team.BLUE) {
            move(Direction.LEFT, 48 /* INCHES */ * INCH_TO_MM);
            if(position == StartingPosition.PUBLIC) {
                move(Direction.LEFT, 48 /* INCHES */ * INCH_TO_MM);
            }
        }
        if(team == Team.RED) {
            move(Direction.RIGHT, 48 /* INCHES */ * INCH_TO_MM);
            if(position == StartingPosition.PUBLIC) {
                move(Direction.RIGHT, 48 /* INCHES */ * INCH_TO_MM);
            }
        }
    }

    private void move(Direction direction, double millimeters) {
        switch (direction) {
            case FORWARD: {
                telemetry.addData("Status", "Moving forward");
                telemetry.update();
                //motorLeftBack.setVelocity(TICKS_PER_SEC);
                //motorLeftFront.setVelocity(TICKS_PER_SEC);
                motorRightFront.setVelocity(TICKS_PER_SEC);
                //motorRightBack.setVelocity(TICKS_PER_SEC);
                break;
            }
            case BACKWARD: {
                telemetry.addData("Status", "Moving backward");
                telemetry.update();
                //motorLeftBack.setVelocity(-TICKS_PER_SEC);
                //motorLeftFront.setVelocity(-TICKS_PER_SEC);
                motorRightFront.setVelocity(-TICKS_PER_SEC);
                //motorRightBack.setVelocity(-TICKS_PER_SEC);
                break;
            }
            case LEFT: {
                telemetry.addData("Status", "Moving left");
                telemetry.update();
                //motorLeftBack.setVelocity(TICKS_PER_SEC);
                //motorLeftFront.setVelocity(-TICKS_PER_SEC);
                motorRightFront.setVelocity(TICKS_PER_SEC);
                //motorRightBack.setVelocity(-TICKS_PER_SEC);
                break;
            }
            case RIGHT: {
                telemetry.addData("Status", "Moving right");
                telemetry.update();
                //motorLeftBack.setVelocity(-TICKS_PER_SEC);
                //motorLeftFront.setVelocity(TICKS_PER_SEC);
                motorRightFront.setVelocity(-TICKS_PER_SEC);
                //motorRightBack.setVelocity(TICKS_PER_SEC);
                break;
            }
        }
        if(millimeters >= 0) {
            sleep((long) (millimeters * MS_PER_MM));
        }
        //motorLeftBack.setVelocity(0);
        //motorLeftFront.setVelocity(0);
        motorRightFront.setVelocity(0);
        //motorRightBack.setVelocity(0);
        sleep(100);
    }

    private void rotate(Rotation rotation, double degrees) {
        switch (rotation) {
            case CLOCKWISE: {
                telemetry.addData("Rotation", "Clockwise");
                telemetry.update();
                //motorLeftBack.setVelocity(TICKS_PER_SEC);
                //motorLeftFront.setVelocity(TICKS_PER_SEC);
                motorRightFront.setVelocity(-TICKS_PER_SEC);
                //motorRightBack.setVelocity(-TICKS_PER_SEC);
                break;
            }
            case COUNTERCLOCKWISE: {
                telemetry.addData("Rotation", "Clockwise");
                telemetry.update();
                //motorLeftBack.setVelocity(-TICKS_PER_SEC);
                //motorLeftFront.setVelocity(-TICKS_PER_SEC);
                motorRightFront.setVelocity(TICKS_PER_SEC);
                //motorRightBack.setVelocity(TICKS_PER_SEC);
                break;
            }
        }
        sleep((long) (degrees * MS_PER_DEGREE));
    }

}