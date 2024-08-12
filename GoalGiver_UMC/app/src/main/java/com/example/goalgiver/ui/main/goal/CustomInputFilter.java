package com.example.goalgiver.ui.main.goal;

import android.text.InputFilter;
import android.text.Spanned;

public class CustomInputFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        // 이미 이모지가 입력되어 있다면, 추가 입력을 막음
        if (dest.length() > 0) {
            return "";  // 아무 것도 반환하지 않아 추가 입력을 막음
        }

        for (int i = start; i < end; i++) {
            int type = Character.getType(source.charAt(i));
            if (type != Character.SURROGATE && type != Character.OTHER_SYMBOL) {
                return "";  // 이모지가 아닌 다른 문자는 허용하지 않음
            }
        }

        return source;  // 이모지 입력을 허용
    }
}
