package com.github.svitorsannttos.wicket.view;

import com.github.svitorsannttos.wicket.dao.StudentDAO;
import com.github.svitorsannttos.wicket.model.domain.Student;
import com.github.svitorsannttos.wicket.model.dto.ZipCodeDTO;
import com.github.svitorsannttos.wicket.model.enums.GenderEnum;
import com.github.svitorsannttos.wicket.service.api.ViaCep;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.Arrays;

public class Register extends Base {

	private static final long serialVersionUID = 1L;

	public Register(){
		this(new Student());
	}

	public Register(Student student) {
		add(new Label("title", "Register Student"));
		StudentDAO studentDAO = new StudentDAO();
		CompoundPropertyModel<Student> compoundPropertyModel = new CompoundPropertyModel<Student>(student);
		Form<Student> form = new Form<Student>("formStudent", compoundPropertyModel){

			@Override
			protected void onSubmit() {
				Class<?> classSession = getPageClass();
				if(classSession == Register.class){
					studentDAO.save(getModelObject());
					setResponsePage(Home.class);
				} else if (classSession == Edit.class) {
					studentDAO.update(getModelObject());
					setResponsePage(Home.class);
				}
			}
		};
		add(form);

		TextField<String> inputFirstName = new TextField<String>("firstName");
		TextField<String> inputLastName = new TextField<String>("lastName");
		TextField<String> inputAge = new TextField<String>("age");
		TextField<String> inputEmail = new TextField<String>("email");
		TextField<String> inputPhone = new TextField<String>("phone");
		TextField<String> inputAddress = new TextField<String>("address");
		TextField<String> inputCity = new TextField<String>("city");
		TextField<String> inputUf = new TextField<String>("uf");
		TextField<String> inputZipCode = new TextField<String>("zipCode", new Model<>());

		inputAddress.setEnabled(false);
		inputCity.setEnabled(false);
		inputUf.setEnabled(false);

		inputAddress.setOutputMarkupId(true);
		inputCity.setOutputMarkupId(true);
		inputUf.setOutputMarkupId(true);

		inputZipCode.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
                try {
                    ZipCodeDTO address = ViaCep.findZipCode(inputZipCode.getValue());
					inputAddress.setModelObject(address.getLogradouro());
					inputCity.setModelObject(address.getLocalidade());
					inputUf.setModelObject(address.getUf());
					target.add(inputAddress, inputCity, inputUf);
                } catch (Exception e) {
                    inputAddress.setEnabled(true);
					inputCity.setEnabled(true);
					inputUf.setEnabled(true);
					target.add(inputAddress, inputCity, inputUf);
                }
            }
		});

		DropDownChoice<GenderEnum> comboGender = new DropDownChoice<>("gender", Arrays.asList(GenderEnum.values()), new ChoiceRenderer<GenderEnum>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(GenderEnum object) {
				return object.getLabel();
			}

			@Override
			public String getIdValue(GenderEnum object, int index) {
				return object.name();
			}
		});
		form.add(inputFirstName, inputLastName, inputAge, inputEmail, inputPhone, inputAddress, inputCity, inputUf, inputZipCode, comboGender);

		inputFirstName.setLabel(Model.of("First Name")).setRequired(true).add(StringValidator.lengthBetween(2,10));
		inputLastName.setLabel(Model.of("Last Name")).setRequired(true).add(StringValidator.lengthBetween(2,10));
		inputEmail.setLabel(Model.of("Email")).add(EmailAddressValidator.getInstance());

		add(new FeedbackPanel("feedbackError", new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
	}
}
