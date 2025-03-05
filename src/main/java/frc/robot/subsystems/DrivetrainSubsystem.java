package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    private final WPI_TalonSRX leftBack;
    private final WPI_TalonSRX leftFront;
    private final WPI_TalonSRX rightBack;
    private final WPI_TalonSRX rightFront;

    private final Encoder encoderLeft;
    private final Encoder encoderRight;

    private final DifferentialDrive differentialDrive;

    public DrivetrainSubsystem() {
        leftBack = new WPI_TalonSRX(1);
        leftFront = new WPI_TalonSRX(2);
        rightBack = new WPI_TalonSRX(3);
        rightFront = new WPI_TalonSRX(4);

        encoderLeft = new Encoder(3, 4, false);
        encoderRight = new Encoder(8, 9, false);

        differentialDrive = new DifferentialDrive(leftBack, rightBack);

        leftBack.configFactoryDefault();
        leftFront.configFactoryDefault();
        rightBack.configFactoryDefault();
        rightFront.configFactoryDefault();

        leftFront.follow(leftBack);
        rightFront.follow(rightBack);

        leftBack.setInverted(false);
        rightBack.setInverted(true);
        leftFront.setInverted(InvertType.FollowMaster);
        rightFront.setInverted(InvertType.FollowMaster);

        this.setNeutralMode(NeutralMode.Coast);

        this.setEncodersDistancePerPulse();
        this.resetEncoders();
    }

    public void drive(double xSpeed, double zRotation) {
        differentialDrive.arcadeDrive(xSpeed, zRotation);
    }

    public void setNeutralMode(NeutralMode neutralMode) {
        leftBack.setNeutralMode(neutralMode);
        rightBack.setNeutralMode(neutralMode);
        leftFront.setNeutralMode(neutralMode);
        rightFront.setNeutralMode(neutralMode);
    }

    public void resetEncoders() {
        encoderLeft.reset();
        encoderRight.reset();
    }

    public void setEncodersDistancePerPulse() {
        var wheelCircumferenceMeters = Units.inchesToMeters(2) * 2 * Math.PI;
        var distancePerPulse = wheelCircumferenceMeters / 500;
    
        this.encoderLeft.setDistancePerPulse(distancePerPulse);
        this.encoderRight.setDistancePerPulse(distancePerPulse);
    }

    public Command arcadeDriveCommand(DoubleSupplier fwd, DoubleSupplier rot) {
        return run(() -> differentialDrive.arcadeDrive(fwd.getAsDouble(), rot.getAsDouble()));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Drivetrain Subsystem/Encoders/Left", encoderLeft.get());
        SmartDashboard.putNumber("Drivetrain Subsystem/Encoders/Right", encoderRight.get());

        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Back Motor/Voltage/Output", leftBack.getMotorOutputVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Back Motor/Voltage/Bus", leftBack.getBusVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Back Motor/Current/Supply", leftBack.getSupplyCurrent());
        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Back Motor/Current/Stator", leftBack.getStatorCurrent());

        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Front Motor/Voltage/Output", leftFront.getMotorOutputVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Front Motor/Voltage/Bus", leftFront.getBusVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Front Motor/Current/Supply", leftFront.getSupplyCurrent());
        SmartDashboard.putNumber("Drivetrain Subsystem/Left Motors/Front Motor/Current/Stator", leftFront.getStatorCurrent());

        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Back Motor/Voltage/Output", rightBack.getMotorOutputVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Back Motor/Voltage/Bus", rightBack.getBusVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Back Motor/Current/Supply", rightBack.getSupplyCurrent());
        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Back Motor/Current/Stator", rightBack.getStatorCurrent());

        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Front Motor/Voltage/Output", rightFront.getMotorOutputVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Front Motor/Voltage/Bus", rightFront.getBusVoltage());
        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Front Motor/Current/Supply", rightFront.getSupplyCurrent());
        SmartDashboard.putNumber("Drivetrain Subsystem/Right Motors/Front Motor/Current/Stator", rightFront.getStatorCurrent());
    }
}
