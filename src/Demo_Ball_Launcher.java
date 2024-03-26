package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
@TeleOp(name="Ball_Launcher_Demo")
public class Demo_Ball_Launcher extends LinearOpMode {
    /*Adds a variable for the left, right and the cataplt Dc Motors.
    adds an "int" for the catapult position and for a "count" to be used later in the program*/
    DcMotor left;
    DcMotor right;
    DcMotor catapult;
    int cpos = 0;    // integer to reference the caapult position in the encoder
    int count = 0;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        /* adds the left, right and catapult motors to the hardware map
        sets the catapult motor to reset the encoder and run them
        Left motor is names left in driver station config
        Right motor is named right in the driver station config
        catapult motor is named catapult in driver station config
         */
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        catapult = hardwareMap.get(DcMotor.class, "catapult");

        // reset the encoder to zero for the catapult
        catapult.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapult.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        runtime.reset();
        waitForStart();
        while(opModeIsActive()) {
        /* names 2 variables ly (left yaw) and ry (right yaw)
        program takes the input of the left and right joystick and sets the power to the motors accordingly
         */
            double ly = gamepad1.left_stick_y;
            double ry = gamepad1.right_stick_y;

            right.setPower(ry);
            left.setPower(-ly);

            if (gamepad1.a) {
                runtime.reset();
                /*

                tells the program to find the motor's current position (needs encoder wire)
                tells the catapult motor to change it's position to go negative 300 ticks
                 */
                cpos = catapult.getCurrentPosition();
                cpos += 100;
                catapult.setTargetPosition(cpos);
                catapult.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                catapult.setPower(1);
                sleep(400);

                catapult.setPower(0); //stops the catapult
                count = (int) runtime.seconds();


                //gives the data from the count to the telemetry (to get displayed on the driver station)
                telemetry.addData("catapult time:", count);
                //updates the telemetry
                telemetry.update();
            }
        }

    }
}

