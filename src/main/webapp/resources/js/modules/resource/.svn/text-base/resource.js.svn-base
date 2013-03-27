
var resourceTable;

function initComponent(){

	//===== Active menu =====//
	activeLeftMenuItem('menuLeftResource');

    // Remove tr if no data existing. It's jsf datatable natual
    if($('#inputHiddenResourceCnt').text() == '0'){
        $('#tblResource tbody tr').remove();
    }


    // Init dialog
    $('#divResourceForm').dialog({

        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,

        // Events
        close: function( event, ui ) {
            $('#btnResourceDialogClose').click();
        }
    });

	
	$('#btnNewResource').on('click', function(){
        showDialogButton('new');
		$('#divResourceForm').dialog('open');
	});
	
	// Buttons.
	$('#btnNewResourceCancel').on('click', function(){
		$('#resourceForm').validationEngine('hideAll');
		$('#divNewResource').hide();
	});
	
	$('#hrefNewResourcePanelClose').on('click', function(){
		$('#divNewResource').hide();
	});

    formatDataTable();
    // Uniform
    $('#tblDiv select').uniform();

}

/**
 * Uniform dialog form.
 */
function uniformDialog(){
    $('#divResourceForm select').uniform();
}

function formatDataTable(){

    resourceTable = $('#tblResource').dataTable({
        "bJQueryUI": true,
        "bAutoWidth": false,
        "sPaginationType": "full_numbers",
        "sDom": '<""f>t<"F"lp>',
        "aoColumns": [
            {
                sWidth: '250px'
            },
            null,
            {
                sWidth: '100px',
                bSortable: false
            }
        ]
    });
}

/**
 * Buttons for different entry type.
 * @param name
 */
function showDialogButton(name){

    if(name == 'new'){
        $('#divResourceForm').dialog('option', 'title', 'Create new resource');
        $('#divResourceForm').dialog('option', 'buttons', [
            {text: 'Create Resource', click: function() { $('#validateFlag').val('True'); $('#btnResourceSubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }else if(name == 'edit'){
        $('#divResourceForm').dialog('option', 'title', 'Modify resource');
        $('#divResourceForm').dialog('option', 'buttons', [
            {text: 'Modify Resource', click: function() { $('#validateFlag').val('False'); $('#btnEditResourceSubmit').click(); } },
            {text: 'Cancel', click: function() { $(this).dialog('close'); } }
        ]);
    }
}

/**
 * Reset dialog
 */
function resetDialogAfterClosed(){
    // Remove invalid class;

    $('#txtNewResourceFirstName').removeClass('invalid');
    $('#txtResourceEmail').removeClass('invalid');
    $('#txtResourceName').removeClass('invalid');
    $('#cmbDepartment').removeClass('invalid');

    uniformDialog();
}

/**
 * Handle resource event.
 * @param data
 */
function resourceEventHandler(data){
    if(data.status == "begin"){
        // Destroy table
        resourceTable.fnDestroy();
        // Loading...
    }else if(data.status == "success" ){

//        init();
        var validForm = ! ($('#txtNewResourceFirstName').hasClass('invalid') ||
            $('#txtResourceEmail').hasClass('invalid') ||
            $('#txtResourceName').hasClass('invalid') ||
            $('#cmbDepartment').hasClass('invalid'));

        if( validForm ){
            $('#divResourceForm').dialog('close');
        }

        // Remove tr if no data existing. It's jsf datatable natual
        if($('#inputHiddenResourceCnt').text() == '0'){
            $('#tblResource tbody tr').remove();
        }

        // Reformat table.
        formatDataTable();

        // Uniform
        $('select').uniform();

    }else if(data.status == "complete"){

    }
}

/**
 * Confirm
 * @param event
 * @param projectId
 * @param projectName
 */
function confirmDelete(event, userId, firstName){
    if(event.status == "success"){
        $.prompt("Are you sure want to delete user: <strong>" + firstName + "</strong> ?", {
            title: "Confirm Deletion",
            buttons: { "Delete": true, "No": false },
            top: "40%",
            submit: function(e,v,m,f){
                // use e.preventDefault() to prevent closing when needed or return false.
                // e.preventDefault();
                if(v){
                    $('#deleteUserIdHidden').val(userId);
                    $('#btnDeleteUserHidden').click();
                }
            }
        });
    }
}

/**
 * Delete user event handler
 * @param data
 */
function deleteUserEventHandler(data){

    if(data.status == "begin"){
        // Destroy table
        // Destroy table
        resourceTable.fnDestroy();
    }else if(data.status == "success"){

        // Remove tr if no data existing. It's jsf datatable natual
        if($('#inputHiddenResourceCnt').text() == '0'){
            $('#tblResource tbody tr').remove();
        }

        // Reformat table.
        formatDataTable();

        $('select').uniform();
    }else if(data.status == "complete"){
        // Hide newproject panel always.
    }
}

/**
 * Set selected resource and open resource edit dialog
 * @param data
 */
function editResource(data){
    if(data.status == "success"){
        uniformDialog();
        showDialogButton("edit");
        $('#divResourceForm').dialog('open');
    }
}