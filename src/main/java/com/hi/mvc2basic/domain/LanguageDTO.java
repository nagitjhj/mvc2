package com.hi.mvc2basic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
public class LanguageDTO {
    private Locale locale;
}
