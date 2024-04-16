package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;

public class RobotConstants {
    @Config
    public static class Intake {
        public static String armServoOne = "armServoOne";
        public static String armServoTwo = "armServoTwo";

        public static double ARM_GROUND = 0;
        public static double ARM_LOADED = 0;
        public static double ARM_TRANSFERRING = 0;

        public static double TRAY_TRANSFERRING = 0;
        public static double TRAY_HANDOFF = 0;
    }

    @Config
    public static class IntakeSlides {
        public static int STOWED = 0;
        public static int HALF = 0;
        public static int FULL = 0;

        public static double MANUAL_ADJUSTMENT_MULT = 50;
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
