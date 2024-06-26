package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSlides;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSlidesState;

@Config
public class SlidesTest extends OpMode {

    private IntakeSlides intakeSlides;

    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addLine("STATUS: INITIALIZING");
        telemetry.update();

        intakeSlides = new IntakeSlides(hardwareMap);

        intakeSlides.setState(IntakeSlidesState.UNPOWERED);

        telemetry.addLine("STATUS: INITIALIZED");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addLine("STATUS: !!!!!!!!!!");
        telemetry.addLine("Slide Motor Ticks: " + intakeSlides.getSlidePosition());
        telemetry.update();
    }
}
