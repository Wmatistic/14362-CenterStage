package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class Drivetrain implements Subsystem{

    private DcMotorEx leftFront, leftRear, rightFront, rightRear;
    double x, y, rx, leftFrontPower, leftRearPower, rightFrontPower, rightRearPower, heading, rotX, rotY;
    private RevIMU imu;
    private Mode mode;
    private double speed = RobotConstants.Drivetrain.DEFAULT_SPEED;

    enum Mode{FIELD, ROBOT}

    public Drivetrain(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotorEx.class, RobotConstants.Drivetrain.LFMOTOR);
        leftRear = hardwareMap.get(DcMotorEx.class, RobotConstants.Drivetrain.LRMOTOR);
        rightFront = hardwareMap.get(DcMotorEx.class, RobotConstants.Drivetrain.RFMOTOR);
        rightRear = hardwareMap.get(DcMotorEx.class, RobotConstants.Drivetrain.RRMOTOR);

        leftRear.setDirection(DcMotorEx.Direction.REVERSE);
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);

        imu = new RevIMU(hardwareMap);
        imu.init();
        mode = Mode.ROBOT;
    }

    public void drive(GamepadEx gamepad) {
        x = Math.pow(gamepad.getLeftX(), 3);
        y = Math.pow(gamepad.getLeftY() * 1.1, 3);
        rx = Math.pow(gamepad.getRightX(), 3);

        switch (mode) {
            case FIELD:
                heading = Math.toRadians(-imu.getHeading() + 180);
                rotX = x * Math.cos(heading) - y * Math.sin(heading);
                rotY = x * Math.sin(heading) + y * Math.sin(heading);

                leftFrontPower = (rotY + rotX + rx);
                leftRearPower = (rotY - rotX + rx);
                rightFrontPower = (rotY - rotX - rx);
                rightRearPower = (rotY + rotX - rx);

                break;
            case ROBOT:

                leftFrontPower = (y + x + rx);
                leftRearPower = (y - x + rx);
                rightFrontPower = (y - x - rx);
                rightRearPower = (y + x - rx);

                break;
        }

        leftFront.setPower(leftFrontPower * speed);
        leftRear.setPower(leftRearPower * speed);
        rightFront.setPower(rightFrontPower * speed);
        rightRear.setPower(rightRearPower * speed);
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getHeading(){
        return imu.getHeading();
    }

    public void resetHeading(){
        imu.reset();
    }

    public void setMode(Mode m){
        mode = m;
    }

    public String getMode(){
        if (mode == Mode.FIELD){
            return "FIELD CENTRIC";
        } else {
            return "ROBOT CENTRIC";
        }
    }

    public void altMode() {
        if (mode.equals(Mode.ROBOT)) {
            mode = Mode.FIELD;
        } else {
            mode = Mode.ROBOT;
        }
    }
}
