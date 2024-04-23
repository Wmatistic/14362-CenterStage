package org.firstinspires.ftc.teamcode.subsystems.Intake;

import static org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSlidesState.*;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class IntakeSlides implements Subsystem{

    private IntakeSlidesState state;

    private DcMotorEx motorA, motorB;

    int targetPosition;
    IntakeSlidesState targetState;

    public IntakeSlides(HardwareMap hardwareMap){
        motorA = hardwareMap.get(DcMotorEx.class, RobotConstants.IntakeSlides.motorA);
        motorB = hardwareMap.get(DcMotorEx.class, RobotConstants.IntakeSlides.motorB);

        motorA.setDirection(DcMotorEx.Direction.REVERSE);

        motorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorA.setTargetPosition(RobotConstants.IntakeSlides.STOWED);
        motorB.setTargetPosition(RobotConstants.IntakeSlides.STOWED);

        motorA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorA.setPower(RobotConstants.IntakeSlides.POWER);
        motorB.setPower(RobotConstants.IntakeSlides.POWER);

        state = STOWED;
    }

    public void setState(IntakeSlidesState state){
        this.state = state;

        switch(state){
            case MOVING:

                if(inRange(getSlidePosition(), targetPosition, RobotConstants.IntakeSlides.RANGE)) {
                    setTargetPosition(targetPosition);
                    this.state = targetState;
                }

                break;

            case FULL:

                setTargetPosition(RobotConstants.IntakeSlides.FULL);

                if(!inRange(getSlidePosition(), targetPosition, RobotConstants.IntakeSlides.RANGE)) {
                    targetState = FULL;
                    this.state = MOVING;
                } else {
                    this.state = FULL;
                }

                break;

            case HALF:

                setTargetPosition(RobotConstants.IntakeSlides.HALF);

                if(!inRange(getSlidePosition(), targetPosition, RobotConstants.IntakeSlides.RANGE)) {
                    targetState = HALF;
                    this.state = MOVING;
                } else {
                    this.state = HALF;
                }

                break;

            case UNPOWERED:

                setPower(0);

                motorA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                motorB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

                break;
        }
    }

    public IntakeSlidesState getState(){
        return state;
    }

    public void setPower(double power){
        motorA.setPower(power);
        motorB.setPower(power);
    }

    public void setTargetPosition(int position){

        targetPosition = position;

        motorA.setTargetPosition(position);
        motorB.setTargetPosition(position);
    }

    public int getSlidePosition(){
        return (motorA.getCurrentPosition() + motorB.getCurrentPosition()) / 2;
    }

    public boolean inRange(int current, int target, int offset){
        return current < target + offset && current > target - offset;
    }
}
