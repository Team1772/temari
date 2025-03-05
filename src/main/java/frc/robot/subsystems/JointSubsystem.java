package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JointSubsystem extends SubsystemBase {
    private final WPI_VictorSPX leftMotor;
    private final WPI_VictorSPX rightMotor;

    private Timer jointTimer;

    private boolean isDown;

    public JointSubsystem() {
        leftMotor = new WPI_VictorSPX(8);
        rightMotor = new WPI_VictorSPX(9);

        isDown = false;

        leftMotor.configFactoryDefault();
        rightMotor.configFactoryDefault();

        rightMotor.follow(leftMotor);

        leftMotor.setInverted(false);
        rightMotor.setInverted(InvertType.OpposeMaster);

        leftMotor.setNeutralMode(NeutralMode.Coast);
        rightMotor.setNeutralMode(NeutralMode.Coast);

        jointTimer = new Timer();
        jointTimer.start();
    }

    public void percentOut(double speed) {
        leftMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        leftMotor.set(ControlMode.PercentOutput, 0);
    }

    public ConditionalCommand toggleCommand() {
        return new ConditionalCommand(
            Commands.startEnd(() -> this.percentOut(1), this::stop, this).withTimeout(5).andThen(() -> this.toggleState(), this),
            Commands.startEnd(() -> this.percentOut(-1), this::stop, this).withTimeout(5).andThen(() -> this.toggleState(), this),
            () -> this.getState());
    }

    public boolean hasJointTimerElapsed() {
        return jointTimer.hasElapsed(5);
    }

    public void restartJointTimer() {
        jointTimer.restart();
    }

    public void stopJointTimer() {
        jointTimer.stop();
    }

    public Command restartJointTimerCommand() {
        return new InstantCommand(this::restartJointTimer, this);
    }

    public Command stopJointTimerCommand() {
        return new InstantCommand(this::stopJointTimer, this);
    }

    public boolean getState() {
        return this.isDown;
    }

    public void setState(boolean isDown) {
        this.isDown = isDown;
    }

    public void toggleState() {
        this.setState(!this.getState());
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Joint Subsystem/State/isDown", this.getState());

        SmartDashboard.putNumber("Joint Subsystem/Motors/Left/Voltage/Output", leftMotor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Joint Subsystem/Motors/Left/Voltage/Bus", leftMotor.getBusVoltage());
        
        SmartDashboard.putNumber("Joint Subsystem/Motors/Right/Voltage/Output", rightMotor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Joint Subsystem/Motors/Right/Voltage/Bus", rightMotor.getBusVoltage());

        SmartDashboard.putNumber("Joint Subsystem/Command Timer", jointTimer.get());
    }
}
