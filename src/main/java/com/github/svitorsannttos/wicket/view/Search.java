package com.github.svitorsannttos.wicket.view;

import com.github.svitorsannttos.wicket.dao.StudentDAO;
import com.github.svitorsannttos.wicket.model.domain.Student;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import java.util.List;

public class Search extends Base {

	private static final long serialVersionUID = 1L;

	public Search() {
		StudentDAO studentDAO = new StudentDAO();
		Form<String> formSearch = new Form<String>("formSearch");
		add(formSearch);

		TextField<String> searchName = new TextField<>("searchName", new Model<String>());
		formSearch.add(searchName);

		WebMarkupContainer divResults = new WebMarkupContainer("divResults");
		divResults.setVisible(false);
		divResults.setOutputMarkupPlaceholderTag(true);
		add(divResults);

		PropertyListView<Student> listResults = new PropertyListView<Student>("listResults", new ListModel<Student>()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Student> item) {
				item.add(new Label("firstName"));
				item.add(new Label("lastName"));
				item.add(new Label("gender"));
				item.add(new Label("age"));
				item.add(new Label("email"));
				item.add(new Label("phone"));
				item.add(new Label("address"));
				item.add(new Label("city"));
				item.add(new Label("uf"));
				item.add(new Link<Void>("edit") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(new Edit(item.getModelObject()));
					}
				});
			}
		};

		divResults.add(listResults);

		AjaxButton searchButton = new AjaxButton("searchButton", formSearch) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				List<Student> students = studentDAO.findAll();
				listResults.setModelObject(students);
				divResults.setVisible(true);
				target.add(divResults);
			}
		};
		formSearch.add(searchButton);
	}
}
