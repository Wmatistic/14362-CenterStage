package org.firstinspires.ftc.teamcode.subsystems.Intake;

import static org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSlidesState.*;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class IntakeSlides implements Subsystem{

    private IntakeSlidesState state;

    private DcMotorEx motorA, motorB;

    public IntakeSlides(HardwareMap hardwareMap){
        motorA = hardwareMap.get(DcMotorEx.class, RobotConstants.IntakeSlides.motorA);
        motorB = hardwareMap.get(DcMotorEx.class, RobotConstants.IntakeSlides.motorA);

        state = STOWED;
    }

    public void setState(IntakeSlidesState state){
        this.state = state;

        switch(state){
            case UNPOWERED:


        }
    }

    public IntakeSlidesState getState(){
        return state;
    }
}
