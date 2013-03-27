
var rootPath;
Ext.ns('App');

Ext.require([
    'Gnt.panel.Gantt',
    'Gnt.column.PercentDone',
    'Gnt.column.StartDate',
    'Gnt.column.EndDate',
    'Sch.plugin.TreeCellEditing'
]);

Ext.onReady( function()
    {
        rootPath = $('#hiddenRoot').val();
        Ext.define("TaskModelWithBaseline", {
            extend : "Gnt.model.Task",
            baselineStartDateField : 'BaselineStartDate',
            baselineEndDateField : 'BaselineEndDate',
            baselinePercentDoneField : 'BaselinePercentDone',

            // Some additional fields for baseline calculation
            fields : [
                {name : 'BaselineStartDate', type : 'date', dateFormat : 'Y-m-d' },
                {name : 'BaselineEndDate', type : 'date', dateFormat : 'Y-m-d' },
                {name : 'BaselinePercentDone'}
            ]
        });

        App.Gantt.init();
    });

App.Gantt = {
    init: function (serverCfg){
        Ext.QuickTips.init();

        var taskStore = Ext.create("Gnt.data.TaskStore", {
            model       : 'TaskModelWithBaseline',
            autoLoad    : true,
//            proxy       : {
//                type    : 'ajax',
//                method  : 'POST',
//                reader  : {
//                type    : 'json'
//                },
//                api     : {
//                    read    :   rootPath+'/rest/project/getprojectwbs/'+projectId,
//                    create  :   rootPath+'/rest/project/getprojectwbs/'+projectId,
//                    destroy :   rootPath+'/rest/project/getprojectwbs/'+projectId,
//                    update  :   rootPath+'/rest/project/getprojectwbs/'+projectId,
//
//                },
//                writer  : {
//                    type    :   'json',
//                    root    :   'data',
//                    encode  :   true,
//                    allowSingle :   false
//                }

            proxy :{
                type : 'memory',
                render : {
                    type : 'json'
                },
                data: [
                    {
                        "StartDate" : "2010-01-18",
                        "EndDate" : "2010-02-02",
                        "Id" : 1,
                        "Name" : "Planning",
                        "expanded" : true,
                        "children" : [
                            {
                                "StartDate" : "2010-01-18",
                                "EndDate" : "2010-01-26",
                                "Id" : 2,
                                "leaf" : true,
                                "Name" : "Investigate1",
                                "parentId" : 1,
                                "BaselineStartDate": "2010-01-20",
                                "BaselineEndDate" : "2010-01-27"
                            },
                            {
                                "StartDate" : "2010-01-22",
                                "EndDate" : "2010-01-25",
                                "Id" : 3,
                                "leaf" : true,
                                "Name" : "Investigate2",
                                "parentId" : 1
                            },
                            {
                                "StartDate" : "2010-02-01",
                                "EndDate" : "2010-02-05",
                                "Id" : 4,
                                "leaf" : true,
                                "Name" : "Investigate3",
                                "parentId" : 1
                            },
                            {
                                "StartDate" : "2010-02-03",
                                "EndDate" : "2010-02-08",
                                "Id" : 5,
                                "leaf" : true,
                                "Name" : "Investigate4",
                                "parentId" : 1
                            },
                            {
                                "StartDate" : "2010-02-10",
                                "EndDate" : "2010-02-13",
                                "Id" : 6,
                                "leaf" : true,
                                "Name" : "Investigate5",
                                "parentId" : 1
                            },
                            {
                                "StartDate" : "2010-02-20",
                                "EndDate" : "2010-02-28",
                                "Id" :7,
                                "leaf" : true,
                                "Name" : "Investigate6",
                                "parentId" : 1
                            },
                            {
                                "StartDate" : "2010-01-28",
                                "EndDate" : "2010-01-28",
                                "Id" : 8,
                                "leaf" : true,
                                "Name" : "Investigate7",
                                "parentId" : 1
                            }
                        ]
                    }
                ]
                // eof data
            }
        });

        var dependencyStore = Ext.create("Gnt.data.DependencyStore", {
            autoLoad : true,
            proxy: {
                type : 'memory',
                reader: {
                    type : 'json'
                }
            },

            data: [
                {
                    "From":2,
                    "To":4,
                    "Type":2,
                    "Id"	: 1
                },
                {
                    "From":3,
                    "To":4,
                    "Type":2,
                    "Id"	: 2
                },
                {
                    "From":3,
                    "To":5,
                    "Type":2,
                    "Id"	: 3
                },
                {
                    "From":3,
                    "To":6,
                    "Type":2,
                    "Id"	: 4
                },
                {
                    "From":3,
                    "To":7,
                    "Type":2,
                    "Id"	: 5
                }
            ]
        });

        var g = Ext.create( 'Gnt.panel.Gantt', {
            height      : 400,
            renderTo    : 'ExtjsGantt',

            viewPreset      : 'weekAndDayLetter',

            enableBaseline              : true,
            baselineVisible             : true,

            startDate       : new Date(2010, 0, 15),
            endDate         : Sch.util.Date.add(new Date(2010, 0, 15), Sch.util.Date.WEEK, 10),

            columns         : [
                {
                    xtype       : 'treecolumn',
                    header      : 'Tasks',
                    sortable    : false,
                    dataIndex   : 'Name',
                    width       : 200
                }
            ],

            taskStore       : taskStore,
            dependencyStore : dependencyStore,

            tbar : [
                {
                    text : 'Show baseline',
                    enableToggle : true,
                    pressed : true,
                    handler : function() {
                        g.el.toggleCls('sch-ganttpanel-showbaseline');
                    }
                }
            ]
        });
    }
};