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
@TeleOp(name="Drone_Launcher_Demo")
public class Demo_Ball_Launcher extends LinearOpMode {
    DcMotor left;
    DcMotor right;
    DcMotor catapult;
    int cpos = 0;
    int count = 0;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        catapult = hardwareMap.get(DcMotor.class, "catapult");
        catapult.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapult.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while(opModeIsActive()) {

            double ly = gamepad1.left_stick_y;
            double ry = gamepad1.right_stick_y;

            right.setPower(ry);
            left.setPower(ly);

            if (gamepad1.a) {
                cpos = catapult.getCurrentPosition();
                cpos -= 300;
                catapult.setTargetPosition(cpos);
                catapult.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                catapult.setPower(1);
                while (catapult.isBusy()) {
                    count ++;
                }
                catapult.setPower(0);
                telemetry.addData("catapult time:", count);

                telemetry.update();
        }
            }

        }
    }

