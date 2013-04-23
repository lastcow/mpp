
var rootPath;
var ganttChartControl;
var taskPanelPositionLeft = 0;
var taskPanelPositionTop = 0;
/**
 * Init
 */
function initComponent(){

	rootPath = $('#hiddenRoot').val();

    // Set taskPanelPositionLeft and top init value
    taskPanelPositionLeft = $.cookie('taskPanelScrollLeft');
    taskPanelPositionTop = $.cookie('taskPanelScrollTop');

    $('#wbstype').html('WBS - Baseline');
	
	$('#btnNewTask').on('click', function(){
        // Set current Date
        setCurrentDate();

		showDialogButton('new');
        $('#divTaskForm').dialog('open');

        rememberTaskScrollPosition();
	});

    initTaskForm();

    /***
     * Dialog
     */
    $('#divTaskForm').dialog({

        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,

        // Events
        close: function( event, ui ) {
            $('#btnTaskDialogClose').click();
        }
    });

    /**
     * Edit task dialog
     */
    $('#divEditTaskForm').dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        buttons:{
            'Edit Task': function(){ $('#btnEditTaskSubmit').click(); },
            'Delete Task': function() { $('#btnEditTaskDeleteSubmitConfirm').click(); },
            'Cancel': function(){$(this).dialog('close');}
        },

        close: function( event, ui ){
            $('#btnEditTaskDialogClose').click();
        }
    });

    $('#divResourceForm').dialog({

        title: "Insert resource",
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 510,
        buttons:{
            "Insert Resource": function(){$('#btnInsertResourceSubmit').click();},
            "Cancel": function(){$( this ).dialog( "close" );}
        },

        // Events
        close: function( event, ui ){
            $('#btnResourceClose').click();
        }
    });

    $('#divTaskDialogForm').dialog({
        title: "Insert pretask",
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 510,
        buttons:{
            "Insert Pre-Task": function(){$('#btnInsertTaskSubmit').click();},
            "Cancel": function(){$(this).dialog("close");}
        },

        // Events
        close: function (event, ui){
            $('#btnTaskDialogFormClose').click();
        }

    });

    // Create Gantt control
    ganttChartControl = new GanttChart();
    // Setup paths and behavior
    ganttChartControl.setImagePath(rootPath+"/resources/plugins/dhtmlxganttchart/imgs/");
    ganttChartControl.setEditable(false);
    ganttChartControl.showTreePanel(true);
    ganttChartControl.showContextMenu(false);
    ganttChartControl.showDescTask(true,'n,s-f');
    ganttChartControl.showDescProject(true,'n,d');
    ganttChartControl.attachEvent("onTaskClick", function (task){
        onTaskClick(task);
    });

    
    // Load data structure        
/*
    ganttChartControl.addProject(project1);
    ganttChartControl.addProject(project2);
*/
    // Build control on the page
    ganttChartControl.create('GanttDiv');

    loadBaseline();
    
//    loadBaseline();

    $('#btnTriggleBaseline').on('click', function(){
        rememberTaskScrollPosition();
        $('#wbstype').html('WBS - Baseline');
        loadBaseline();
    } ) ;

    $('#btnTriggleActual').on('click', function(){
        rememberTaskScrollPosition();
        $('#wbstype').html('WBS - Actual');
        loadActual();
    });

}

function loadBaseline(){
    /* 	 Get XML data from server*/
    $('#GanttDiv').showLoading();
    $.ajax({
        type:'GET',
        url:rootPath+'/rest/project/getprojectwbs/'+projectId
    }).done(function ( data ){
            ganttChartControl.loadData((new XMLSerializer()).serializeToString(data), false, true);
            /* 			ganttChartControl.loadData('<projects><project id="1"/></projects>', false, true); */
        }).fail(function (){

        }).always(function (){
            // hide loading.
            $('#GanttDiv').hideLoading();

            // Set task panel position
            setTaskScrollPosition();
        });
}

function loadActual(){
    /* 	 Get XML data from server*/
    $('#GanttDiv').showLoading();
    $.ajax({
        type:'GET',
        url:rootPath+'/rest/project/getprojectwbs_acutal/'+projectId
    }).done(function ( data ){
            ganttChartControl.loadData((new XMLSerializer()).serializeToString(data), false, true);
            /* 			ganttChartControl.loadData('<projects><project id="1"/></projects>', false, true); */
        }).fail(function (){

        }).always(function (){
            // hide loading.
            $('#GanttDiv').hideLoading();

            // Set task panel position
            setTaskScrollPosition();
        });
}

/**
 *
 * @param task
 */
function onTaskClick(task){

    rememberTaskScrollPosition();
    // Assign id to hidden field
    $('#editTaskId').val(task.getId())
    $('#btnEditTaskSubmitId').click();
}

/**
*
* Create task onevent
**/
function crudTaskEventHandler(data){
	if(data.status == "begin"){
		// Loading...
	}else if(data.status == "success" ){	
		// Buttons.
		initComponent();
        uniformElement('taskform');

        if(isFormValid()){
            $('#divTaskForm').dialog('close');
        }

        $('#btnResetTaskPanelPosition').click();

	}else if(data.status == "complete"){

	}
}

function editTaskEventHandler(data){
    if(data.status == "begin"){
        // Loading...
    }else if(data.status == "success"){

        if(isEditTaskFormValid()){
            $('#divEditTaskForm').dialog('close');
        }

        initEditTaskForm();

//        $('#btnResetTaskPanelPosition').click();
    }else if(data.status == "complete"){

    }
}

/**
 * Buttons sets for different entry.
 * @param name
 */
function showDialogButton(name){

    if(name == 'new'){
        $('#divTaskForm').dialog('option', 'title', 'Create new task');
        $('#divTaskForm').dialog('option', 'buttons', [
            {text: 'Create Task', click: function() { $('#btnNewTaskSubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }else if(name == 'edit'){
        $('#divTaskForm').dialog('option', 'title', 'Modify task');
        $('#divTaskForm').dialog('option', 'buttons', [
            {text: 'Modify Task', click: function() { $('#btnEditTaskSubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }
}

/**
 * Task CRUD event handler
 * @param data
 */
function resourceEventHandler(data){
    if(data.status == "begin"){
        // Loading...
    }else if(data.status == "success" ){
        // Success...
        if(isResourceFormValid()){
            $('#divResourceForm').dialog('close');
        }else{
            // Update combobox.
        }
        uniformElement('resourceform');
        initTaskForm();
    }else if(data.status == "complete"){

    }
}

function pretaskEventHandler(data){
    if(data.status == "begin"){
        // Loading...
    }else if(data.status == "success"){
        $('#divTaskDialogForm').dialog('close');
        uniformElement('newTaskDialogForm');
        initTaskForm();
    }else if(data.status == "complete"){}
}

/**
 * Resource CRUD event handler
 * @param data
 */
function crudResourceEventHandler(data){
    if(data.status == "begin"){
        // Loading...
    }else if(data.status == "success" ){
        // Success...
        initTaskForm();
        uniformElement('newTaskDialogForm');
    }else if(data.status == "complete"){

    }
}

/**
 * Load task finished
 * @param data
 */
function loadTaskEventHandler(data){
    if(data.status == "begin"){
        // Loading...
    }else if(data.status = "success"){
        // Reload edit task form and open dialog
        initEditTaskForm();
        initTaskForm();
        $('#divEditTaskForm').dialog('open');
    }else if(data.status == "complete"){
        // Complete
    }
}

/**
 * remove resource from list
 * @param userId
 */
function removeResource(userId){
    // Assign to hidden field.
    $('#selectedResource').val(userId);
    // Submit
    $('#btnRemoveResourceSubmit').click();

}

function removeTask(taskId){
    // Assign to hidden field.
    $('#selectedPreTask').val(taskId);
    // Submit
    $('#btnRemovePreTaskSubmit').click();
}

function removePreTask(taskId){
    // Assign to hidden field.
    $('#selectedPreTask').val(taskId);
    // Submit
    $('#btnRemovePreEditTaskSubmit').click();
}

/**
 * Check form validation
 * @return {boolean}
 */
function isFormValid(){
    return !($('#txtNewTaskName').hasClass('invalid') ||
        $('#txtNewTaskStartDate').hasClass('invalid') ||
        $('#txtNewTaskEndDate').hasClass('invalid') ||
        $('#comboNewTaskParent').hasClass('invalid') ||
        $('#comboNewTaskPred').hasClass('invalid') ||
        $('#taNewTaskDesc').hasClass('invalid'));
}

/**
 * Check form validation (edit task form)
 * @returns {boolean}
 */
function isEditTaskFormValid(){
    return !($('#txtEditTaskName').hasClass('invalid') ||
        $('#txtEditTaskStartDate').hasClass('invalid') ||
        $('#txtEditTaskActualEndDate').hasClass('invalid') ||
        $('#txtEditTaskActualStartDate').hasClass('invalid') ||
        $('#txtEditTaskEndDate').hasClass('invalid'));
}

/**
 * Check resource form validation.
 * @return {boolean}
 */
function isResourceFormValid(){
    return !($('#txtUtiliz').hasClass('invalid') ||
        $('#comboResource').hasClass('invalid'));
}

/**
 * Init task form.
 */
function initTaskForm(){

    $('#linkOpenResrouceForm').on('click', function(){
        $('#divResourceForm').dialog('open');
    });

    $('#linkOpenPreTaskForm').on('click', function(){
        $('#divTaskDialogForm').dialog('open');
    })

    $('#linkOpenPreEditTaskForm').on('click', function(){
        $('#divTaskDialogForm').dialog('open');
    })

    $('#txtNewTaskStartDate').datepicker();
    $('#txtNewTaskEndDate').datepicker();
    $('#txtEditTaskStartDate').datepicker();
    $('#txtEditTaskEndDate').datepicker();
    $('#txtEditTaskActualStartDate').datepicker();
    $('#txtEditTaskActualEndDate').datepicker();
}

/**
 * Init task edit form
 */
function initEditTaskForm(){
    $('#txtEditTaskStartDate').datepicker();
    $('#txtEditTaskActualStartDate').datepicker();
    $('#txtEditTaskEndDate').datepicker();
    $('#comboEditTaskParent').uniform();
}

/**
 * Reset resource form after close.
 * @param data
 */
function resetResourceDialogAfterClosed(data){
    if(data.status == 'success'){
        // Init resource form and clean everything.
        uniformElement('resourceform');
        // Remove error class.
        $('#txtUtiliz').removeClass('invalid');
        // Init form
        initTaskForm();
    }
}

function resetPreTaskDialogAfterClosed(data){
    if(data.status == 'success'){
        // Init resource form and clean everything.
        uniformElement('newTaskDialogForm');
        // Init form
        initTaskForm();
    }
}

/**
 * Reset task form after close.
 * @param data
 */
function resetTaskDialogAfterClose(data){
    if(data.status == 'success'){
        // Init task form and clean everything.
        uniformElement('taskform');

        // Remove error class
        if($('#txtNewTaskName').hasClass('invalid')) $('#txtNewTaskName').removeClass('invalid');
        if($('#txtNewTaskStartDate').hasClass('invalid')) $('#txtNewTaskStartDate').removeClass('invalid');
        if($('#txtNewTaskEndDate').hasClass('invalid')) $('#txtNewTaskEndDate').removeClass('invalid');
        if($('#comboNewTaskParent').hasClass('invalid')) $('#comboNewTaskParent').removeClass('invalid');
        if($('#comboNewTaskPred').hasClass('invalid')) $('#comboNewTaskPred').removeClass('invalid');
        if($('#taNewTaskDesc').hasClass('invalid')) $('#taNewTaskDesc').removeClass('invalid');
        // Init form
        initTaskForm();
        setTaskScrollPosition();
    }
}

/**
 * Reset task form after close.
 * @param data
 */
function resetEditTaskDialogAfterClose(data){
    if(data.status == 'success'){
        // Init task form and clean everything.
        // Remove error class
        if($('#txtEditTaskName').hasClass('invalid')) $('#txtEditTaskName').removeClass('invalid');
        if($('#txtEditTaskPercentage').hasClass('invalid')) $('#txtEditTaskPercentage').removeClass('invalid');
        if($('#txtEditTaskStartDate').hasClass('invalid')) $('#txtEditTaskStartDate').removeClass('invalid');
        if($('#txtEditTaskActualStartDate').hasClass('invalid')) $('#txtEditTaskActualStartDate').removeClass('invalid');
        if($('#txtEditTaskEndDate').hasClass('invalid')) $('#txtEditTaskEndDate').removeClass('invalid');
        if($('#txtEditTaskActualEndDate').hasClass('invalid')) $('#txtEditTaskActualEndDate').removeClass('invalid');

        setTaskScrollPosition();

        location.reload();
    }
}

/**
 * Uniform
 * @param type
 */
function uniformElement(type){

    if(type == 'taskform'){
        $('#comboNewTaskParent').uniform();
    }
    if(type == 'resourceform'){
        $('#comboResource').uniform();
    }
    if(type == 'newTaskDialogForm'){
        $('#comboNewTaskPred').uniform();
    }
}

/**
 * Set init date to new task form.
 */
function setCurrentDate(){
    var d = new Date();

    var month = d.getMonth()+1;
    var day = d.getDate();

    currentDate = d.getFullYear() + '/' +
        ((''+month).length<2 ? '0' : '') + month + '/' +
        ((''+day).length<2 ? '0' : '') + day;

    dPlusOne = new Date();
    dPlusOne.setDate(d.getDate() + 1);

    monthPlus = dPlusOne.getMonth() + 1;
    dayPlus = dPlusOne.getDate();

    currentDatePlusOne = dPlusOne.getFullYear() + '/' +
        ((''+monthPlus).length<2 ? '0' : '') + monthPlus + '/' +
        ((''+dayPlus).length<2 ? '0' : '') + dayPlus;


    $('#txtNewTaskStartDate').val(currentDate);
    $('#txtNewTaskEndDate').val(currentDatePlusOne);

}


/**
 * Get taskpanel position
 */
function rememberTaskScrollPosition(){
    taskPanelPositionLeft = $('.taskPanel').parent().scrollLeft();
    taskPanelPositionTop = $('.taskPanel').parent().scrollTop();

    $.cookie('taskPanelScrollLeft', taskPanelPositionLeft);
    $.cookie('taskPanelScrollTop', taskPanelPositionTop);
}

/**
 * Set taskpanel position
 */
function setTaskScrollPosition(){
    $('.taskPanel').parent().scrollLeft(taskPanelPositionLeft);
    $('.taskPanel').parent().scrollTop(taskPanelPositionTop);
}

/**
 * Confirm delete
 * @param event
 * @param projectId
 * @param projectName
 */
function confirmDelete(event, taskName){
    if(event.status == "success"){
        $.prompt("Are you sure want to delete task: <strong>" + taskName + "</strong> ?", {
            title: "Confirm Deletion",
            buttons: { "Delete": true, "No": false },
            top: "40%",
            submit: function(e,v,m,f){
                // use e.preventDefault() to prevent closing when needed or return false.
                // e.preventDefault();
                if(v){
                    // Delete (id been transformed to table:index:id)
//                    $('#deleteProjectIdHidden').val(projectId);
                    $('#btnEditTaskDeleteSubmit').click();
                }
            }
        });

        // Insert to right position
        $('.jqibox').insertBefore($('#divEditTaskForm'));
    }
}