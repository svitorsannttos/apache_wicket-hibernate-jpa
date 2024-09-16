package com.github.svitorsannttos.wicket;

import com.github.svitorsannttos.wicket.view.Login;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Login.class;
	}

	@Override
	public void init()
	{
		super.init();
    }

}
