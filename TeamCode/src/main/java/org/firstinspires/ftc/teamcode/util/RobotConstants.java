package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;

public class RobotConstants {
    @Config
    public static class Intake {
        // put intake constants here
    }

    @Config
    public static class IntakeSlides {
        // put intake slide constants
    }

    @Config
    public static class Drivetrain {
        public static String LFMOTOR = "leftFront";
        public static String LRMOTOR = "leftRear";
        public static String RFMOTOR = "rightFront";
        public static String RRMOTOR = "rightRear";

        public static double DEFAULT_SPEED = 1.0;
        public static double SLOW_SPEED = 0.5;
    }
}
