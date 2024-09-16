package com.github.svitorsannttos.wicket.view;

import com.github.svitorsannttos.wicket.model.domain.Student;
import org.apache.wicket.markup.html.basic.Label;

public class Edit extends Register{

    public Edit(Student student) {
        super(student);
        replace(new Label("title", "Edit Student"));
    }
}
