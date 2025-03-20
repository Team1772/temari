package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JointSubsystem extends SubsystemBase {
    private final WPI_VictorSPX leftMotor;
    //private final WPI_VictorSPX rightMotor;
    private final PWMTalonFX rightMotor;

    public JointSubsystem() {
        leftMotor = new WPI_VictorSPX(8);
        rightMotor = new PWMTalonFX(0);
        //rightMotor = new WPI_VictorSPX(9);

        leftMotor.configFactoryDefault();
        //rightMotor.configFactoryDefault();

        //rightMotor.follow(leftMotor);

        leftMotor.setInverted(false);
        //rightMotor.setInverted(InvertType.OpposeMaster);

        leftMotor.setNeutralMode(NeutralMode.Coast);
        //rightMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void percentOut(double speed) {
        leftMotor.set(ControlMode.PercentOutput, speed);
        rightMotor.set(-speed);
    }

    public void stop() {
        leftMotor.set(ControlMode.PercentOutput, 0);
        rightMotor.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Joint Subsystem/Motors/Left/Voltage/Output", leftMotor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Joint Subsystem/Motors/Left/Voltage/Bus", leftMotor.getBusVoltage());
        
        //SmartDashboard.putNumber("Joint Subsystem/Motors/Right/Voltage/Output", rightMotor.getMotorOutputVoltage());
        //SmartDashboard.putNumber("Joint Subsystem/Motors/Right/Voltage/Bus", rightMotor.getBusVoltage());
    }
}
