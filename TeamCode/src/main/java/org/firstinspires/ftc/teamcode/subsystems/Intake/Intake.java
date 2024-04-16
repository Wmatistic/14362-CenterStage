package org.firstinspires.ftc.teamcode.subsystems.Intake;

import static org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeState.*;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class Intake implements Subsystem{

    private IntakeState state;

    private Servo armServoOne, armServoTwo;

    public Intake(HardwareMap hardwareMap){
        armServoOne = hardwareMap.servo.get(RobotConstants.Intake.armServoOne);
        armServoTwo = hardwareMap.servo.get(RobotConstants.Intake.armServoTwo);

        state = IDLE;
    }

    public void setState(IntakeState state){
        this.state = state;

        switch(state){
            case INTAKING:

                setArm(RobotConstants.Intake.ARM_GROUND);

                break;
        }
    }

    public IntakeState getState(){
        return state;
    }

    public void setArm(double angle){
        armServoOne.setPosition(angle);
        armServoTwo.setPosition(angle);
    }
}
