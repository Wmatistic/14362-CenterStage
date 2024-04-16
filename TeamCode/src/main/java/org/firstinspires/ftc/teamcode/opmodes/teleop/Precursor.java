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

import org.firstinspires.ftc.teamcode.subsystems.Robot;

@Config
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

        telemetry.addLine("STATUS: !!!!!!!!");
        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        robot.drivetrain.drive(driver);

        // RECENTER GYRO
        if(driver.wasJustPressed(GamepadKeys.Button.Y)){ robot.drivetrain.resetHeading(); }
    }
}
