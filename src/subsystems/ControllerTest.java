package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Controller Test")
public class ControllerTest extends LinearOpMode{

    DcMotor motor1 = null;
    DcMotor motor2 = null;
    CRServo   servo1 = null;
    CRServo  servo2  = null;


    @Override
    public void runOpMode() throws InterruptedException {

        motor1   = hardwareMap.get(DcMotor.class,"motor1");
        motor2   = hardwareMap.get(DcMotor.class,"motor2");
        servo1   = hardwareMap.get(CRServo.class,"servo1");
        servo2   = hardwareMap.get(CRServo.class,"servo2");


        waitForStart();
        while (opModeIsActive()) {

        if (gamepad1.a) {
            motor1.setPower(0.5);
        }
        if (gamepad1.b) {
            motor1.setPower(0);
        }
        if (gamepad1.y) {
            motor2.setPower(0.5);
        }
        if (gamepad1.x) {
            motor2.setPower(0);
        }
        if (gamepad1.dpad_down) {
            servo1.setPower(0.5);
        }
        if (gamepad1.dpad_up) {
            servo1.setPower(0);
        }
        if (gamepad1.dpad_left) {
            servo2.setPower(0.5);
        }
        if (gamepad1.dpad_right) {
            servo2.setPower(0);
        }
            if (gamepad1.left_bumper) {
                motor1.setPower(0.5);
                motor2.setPower(0.5);
            }
        if (gamepad1.right_bumper) {
            motor1.setPower(1);
            motor2.setPower(1);
        }
        if (gamepad1.left_trigger  > 0.5) {
            servo1.setPower(0.25);
            servo2.setPower(0.25);
        }
        if (gamepad1.right_trigger > 0.5) {
            servo1.setPower(0.75);
            servo2.setPower(0.75);
        }
        if (gamepad1.right_stick_button) {
            motor1.setPower(-1);
            motor2.setPower(-1);
        }
        if (gamepad1.left_stick_button) {
            motor1.setPower(-0.5);
            motor2.setPower(-0.5);
        }
        if (gamepad1.left_stick_y > 0.5 || gamepad1.left_stick_y < -0.5) {
            servo1.setPower(-0.5);
            servo2.setPower(-0.5);
        }
        if (gamepad1.left_stick_x > 0.5 || gamepad1.left_stick_x < -0.5) {
            motor1.setPower(1);
            servo2.setPower(1);
        }

        }
    }
}
