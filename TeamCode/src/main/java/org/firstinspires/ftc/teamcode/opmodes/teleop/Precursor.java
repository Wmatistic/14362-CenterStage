package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.*;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.*;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSlidesState;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeState;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.RobotState;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

@Config
@TeleOp(name = "Precursor", group = "Final")
public class Precursor extends OpMode {
    private Robot robot;

    private GamepadEx driver, operator;

    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addLine("STATUS: INITIALIZING");
        telemetry.update();

        robot = new Robot(hardwareMap, telemetry);

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);

        telemetry.addLine("STATUS: INITIALIZED");
        telemetry.update();
    }

    @Override
    public void loop() {

        /* ************************************ TELEMETRY ************************************ */

        telemetry.addLine("STATUS: !!!!!!!!");
        telemetry.update();

        /* ************************************ METHOD CALLS ************************************ */

        driver.readButtons();
        operator.readButtons();

        robot.drivetrain.drive(driver);

        // LOOP THROUGH STATES TO UPDATE
        //robot.intake.setState(robot.intake.getState());
        //robot.intakeSlides.setState(robot.intakeSlides.getState());


        /* ************************************ CONTROL ************************************ */

        /*
        BUTTON LAYOUT:

            DRIVER:
                DEFAULT LAYER:
                    Y -> RECENTER GYRO
                    X ->
                    A ->
                    B ->

                    Right Bumper HELD -> Switch to intake layer
                    Left Bumper ->

                    Right Trigger ->
                    Left Trigger ->

                    Dpad Up ->
                    Dpad Right ->
                    Dpad Down ->
                    Dpad Left ->

                    Left Stick X -> Translational Movement
                    Left Stick Y -> Translational Movement
                    Right Stick X -> Rotational Movement
                    Right Stick Y ->

                    Right Stick Button ->
                    Left Stick Button ->

                    TouchPad ->

                INTAKE LAYER:
                    Y -> RECENTER GYRO
                    X ->
                    A ->
                    B ->

                    Right Bumper HELD -> Intake activates
                    Left Bumper ->

                    Right Trigger ->
                    Left Trigger ->

                    Dpad Up -> Slides extend to full
                    Dpad Right -> Slides extend to half
                    Dpad Down -> Slides retract
                    Dpad Left ->

                    Left Stick X -> Translational Movement
                    Left Stick Y -> Translational Movement
                    Right Stick X -> Rotational Movement
                    Right Stick Y ->

                    Right Stick Button ->
                    Left Stick Button ->

                    TouchPad ->

         */

        /*              ***************** GLOBAL CONTROLS *****************             */

        // RECENTER GYRO
        if(driver.wasJustPressed(GamepadKeys.Button.Y)){ robot.drivetrain.resetHeading(); }

        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) { robot.drivetrain.changeMode(); }

        // SLOWDOWN
        if(driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
            robot.drivetrain.setSpeed(RobotConstants.Drivetrain.SLOW_SPEED);
        } else {
            robot.drivetrain.setSpeed(RobotConstants.Drivetrain.DEFAULT_SPEED);
        }

        /*              ************* STATE SPECIFIC CONTROLS **************             */

        switch(robot.getState()){

            case IDLE:

                if(driver.isDown(GamepadKeys.Button.RIGHT_BUMPER)){
                    robot.setState(RobotState.INTAKING);
                }

                break;

            case INTAKING:

                if(driver.isDown(GamepadKeys.Button.RIGHT_BUMPER)){
                    robot.intake.setState(IntakeState.INTAKING);
                } else {
                    robot.intake.setState(IntakeState.IDLE);
                    robot.intakeSlides.setState(IntakeSlidesState.STOWED);
                    robot.setState(RobotState.IDLE);
                }

                if(driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
                    robot.intakeSlides.setState(IntakeSlidesState.FULL);
                }

                if(driver.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
                    robot.intakeSlides.setState(IntakeSlidesState.HALF);
                }

                if(driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
                    robot.intakeSlides.setState(IntakeSlidesState.STOWED);
                }

                break;

            case OUTTAKING:



                break;

            case HANGING:



                break;
        }


    }
}
