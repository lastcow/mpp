
var rootPath;

function initComponent(){

    rootPath = $('#hiddenRoot').val();
	//===== Active menu =====//
	activeLeftMenuItem('menuLeftSchedule');

    //===== CalendarDto =====//

    $('#mySchedule1').eventCalendar({
        eventsjson: rootPath+'/rest/event/getevents/1',
        cacheJson: false
    });
    $('#mySchedule2').eventCalendar({
        eventsjson: rootPath+'/rest/event/getevents/1'
    });
    $('#mySchedule3').eventCalendar({
        eventsjson: rootPath+'/rest/event/getevents/1'
    });

}