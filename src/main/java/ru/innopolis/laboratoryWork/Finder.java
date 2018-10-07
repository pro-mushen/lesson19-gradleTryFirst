package ru.innopolis.laboratoryWork;

import java.io.IOException;

public interface Finder {
    void getOccurencies(String[] sources, String[] words, String res) throws IOException;
}
