package com.hill.webui.utilities;

import com.hill.webui.core.PropertyReader;

public interface Timeouts {

    float FACTOR = Float.parseFloat(PropertyReader.get("timeouts.factor"));

    int TITLE = (int) (4 * FACTOR);
    int ELEMENT_VISIBILITY = (int) (15 * FACTOR);
    int SUBSCRIBE_WINDOW = (int) (25 * FACTOR);
    int ALERT = (int) (15 * FACTOR);
    int TEXT_TYPE = (int) (6 * FACTOR);
    int POPUP_TO_BE_SHOWN = (int) (7 * FACTOR);
    int PAGE_LOADING = (int) (45 * FACTOR);
    int JS_EXECUTION = (int) (15 * FACTOR);
    int COOKIE_ACCEPT = (int) (15 * FACTOR);
    int ACCEPTED_DISAPPEAR = (int) (4 * FACTOR);



}
