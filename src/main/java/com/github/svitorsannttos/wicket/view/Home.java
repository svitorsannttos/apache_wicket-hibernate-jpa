package com.github.svitorsannttos.wicket.view;

import org.apache.wicket.model.Model;
import org.apache.wicket.markup.html.basic.Label;

public class Home extends Base {

    private static final long serialVersionUID = 1L;

    public Home() {
        Label labelHelloWord = new Label("welcome", Model.of("Seja bem vindo!"));
        add(labelHelloWord);
    }
}
