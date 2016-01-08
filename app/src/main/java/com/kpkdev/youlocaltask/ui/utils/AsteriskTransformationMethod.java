package com.kpkdev.youlocaltask.ui.utils;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by krasimir.karamazov on 1/5/2016.
 */
public class AsteriskTransformationMethod extends PasswordTransformationMethod{

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new AsteriskCharSequence(source);
    }

    private class AsteriskCharSequence implements CharSequence {
        private CharSequence sourceSequence;

        public AsteriskCharSequence(CharSequence source) {
            sourceSequence = source;
        }

        @Override
        public int length() {
            return sourceSequence.length();
        }

        @Override
        public char charAt(int i) {
            return '*';
        }

        @Override
        public CharSequence subSequence(int i, int i1) {
            return sourceSequence.subSequence(i, i1);
        }
    }
}
