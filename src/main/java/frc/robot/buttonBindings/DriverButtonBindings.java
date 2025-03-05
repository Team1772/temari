package frc.robot.buttonBindings;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.JointSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class DriverButtonBindings {
    private final CommandXboxController driver;

    private final DrivetrainSubsystem drivetrainSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final JointSubsystem jointSubsystem;
    private final LauncherSubsystem launcherSubsystem;

    public DriverButtonBindings(CommandXboxController driver, DrivetrainSubsystem drivetrainSubsystem, 
                                IntakeSubsystem intakeSubsystem, JointSubsystem jointSubsystem, 
                                LauncherSubsystem launcherSubsystem) {
        this.driver = driver;
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
        drivetrainSubsystem.setDefaultCommand(drivetrainSubsystem.arcadeDriveCommand(
            () -> -driver.getLeftY(), () -> -driver.getRightX()
        ));
    }

    public void intakeButtonBindings() {
        driver.leftTrigger().whileTrue(intakeSubsystem.intakeCommand());
        driver.rightTrigger().whileTrue(intakeSubsystem.outtakeCommand());
    }

    public void jointButtonBindings() {
        driver.rightBumper().and(jointSubsystem::hasJointTimerElapsed)
            .onTrue(jointSubsystem.restartJointTimerCommand()
            .andThen(jointSubsystem.toggleCommand())
            .andThen(jointSubsystem.stopJointTimerCommand()));
    }

    public void launcherButtonBindings() {
        driver.leftBumper().onTrue(Commands.race(
            Commands.startEnd(() -> launcherSubsystem.percentOut(1), launcherSubsystem::stop, launcherSubsystem),
            Commands.waitSeconds(5).andThen(intakeSubsystem.intakeCommand())
        ));
    }
}
