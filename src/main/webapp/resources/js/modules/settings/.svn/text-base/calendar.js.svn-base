

function initComponent(){
    // Init dialog
    $('#divNewHoliday').dialog({

        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,

        // Events
        close: function( event, ui ) {
            $('#btnHolidayDialogClose').click();
        }
    });


    $('#btnNewHoliday').on('click', function(){
        // Only show Create button.
        showDialogButton('new');
        $('#divNewHoliday').dialog('open');
    });

    // Buttons.

    $('#hrefNewHolidayPanelClose').on('click', function(){
        $('#divNewHoliday').hide();
    });

	//===== Active menu =====//
	activeLeftMenuItem('menuLeftSettingsCalendar');

    $('#txtNewHolidayStartDate').datepicker();

    $('#txtNewHolidayEndDate').datepicker();

    //===== CalendarDto =====//

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next',
            center: 'title',
            right: 'month'
        },
        events:{
            url: $('#hiddenRoot').val()+'/rest/calendar/holidaylist',
            type: 'POST',

            error: function(){
                        alert('ERROR');
                    }
        }
    });
}

/**
 * Init
 */
function init(){
    $('#hrefNewHolidayPanelClose').on('click', function(){
        $('#divNewHoliday').hide();
    });

    $('#txtNewHolidayStartDate').datepicker();

    $('#txtNewHolidayEndDate').datepicker();

}

function showDialogButton(name){
    var buttons = $('#divNewHoliday').dialog( "option", "buttons" );
    if(name == 'new'){
        $('#divNewHoliday').dialog('option', 'title', 'Create new holiday');
        $('#divNewHoliday').dialog('option', 'buttons', [
            {text: 'Create Holiday', click: function() { $('#btnNewHolidaySubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }else if(name == 'edit'){
        $('#divNewHoliday').dialog('option', 'title', 'Modify holiday');
        $('#divNewHoliday').dialog('option', 'buttons', [
            {text: 'Modify Holiday', click: function() { $('#btnEditHolidaySubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }
}


/**
 * Reset after closed
 */
function resetDialogAfterClosed(){
    init();

    /* Remove invalid class */
    $('#txtNewHolidayName').removeClass('invalid');
    $('#txtNewHolidayStartDate').removeClass('invalid');
    $('#txtNewHolidayEndDate').removeClass('invalid');
    $('#txtNewHolidayDesc').removeClass('invalid');

    /* Remove end date
    * Validator can't be reset by backend bean.
    * */
    $('#txtNewHolidayEndDate').val('');
}

/**
 * Hidden form
 */
function resetForm(data){
    $('#divNewHoliday').dialog('close');
    init();
    /* Remove invalid class */
    $('#txtNewHolidayName').removeClass('invalid');
    $('#txtNewHolidayStartDate').removeClass('invalid');

}

/**
 * Re-Init.
 */
function editHoliday(data){
    init();

    if(data.status == "success" ){
        // Open dialog
        showDialogButton('edit');
        $('#divNewHoliday').dialog('open');
    }
}

/**
 * Delete selected holiday
 * @param event
 * @param holidayId
 * @param holidayName
 */
function confirmDelete(event, holidayId, holidayName){
    if(event.status == "success"){
        $.prompt("Are you sure want to delete holiday: <strong>" + holidayName + "</strong> ?", {
            title: "Confirm Deletion",
            buttons: { "Delete": true, "No": false },
            top: "40%",
            submit: function(e,v,m,f){
                // use e.preventDefault() to prevent closing when needed or return false.
                // e.preventDefault();
                if(v){
                    // Delete (id been transformed to table:index:id)
                    $('#deleteHolidayIdHidden').val(holidayId);
                    $('#btnDeleteHolidayHidden').click();
                }
            }
        });
    }
}

function deleteHolidayEventHandler(data){

    if(data.status == "begin"){
    }else if(data.status == "success"){
        $('#calendar').fullCalendar('refetchEvents');
    }else if(data.status == "complete"){
        // Hide newproject panel always.
    }
}

/**
 * Handle event create/modify action.
 * @param data
 */
function holidayEventHandler(data){
    if(data.status == "begin"){
        // Loading...
    }else if(data.status == "success" ){
        // Success...
        if($('#txtNewHolidayName').hasClass('invalid') ||
            $('#txtNewHolidayStartDate').hasClass('invalid') ||
            $('#txtNewHolidayEndDate').hasClass('invalid') ||
            $('#txtNewHolidayDesc').hasClass('invalid') ){
            // Do nothing
            init();
        }else{
            // Close dialog
            $('#divNewHoliday').dialog('close');
        }
        $('#calendar').fullCalendar('refetchEvents');
    }else if(data.status == "complete"){

    }
}