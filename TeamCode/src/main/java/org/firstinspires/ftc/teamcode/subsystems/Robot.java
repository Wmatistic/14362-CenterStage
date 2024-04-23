package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.RobotState.*;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.*;
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSlides;

public class Robot {

    public Intake intake;
    public IntakeSlides intakeSlides;
    public Drivetrain drivetrain;

    private RobotState state;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        //intake = new Intake(hardwareMap);
        //intakeSlides = new IntakeSlides(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);

        state = INTAKING;
    }

    public void setState(RobotState state){
        this.state = state;
    }

    public RobotState getState(){
        return state;
    }
}
