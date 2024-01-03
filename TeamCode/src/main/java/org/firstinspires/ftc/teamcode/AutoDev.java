package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "AutoDev", group = "DEV")
public class AutoDev extends LinearOpMode {
    final double VELOCITY = 62.5;
    DcMotor motorRightFront = null;
    DcMotor motorRightBack = null;
    DcMotor motorLeftFront = null;
    DcMotor motorLeftBack = null;
    Rev2mDistanceSensor sensorLeft = null;
    Rev2mDistanceSensor sensorRight = null;
    RevColorSensorV3 sensorColour = null;
    @Override
    public void runOpMode() throws InterruptedException {
        motorRightFront = hardwareMap.get(DcMotor.class, "Right Front Motor");
        motorRightBack = hardwareMap.get(DcMotor.class, "Right Back Motor");
        motorLeftFront = hardwareMap.get(DcMotor.class, "Left Front Motor");
        motorLeftBack = hardwareMap.get(DcMotor.class, "Left Back Motor");
        sensorLeft = hardwareMap.get(Rev2mDistanceSensor.class, "Left Sensor");
        sensorRight = hardwareMap.get(Rev2mDistanceSensor.class, "Right Sensor");
        sensorColour = hardwareMap.get(RevColorSensorV3.class, "Colour Sensor");

        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);

        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            int x = sensorColour.blue();

            backstage(Team.BLUE, StartingPosition.BACKSTAGE);
        }
    }

    private enum Direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
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
        move(Direction.LEFT, -1);
        while(opModeIsActive()) {
            double distLeft = sensorLeft.getDistance(DistanceUnit.CM);
            double distRight = sensorRight.getDistance(DistanceUnit.CM);

            if(distLeft < 40) {
                move(Direction.RIGHT, 50);
                sleep(1000);
                move(Direction.FORWARD, 50);
                break;
            }
        }
        move(Direction.FORWARD, 20);
        if(team == Team.BLUE) {
            move(Direction.RIGHT, 100);
            if(position == StartingPosition.PUBLIC) {
                move(Direction.RIGHT, 100);
            }
        }
        if(team == Team.RED) {
            move(Direction.LEFT, 100);
            if(position == StartingPosition.PUBLIC) {
                move(Direction.LEFT, 100);
            }
        }
    }

    private void move(Direction direction, long centimeters) {
        switch (direction) {
            case FORWARD: {
                telemetry.addData("Status", "Moving forward");
                telemetry.update();
                motorLeftBack.setPower(1);
                motorLeftFront.setPower(1);
                motorRightFront.setPower(1);
                motorRightBack.setPower(1);
                break;
            }
            case BACKWARD: {
                telemetry.addData("Status", "Moving backward");
                telemetry.update();
                motorLeftBack.setPower(-1);
                motorLeftFront.setPower(-1);
                motorRightFront.setPower(-1);
                motorRightBack.setPower(-1);
                break;
            }
            case LEFT: {
                telemetry.addData("Status", "Moving left");
                telemetry.update();
                motorLeftBack.setPower(1);
                motorLeftFront.setPower(-1);
                motorRightFront.setPower(1);
                motorRightBack.setPower(-1);
                break;
            }
            case RIGHT: {
                telemetry.addData("Status", "Moving right");
                telemetry.update();
                motorLeftBack.setPower(-1);
                motorLeftFront.setPower(1);
                motorRightFront.setPower(-1);
                motorRightBack.setPower(1);
                break;
            }
        }
        if(centimeters >= 0) {
            sleep((long) (centimeters * 1000 / VELOCITY));
            motorLeftBack.setPower(0);
            motorLeftFront.setPower(0);
            motorRightFront.setPower(0);
            motorRightBack.setPower(0);
        }
    }
}