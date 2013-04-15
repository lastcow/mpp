
var rootPath;
var ganttChartControl;
var taskPanelPositionLeft = 0;
var taskPanelPositionTop = 0;
/**
 * Init
 */
function initComponent(){

	rootPath = $('#hiddenRoot').val();

    $('#wbstype').html('WBS - Baseline');
	
	$('#btnNewTask').on('click', function(){
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
	
	
    // Initialize Gantt data structures
    //project 1
/*
    var project1 = new GanttProjectInfo(1, "Applet redesign", new Date(2010, 5, 11));
    var parentTask1 = new GanttTaskInfo(1, "Old code review", new Date(2010, 5, 11), 208, 50, "");
    parentTask1.addChildTask(new GanttTaskInfo(2, "Convert to J#", new Date(2010, 5, 11), 100, 40, ""));
    parentTask1.addChildTask(new GanttTaskInfo(13, "Add new functions", new Date(2010, 5, 12), 80, 90, ""));
    var parentTask2 = new GanttTaskInfo(3, "Hosted Control", new Date(2010, 6, 7), 190, 80, "1");
    var parentTask5 = new GanttTaskInfo(5, "J# interfaces", new Date(2010, 6, 14), 60, 70, "6");
    var parentTask123 = new GanttTaskInfo(123, "use GUIDs", new Date(2010, 6, 14), 60, 70, "");
    parentTask5.addChildTask(parentTask123);
    parentTask2.addChildTask(parentTask5);
    parentTask2.addChildTask(new GanttTaskInfo(6, "Task D", new Date(2010, 6, 10), 30, 80, "14"));
    var parentTask4 = new GanttTaskInfo(7, "Unit testing", new Date(2010, 6, 15), 118, 80, "6");
    var parentTask8 = new GanttTaskInfo(8, "core (com)", new Date(2010, 6, 15), 100, 10, "");
    parentTask8.addChildTask(new GanttTaskInfo(55555, "validate uids", new Date(2010, 6, 20), 60, 10, ""));
    parentTask4.addChildTask(parentTask8);
    parentTask4.addChildTask(new GanttTaskInfo(9, "Stress test", new Date(2010, 6, 15), 80, 50, ""));
    parentTask4.addChildTask(new GanttTaskInfo(10, "User interfaces", new Date(2010, 6, 16), 80, 10, ""));
    parentTask2.addChildTask(parentTask4);
    parentTask2.addChildTask(new GanttTaskInfo(11, "Testing, QA", new Date(2010, 6, 21), 60, 100, "6"));
    parentTask2.addChildTask(new GanttTaskInfo(12, "Task B (Jim)", new Date(2010, 6, 8), 110, 1, "14"));
    parentTask2.addChildTask(new GanttTaskInfo(14, "Task A", new Date(2010, 6, 7), 8, 10, ""));
    parentTask2.addChildTask(new GanttTaskInfo(15, "Task C", new Date(2010, 6, 9), 110, 90, "14"));
    project1.addTask(parentTask1);
    project1.addTask(parentTask2);
    //project 2
    var project2 = new GanttProjectInfo(2, "Web Design", new Date(2010, 5, 17));
    var parentTask22 = new GanttTaskInfo(62, "Fill HTML pages", new Date(2010, 5, 17), 157, 50, "");
    parentTask22.addChildTask(new GanttTaskInfo(63, "Cut images", new Date(2010, 5, 22), 78, 40, ""));
    parentTask22.addChildTask(new GanttTaskInfo(64, "Manage CSS", null, 90, 90, ""));
    project2.addTask(parentTask22);
    var parentTask70 = new GanttTaskInfo(70, "PHP coding", new Date(2010, 5, 18), 120, 10, "");
    parentTask70.addChildTask(new GanttTaskInfo(71, "Purchase D control", new Date(2010, 5, 18), 50, 0, ""));
    project2.addTask(parentTask70);
*/
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
        $('#wbstype').html('WBS - Baseline');
        loadBaseline();
    } ) ;

    $('#btnTriggleActual').on('click', function(){
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
        });
}

/**
 *
 * @param task
 */
function onTaskClick(task){

    rememberTaskScrollPosition();
    // Assign id to hidden field
    $('#editTaskId').val(task.getId());
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
        initComponent();
        if(isEditTaskFormValid()){
            $('#divEditTaskForm').dialog('close');
        }

        $('#btnResetTaskPanelPosition').click();
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
        // Setup progress
//        progressBarProcess('pbar', $('#txtEditTaskPercentage').val()+'%');
        initComponent();
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
        $('#txtEditTaskStartDate').hasClass('invalid'));
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
        uniformElement('newTaskDialogForm');
        // Remove error class
        if($('#txtNewTaskName').hasClass('invalid')) $('#txtNewTaskName').removeClass('invalid');
        if($('#txtNewTaskStartDate').hasClass('invalid')) $('#txtNewTaskStartDate').removeClass('invalid');
        if($('#txtNewTaskEndDate').hasClass('invalid')) $('#txtNewTaskEndDate').removeClass('invalid');
        if($('#comboNewTaskParent').hasClass('invalid')) $('#comboNewTaskParent').removeClass('invalid');
        if($('#comboNewTaskPred').hasClass('invalid')) $('#comboNewTaskPred').removeClass('invalid');
        if($('#taNewTaskDesc').hasClass('invalid')) $('#taNewTaskDesc').removeClass('invalid');
        // Init form
        initTaskForm();
    }
}

/**
 * Reset task form after close.
 * @param data
 */
function resetEditTaskDialogAfterClose(data){
    if(data.status == 'success'){
        // Init task form and clean everything.
        uniformElement('taskform');
        uniformElement('newTaskDialogForm');
        // Remove error class
        if($('#txtEditTaskName').hasClass('invalid')) $('#txtEditTaskName').removeClass('invalid');
        if($('#txtEditTaskPercentage').hasClass('invalid')) $('#txtEditTaskPercentage').removeClass('invalid');
        if($('#txtEditTaskStartDate').hasClass('invalid')) $('#txtEditTaskStartDate').removeClass('invalid');
        if($('#txtEditTaskActualStartDate').hasClass('invalid')) $('#txtEditTaskActualStartDate').removeClass('invalid');
        if($('#txtEditTaskEndDate').hasClass('invalid')) $('#txtEditTaskEndDate').removeClass('invalid');
        if($('#txtEditTaskActualEndDate').hasClass('invalid')) $('#txtEditTaskActualEndDate').removeClass('invalid');
        // Init form
        initTaskForm();
    }
}

/**
 * Uniform
 * @param type
 */
function uniformElement(type){

    if(type == 'taskform'){
        $('#comboNewTaskParent').uniform();
        $('#comboNewTaskPred').uniform();
    }
    if(type == 'resourceform'){
        $('#comboResource').uniform();
    }
    if(type == 'newTaskDialogForm'){
        $('#comboNewTaskPred').uniform();
    }
}


/**
 * Get taskpanel position
 */
function rememberTaskScrollPosition(){
    taskPanelPositionLeft = $('.taskPanel').parent().scrollLeft();
    taskPanelPositionTop = $('.taskPanel').parent().scrollTop();
}

/**
 * Set taskpanel position
 */
function setTaskScrollPosition(){
    $('.taskPanel').parent().scrollLeft(taskPanelPositionLeft);
    $('.taskPanel').parent().scrollTop(taskPanelPositionTop);
}