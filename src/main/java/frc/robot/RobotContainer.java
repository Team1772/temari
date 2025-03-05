// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.buttonBindings.DriverButtonBindings;
import frc.robot.buttonBindings.OperatorButtonBindings;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.JointSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class RobotContainer {
  private final CommandXboxController driver;
  private final CommandXboxController operator;

  private final DrivetrainSubsystem drivetrainSubsystem;
  private final IntakeSubsystem intakeSubsystem;
  private final JointSubsystem jointSubsystem;
  private final LauncherSubsystem launcherSubsystem;

  private final DriverButtonBindings driverButtonBindings;
  private final OperatorButtonBindings operatorButtonBindings;

  public RobotContainer() {
    driver = new CommandXboxController(0);
    operator = new CommandXboxController(1);

    drivetrainSubsystem = new DrivetrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    jointSubsystem = new JointSubsystem();
    launcherSubsystem = new LauncherSubsystem();

    driverButtonBindings = new DriverButtonBindings(driver, drivetrainSubsystem, intakeSubsystem, jointSubsystem, launcherSubsystem);
    operatorButtonBindings = new OperatorButtonBindings(operator, drivetrainSubsystem, intakeSubsystem, jointSubsystem, launcherSubsystem);

    configureBindings();
  }

  private void configureBindings() {
    driverButtonBindings.configureBindings();
    operatorButtonBindings.configureBindings();
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
