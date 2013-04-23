SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `mppdb` ;
CREATE SCHEMA IF NOT EXISTS `mppdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mppdb` ;

-- -----------------------------------------------------
-- Table `mppdb`.`RoleGroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`RoleGroup` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`RoleGroup` (
  `groupId` VARCHAR(36) NOT NULL ,
  `groupName` VARCHAR(150) NOT NULL ,
  `groupDesc` VARCHAR(1000) NULL ,
  PRIMARY KEY (`groupId`) )
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Role` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Role` (
  `roleId` VARCHAR(36) NOT NULL ,
  `roleName` VARCHAR(150) NOT NULL ,
  `roleDesc` VARCHAR(1000) NULL ,
  `groupId` VARCHAR(36) NULL ,
  PRIMARY KEY (`roleId`) ,
  INDEX `fk_Role_Group1` (`groupId` ASC) ,
  CONSTRAINT `fk_Role_Group1`
  FOREIGN KEY (`groupId` )
  REFERENCES `mppdb`.`RoleGroup` (`groupId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Department` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Department` (
  `departmentId` VARCHAR(36) NOT NULL ,
  `departmentName` VARCHAR(200) NOT NULL ,
  `departmentDesc` VARCHAR(1000) NULL ,
  PRIMARY KEY (`departmentId`) )
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`User` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`User` (
  `userId` VARCHAR(36) NOT NULL ,
  `userName` VARCHAR(150) NOT NULL ,
  `password` VARCHAR(100) NOT NULL ,
  `tel` VARCHAR(100) NULL ,
  `email` VARCHAR(250) NOT NULL ,
  `groupId` VARCHAR(36) NULL ,
  `roleId` VARCHAR(36) NULL ,
  `departmentId` VARCHAR(36) NULL ,
  PRIMARY KEY (`userId`) ,
  INDEX `fk_User_Group1` (`groupId` ASC) ,
  INDEX `fk_User_Role1` (`roleId` ASC) ,
  INDEX `fk_User_Department1` (`departmentId` ASC) ,
  CONSTRAINT `fk_User_Group1`
  FOREIGN KEY (`groupId` )
  REFERENCES `mppdb`.`RoleGroup` (`groupId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Role1`
  FOREIGN KEY (`roleId` )
  REFERENCES `mppdb`.`Role` (`roleId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Department1`
  FOREIGN KEY (`departmentId` )
  REFERENCES `mppdb`.`Department` (`departmentId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Project` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Project` (
  `projectId` VARCHAR(36) NOT NULL ,
  `projectName` VARCHAR(500) NOT NULL ,
  `projectDesc` VARCHAR(2000) NULL ,
  `startDate` DATETIME NOT NULL ,
  `ownerId` VARCHAR(36) NOT NULL ,
  PRIMARY KEY (`projectId`) ,
  INDEX `fk_Project_User1` (`ownerId` ASC) ,
  CONSTRAINT `fk_Project_User1`
  FOREIGN KEY (`ownerId` )
  REFERENCES `mppdb`.`User` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Status` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Status` (
  `statusId` VARCHAR(36) NOT NULL ,
  `statusName` VARCHAR(100) NOT NULL ,
  `statusDesc` VARCHAR(1000) NULL ,
  PRIMARY KEY (`statusId`) )
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Priority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Priority` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Priority` (
  `priorityId` VARCHAR(36) NOT NULL ,
  `priorityName` VARCHAR(100) NOT NULL ,
  `priorityDesc` VARCHAR(1000) NULL ,
  PRIMARY KEY (`priorityId`) )
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Task` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Task` (
  `taskId` VARCHAR(36) NOT NULL ,
  `taskName` VARCHAR(200) NOT NULL ,
  `taskActualStartDate` DATETIME NULL ,
  `taskActualEndDate` DATETIME NULL ,
  `taskEstimatedStartDate` DATETIME NOT NULL ,
  `taskEstimatedEndDate` DATETIME NOT NULL ,
  `taskDurationHour` INT NULL DEFAULT 1 ,
  `taskActualDurationHour` INT NULL ,
  `taskPercentComplete` FLOAT NOT NULL DEFAULT 0.0 ,
  `taskDesc` VARCHAR(1000) NULL ,
  `parentTaskId` VARCHAR(36) NULL ,
  `preTaskId` VARCHAR(36) NULL ,
  `projectId` VARCHAR(36) NULL ,
  `statusId` VARCHAR(36) NOT NULL ,
  `priorityId` VARCHAR(36) NOT NULL ,
  PRIMARY KEY (`taskId`) ,
  INDEX `fk_task_project` (`projectId` ASC) ,
  INDEX `fk_Task_Task1` (`parentTaskId` ASC) ,
  INDEX `fk_Task_Status1` (`statusId` ASC) ,
  INDEX `fk_Task_Priority1` (`priorityId` ASC) ,
  INDEX `fk_Task_Task2` (`preTaskId` ASC) ,
  CONSTRAINT `fk_task_project`
  FOREIGN KEY (`projectId` )
  REFERENCES `mppdb`.`Project` (`projectId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Task1`
  FOREIGN KEY (`parentTaskId` )
  REFERENCES `mppdb`.`Task` (`taskId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Status1`
  FOREIGN KEY (`statusId` )
  REFERENCES `mppdb`.`Status` (`statusId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Priority1`
  FOREIGN KEY (`priorityId` )
  REFERENCES `mppdb`.`Priority` (`priorityId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Task2`
  FOREIGN KEY (`preTaskId` )
  REFERENCES `mppdb`.`Task` (`taskId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`UserXTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`UserXTask` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`UserXTask` (
  `userId` VARCHAR(36) NOT NULL ,
  `taskId` VARCHAR(36) NOT NULL ,
  `utilized` FLOAT NOT NULL DEFAULT 100 COMMENT 'Sometime resource been placed in more than 1 task, so it can be less than 100%' ,
  PRIMARY KEY (`userId`, `taskId`) ,
  INDEX `fk_User_has_Task_Task1` (`taskId` ASC) ,
  INDEX `fk_User_has_Task_User1` (`userId` ASC) ,
  CONSTRAINT `fk_User_has_Task_User1`
  FOREIGN KEY (`userId` )
  REFERENCES `mppdb`.`User` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Task_Task1`
  FOREIGN KEY (`taskId` )
  REFERENCES `mppdb`.`Task` (`taskId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Preference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Preference` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Preference` (
  `preferenceId` INT NOT NULL ,
  `userId` VARCHAR(36) NOT NULL ,
  `value` VARCHAR(500) NULL ,
  PRIMARY KEY (`preferenceId`) ,
  INDEX `fk_Preference_User1` (`userId` ASC) ,
  CONSTRAINT `fk_Preference_User1`
  FOREIGN KEY (`userId` )
  REFERENCES `mppdb`.`User` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mppdb`.`Holiday`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mppdb`.`Holiday` ;

CREATE  TABLE IF NOT EXISTS `mppdb`.`Holiday` (
  `holidayId` VARCHAR(36) NOT NULL ,
  `holidayDesc` VARCHAR(400) NULL ,
  `holidayName` VARCHAR(250) NOT NULL ,
  `startDate` DATETIME NULL ,
  `endDate` DATETIME NULL ,
  `fixed` TINYINT(1) NULL ,
  PRIMARY KEY (`holidayId`) )
  ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Data for table `mppdb`.`Role`
-- -----------------------------------------------------
START TRANSACTION;
USE `mppdb`;
INSERT INTO `mppdb`.`Role` (`roleId`, `roleName`, `roleDesc`, `groupId`) VALUES ('1', 'Admin', 'administration', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `mppdb`.`Department`
-- -----------------------------------------------------
START TRANSACTION;
USE `mppdb`;
INSERT INTO `mppdb`.`Department` (`departmentId`, `departmentName`, `departmentDesc`) VALUES ('1', 'R&D', 'Reasch department');

COMMIT;

-- -----------------------------------------------------
-- Data for table `mppdb`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `mppdb`;
INSERT INTO `mppdb`.`User` (`userId`, `userName`, `password`, `tel`, `email`, `groupId`, `roleId`, `departmentId`) VALUES ('1', 'Administrator', '8fa25ddd9d5c9c701ae832642f3120a3', '18000000000', 'zhijiang@chen.me', NULL, '1', '1');

COMMIT;

-- -----------------------------------------------------
-- Data for table `mppdb`.`Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `mppdb`;
INSERT INTO `mppdb`.`Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('1', 'New', NULL);
# INSERT INTO `mppdb`.`Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('2', 'Pending', NULL);
INSERT INTO `mppdb`.`Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('3', 'In Progress', NULL);
INSERT INTO `mppdb`.`Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('4', 'Finish', NULL);
# INSERT INTO `mppdb`.`Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('5', 'Reopen', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `mppdb`.`Priority`
-- -----------------------------------------------------
START TRANSACTION;
USE `mppdb`;
INSERT INTO `mppdb`.`Priority` (`priorityId`, `priorityName`, `priorityDesc`) VALUES ('1', 'Major', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mppdb`.`Preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `mppdb`;
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (0, '1', '1');
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (1, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (2, '1', '5');
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (3, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (4, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (5, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (6, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (7, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (8, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (9, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (10, '1', NULL);
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (11, '1', '0');
INSERT INTO `mppdb`.`Preference` (`preferenceId`, `userId`, `value`) VALUES (12, '1', '0');

COMMIT;

-- -----------------------------------------------------
-- Data for table `mppdb`.`Preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `mppdb`;
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('1', '元日', '2013-01-01', '1', '元日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('2', '成人の日', '2013-01-14', '1','成人の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('3', '建国記念の日', '2013-02-11', '1','建国記念の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('4', '春分の日', '2013-03-20', '1','春分の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('5', '昭和の日', '2013-04-29', '1','昭和の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('6', '憲法記念日', '2013-05-03', '1','憲法記念日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('7', 'みどりの日', '2013-05-04', '1','みどりの日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('8', 'こどもの日', '2013-05-05', '1','こどもの日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('9', '振替休日', '2013-05-06', '1','振替休日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('10', '海の日', '2013-07-15', '1','海の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('11', '敬老の日', '2013-09-16', '1','敬老の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('12', '秋分の日', '2013-09-23', '1','秋分の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('13', '体育の日', '2013-10-14', '1','体育の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('14', '文化の日', '2013-11-03', '1','文化の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('15', '振替休日', '2013-11-04', '1','振替休日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('16', '勤労感謝の日', '2013-11-23', '1','勤労感謝の日');
INSERT INTO `mppdb`.`Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('17', '天皇誕生日', '2013-12-23', '1','天皇誕生日');

COMMIT;
