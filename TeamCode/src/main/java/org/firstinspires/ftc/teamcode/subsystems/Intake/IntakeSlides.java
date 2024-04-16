package org.firstinspires.ftc.teamcode.subsystems.Intake;

import static org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSlidesState.*;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.*;

public class IntakeSlides implements Subsystem{

    private IntakeSlidesState state;

    public IntakeSlides(HardwareMap hardwareMap){
        state = STOWED;
    }

    public void setState(IntakeSlidesState state){

    }

    public IntakeSlidesState getState(){
        return state;
    }
}
