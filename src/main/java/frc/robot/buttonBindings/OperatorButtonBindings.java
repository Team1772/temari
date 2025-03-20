package frc.robot.buttonBindings;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.JointSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class OperatorButtonBindings {
    private final CommandXboxController operator;

    private final DrivetrainSubsystem drivetrainSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final JointSubsystem jointSubsystem;
    private final LauncherSubsystem launcherSubsystem;

    public OperatorButtonBindings(CommandXboxController operator, DrivetrainSubsystem drivetrainSubsystem, 
                                IntakeSubsystem intakeSubsystem, JointSubsystem jointSubsystem, 
                                LauncherSubsystem launcherSubsystem) {
        this.operator = operator;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.jointSubsystem = jointSubsystem;
        this.launcherSubsystem = launcherSubsystem;

        configureBindings();
    }

    public void configureBindings() {
        drivetrainButtonBindings();
        intakeButtonBindings();
        jointButtonBindings();
        launcherButtonBindings();
    }

    public void drivetrainButtonBindings() {
    }

    public void intakeButtonBindings() {
        operator.leftTrigger().whileTrue(intakeSubsystem.intakeCommand());
        operator.rightTrigger().whileTrue(intakeSubsystem.outtakeCommand());
    }

    public void jointButtonBindings() {
        operator.rightBumper().whileTrue(jointSubsystem.runEnd(() -> jointSubsystem.percentOut(0.5), jointSubsystem::stop));
        operator.leftBumper().whileTrue(jointSubsystem.runEnd(() -> jointSubsystem.percentOut(-0.5), jointSubsystem::stop));
    }

    public void launcherButtonBindings() {
        operator.a().whileTrue(Commands.startEnd(() -> launcherSubsystem.percentOut(1), launcherSubsystem::stop, launcherSubsystem));
    }
}
