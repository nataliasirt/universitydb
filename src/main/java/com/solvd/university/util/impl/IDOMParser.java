package com.solvd.university.util.impl;

import org.w3c.dom.Element;

public interface IDOMParser<T> {
    T parse(String filePath);
    String getElementValue(Element parentElement, String elementName);
}
