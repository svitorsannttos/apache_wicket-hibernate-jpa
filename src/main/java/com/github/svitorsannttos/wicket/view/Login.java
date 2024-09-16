package com.github.svitorsannttos.wicket.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class Login  extends WebPage {

    private static final long serialVersionUID = 1L;

    public Login() {

        final TextField<String> username = new TextField<String>("username", new Model<String>());
        final PasswordTextField password = new PasswordTextField("password", new Model<String>());

        final Label errorMessage = new Label("errorMessage", Model.of("Error when logging in"));
        errorMessage.setOutputMarkupId(true).setVisible(false);

        Form<String> formLogin = new Form<String>("formLogin"){

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                String usernameValue = username.getModelObject();
                String passwordValue = password.getModelObject();
                if (usernameValue.equals("admin") && passwordValue.equals("admin")) {
                    getSession().setAttribute("username", usernameValue);
                    setResponsePage(Home.class);
                } else {
                    errorMessage.setVisible(true);
                }
            }
        };
        add(errorMessage, formLogin);
        formLogin.add(username, password);
    }
}
