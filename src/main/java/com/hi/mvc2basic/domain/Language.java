package com.hi.mvc2basic.domain;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum Language {
    KOREA("우리말", Locale.KOREA)
    , ENGLISH("영어", Locale.ENGLISH)
    , GERMAN("독일어", Locale.GERMAN)
    ;

    private String lang;
    private Locale locale;

    Language(String lang, Locale locale) {
        this.lang = lang;
        this.locale = locale;
    }
}
