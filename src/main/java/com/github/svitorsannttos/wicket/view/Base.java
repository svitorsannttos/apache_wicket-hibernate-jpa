package com.github.svitorsannttos.wicket.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class Base extends WebPage {

	private static final long serialVersionUID = 1L;

	public Base() {
		String username = (String) getSession().getAttribute("username");
		if (username == null) {
			setResponsePage(getApplication().getHomePage());
			return;
		}

		add(new Link<Void>("exit"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				getSession().invalidate();
				setResponsePage(getApplication().getHomePage());
			}
		});
	}

}
