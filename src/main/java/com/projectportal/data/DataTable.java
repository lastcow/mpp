package com.projectportal.data;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Named;

@Named
@RequestScoped
public class DataTable {

	private HtmlDataTable projectDataTable;
	
	public DataTable() {
		// TODO Auto-generated constructor stub
	}

	public HtmlDataTable getProjectDataTable() {
		return projectDataTable;
	}

	public void setProjectDataTable(HtmlDataTable projectDataTable) {
		this.projectDataTable = projectDataTable;
	}

	
}
