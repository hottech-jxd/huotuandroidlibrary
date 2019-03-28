package com.huotu.android.library.libedittext;

import com.huotu.android.library.libedittext.EditText.Validator;

public class PhoneValidator implements Validator{

	@Override
	public boolean validate(String input) {
		return !(input.length() < 11);
	}

	@Override
	public String validateErrorMsg() {
		return "长度不对";
	}

}
