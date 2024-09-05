package choi.choice.framework.systems;

import lombok.Getter;

public enum Language {
    KOREAN("ko"), ENGLISH("en"), CHINESS("cn");

    @Getter
    private String code;

    Language(String code) {this.code = code;}
}
