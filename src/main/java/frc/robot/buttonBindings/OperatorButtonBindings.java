package frc.robot.buttonBindings;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.JointSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class OperatorButtonBindings {
    @SuppressWarnings("unused")
    private final CommandXboxController operator;

    @SuppressWarnings("unused")
    private final DrivetrainSubsystem drivetrainSubsystem;
    @SuppressWarnings("unused")
    private final IntakeSubsystem intakeSubsystem;
    @SuppressWarnings("unused")
    private final JointSubsystem jointSubsystem;
    @SuppressWarnings("unused")
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
    }

    public void jointButtonBindings() {
    }

    public void launcherButtonBindings() {
    }
}
