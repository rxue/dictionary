package io.github.rxue.dictionary;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {
    public static void main(String ... args) {
        System.out.println("Running main method");
        Quarkus.run(args);
    }
}
