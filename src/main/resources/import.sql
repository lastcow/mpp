
INSERT INTO `Role` (`roleId`, `roleName`, `roleDesc`, `groupId`) VALUES ('1', 'Admin', 'administration', NULL);
INSERT INTO `Department` (`departmentId`, `departmentName`, `departmentDesc`) VALUES ('1', 'Project', 'Project department');
INSERT INTO `User` (`userId`, `userName`, `password`, `email`, `groupId`, `roleId`) VALUES ('1', 'Administrator', '8fa25ddd9d5c9c701ae832642f3120a3',  'zhijiang@chen.me', NULL, '1');
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('0', '1', '1');
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('1', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('2', '1', '5');
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('3', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('4', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('5', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('6', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('7', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('8', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('9', '1', NULL);
INSERT INTO `Preference` (`preferenceId`, `userId`, `value`) VALUES ('10', '1', NULL);
INSERT INTO `Project` (`projectId`, `projectName`, `projectDesc`, `startDate`, `ownerId`) VALUES ('1', 'Project 1', 'test project 1', '2013-01-01', '1');
INSERT INTO `Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('1', 'New', NULL);
INSERT INTO `Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('2', 'Pending', NULL);
INSERT INTO `Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('3', 'In Progress', NULL);
INSERT INTO `Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('4', 'Finish', NULL);
INSERT INTO `Status` (`statusId`, `statusName`, `statusDesc`) VALUES ('5', 'Reopen', NULL);
INSERT INTO `Priority` (`priorityId`, `priorityName`, `priorityDesc`) VALUES ('1', 'Major', NULL);
//INSERT INTO `Task` (`taskId`, `taskName`, `taskActualStartDate`, `taskActualEndDate`, `taskEstimatedStartDate`, `taskEstimatedEndDate`, `taskDurationHour`, `taskPercentComplete`, `taskDesc`, `parentTaskId`, `preTaskId`, `projectId`, `statusId`, `priorityId`) VALUES ('1', 'Test task', '2001-01-01', NULL, '2011-01-01', '2011-01-02', 12, 0, NULL, NULL, NULL, '1', '2', '1');
//INSERT INTO `Task` (`taskId`, `taskName`, `taskActualStartDate`, `taskActualEndDate`, `taskEstimatedStartDate`, `taskEstimatedEndDate`, `taskDurationHour`, `taskPercentComplete`, `taskDesc`, `parentTaskId`, `preTaskId`, `projectId`, `statusId`, `priorityId`) VALUES ('2', 'Test task sub task', '2001-01-02', NULL, '2011-01-02', '2011-01-03', 3, 10, NULL, '1', NULL, NULL, '2', '1');
// INSERT INTO `UserXTask` (`userId`, `taskId`, `utilized`) VALUES ('1', '1', 1);

// Holiday
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('1', '元日', '2013-01-01', '1', '元日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('2', '成人の日', '2013-01-14', '1','成人の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('3', '建国記念の日', '2013-02-11', '1','建国記念の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('4', '春分の日', '2013-03-20', '1','春分の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('5', '昭和の日', '2013-04-29', '1','昭和の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('6', '憲法記念日', '2013-05-03', '1','憲法記念日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('7', 'みどりの日', '2013-05-04', '1','みどりの日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('8', 'こどもの日', '2013-05-05', '1','こどもの日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('9', '振替休日', '2013-05-06', '1','振替休日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('10', '海の日', '2013-07-15', '1','海の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('11', '敬老の日', '2013-09-16', '1','敬老の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('12', '秋分の日', '2013-09-23', '1','秋分の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('13', '体育の日', '2013-10-14', '1','体育の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('14', '文化の日', '2013-11-03', '1','文化の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('15', '振替休日', '2013-11-04', '1','振替休日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('16', '勤労感謝の日', '2013-11-23', '1','勤労感謝の日');
INSERT INTO `Holiday` (`holidayId`, `holidayName`, `startDate`, `fixed`, `holidayDesc`) VALUES ('17', '天皇誕生日', '2013-12-23', '1','天皇誕生日');