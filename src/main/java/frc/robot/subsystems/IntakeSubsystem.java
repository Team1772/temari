package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final WPI_TalonSRX motor;

    public IntakeSubsystem() {
        motor = new WPI_TalonSRX(7);

        motor.configFactoryDefault();
        motor.setInverted(false);
        motor.setNeutralMode(NeutralMode.Coast);
    }

    public void percentOut(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }

    public Command intakeCommand() {
        return Commands.startEnd(() -> this.percentOut(0.5), this::stop, this);
    }

    public Command outtakeCommand() {
        return Commands.startEnd(() -> this.percentOut(-0.5), this::stop, this);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Subsystem/Motor/Voltage/Output", motor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Intake Subsystem/Motor/Voltage/Bus", motor.getBusVoltage());
        SmartDashboard.putNumber("Intake Subsystem/Motor/Current/Supply", motor.getSupplyCurrent());
        SmartDashboard.putNumber("Intake Subsystem/Motor/Current/Statormotor", motor.getStatorCurrent());
    }
}
