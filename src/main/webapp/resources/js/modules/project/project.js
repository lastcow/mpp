
// Variables
var projectTable;

function initComponent(){
    // Init dialog
    $('#divProjectForm').dialog({

        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,

        // Events
        close: function( event, ui ) {
            $('#btnProjectDialogClose').click();
        }
    });
/*
	//===== Active menu =====//
	activeLeftMenuItem('menuLeftProject');
*/
		// Remove tr if no data existing. It's jsf datatable natual
	if($('#inputHiddenProjectCnt').val() == '0'){
		$('#tblProject tbody tr').remove();
	}
	
	$('#btnNewProject').on('click', function(){
        showDialogButton('new');
        $('#divProjectForm').dialog('open');
	});

    init();

	formatDataTable();
										
}

/**
 * Buttons for different entry type.
 * @param name
 */
function showDialogButton(name){

    if(name == 'new'){
        $('#divProjectForm').dialog('option', 'title', 'Create new project');
        $('#divProjectForm').dialog('option', 'buttons', [
            {text: 'Create Project', click: function() { $('#btnNewProjectSubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }else if(name == 'edit'){
        $('#divProjectForm').dialog('option', 'title', 'Modify project');
        $('#divProjectForm').dialog('option', 'buttons', [
            {text: 'Modify Project', click: function() { $('#btnEditProjectSubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }
}


function confirmDelete(event, projectId, projectName){
	if(event.status == "success"){
		$.prompt("Are you sure want to delete project: <strong>" + projectName + "</strong> ?", {
			title: "Confirm Deletion",
			buttons: { "Delete": true, "No": false },
			top: "40%",
			submit: function(e,v,m,f){
				// use e.preventDefault() to prevent closing when needed or return false. 
				// e.preventDefault(); 
				if(v){
					// Delete (id been transformed to table:index:id)
					$('#deleteProjectIdHidden').val(projectId);
					$('#btnDeleteProjectHidden').click();
				}
			}
		});	
	}
}


/**
*
* Format data table
**/
function formatDataTable(){

	projectTable = $('#tblProject').dataTable({
		"bJQueryUI": true,
		"bAutoWidth": false,
		"sPaginationType": "full_numbers",
		"sDom": '<""f>t<"F"lp>'
       });
    
    // Uniform
	$('select').uniform();
}

/**
*
* Create project onevent
**/
function projectEventHandler(data){
	if(data.status == "begin"){
		// Destroy table
		projectTable.fnDestroy();
		// Loading...
	}else if(data.status == "success" ){	

		init();
        if( ! ($('#txtNewProjectName').hasClass('invalid') ||
                $('#txtNewProjectStartDate').hasClass('invalid') ||
                $('#txtProjectDesc').hasClass('invalid'))){
            $('#divProjectForm').dialog('close');
        }

		// Remove tr if no data existing. It's jsf datatable natual
		if($('#inputHiddenProjectCnt').val() == '0'){
			$('#tblProject tbody tr').remove();
		}
		
		// Reformat table.
		formatDataTable();

        // Reload
        location.reload();
	}else if(data.status == "complete"){
		
	}
}

/**
*
* Delete project onevent
**/
function deleteProjectEventHandler(data){

	if(data.status == "begin"){
		// Destroy table
		// Destroy table
		projectTable.fnDestroy();
	}else if(data.status == "success"){

		// Remove tr if no data existing. It's jsf datatable natual
		if($('#inputHiddenProjectUpdatedCnt').val() == '0'){
			$('#tblProject tbody tr').remove();
		}
	
		// Reformat table.
		formatDataTable();
	}else if(data.status == "complete"){
		// Hide newproject panel always.
	}
}

function init(){

    /**
     * Calendar picker.
     */
    $('#txtNewProjectStartDate').datepicker();
}

/**
 * Re-Init.
 */
function editProject(data){
    init();

    if(data.status == "success" ){
        // Open dialog
        showDialogButton('edit');
        $('#divProjectForm').dialog('open');
    }
}


/**
 * Reset after closed
 */
function resetDialogAfterClosed(){
    init();
    /* Remove invalid class */
    $('#txtNewProjectName').removeClass('invalid');
    $('#txtNewProjectStartDate').removeClass('invalid');
    $('#txtProjectDesc').removeClass('invalid');

    /**
     * Reset all fields (may duplicated)
     */
    // $('#projectForm :input').val('');
}