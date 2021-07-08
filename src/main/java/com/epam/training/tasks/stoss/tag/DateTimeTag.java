package com.epam.training.tasks.stoss.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DateTimeTag extends TagSupport {

private static final Logger LOGGER = Logger.getLogger(DateTimeTag.class);

private static final String COLON_DELIMITER = ":";
private static final String DOT_DELIMITER = ".";
private static final String DASH_DELIMITER = "-";
private static final String WHITESPACE_DELIMITER = " ";
private static final String ANTE_MERIDIEM = "AM";
private static final String POST_MERIDIEM = "PM";
private static final String EN_LANGUAGE_TAG = "en";
private static final String RU_LANGUAGE_TAG = "ru";
private static final String BY_LANGUAGE_TAG = "by";
private static final int AM_PM_INDICATOR = 12;

private String date;
private String time;
private String locale;

public void setDate (String date) {
    this.date = date;
}
public void setTime ( String time) {
    this.time = time;
}

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if ((date != null) && (time != null) && (locale != null)) {
                String formattedDate = "";
                String formattedTime = "";
                String hours = time.substring(0,2);
                String minutes = time.substring(3,5);
                String seconds = time.substring(6,8);
                String year = date.substring(0,4);
                String month = date.substring(5,7);
                String day = date.substring(8,10);

                switch (locale) {
                    case EN_LANGUAGE_TAG: {

                        formattedTime = formatEnTime(hours, minutes, seconds);
                        formattedDate = year +
                                DASH_DELIMITER +
                                month +
                                DASH_DELIMITER +
                                day;
                        break;
                    }
                    case RU_LANGUAGE_TAG:
                    case BY_LANGUAGE_TAG: {
                        formattedTime = hours +
                                COLON_DELIMITER +
                                minutes +
                                COLON_DELIMITER +
                                seconds;
                        formattedDate = day +
                                DOT_DELIMITER +
                                month +
                                DOT_DELIMITER +
                                year;
                        break;
                    }
                    default: {
                        formattedTime = time;
                        formattedDate = date;
                    }
                }
                LOGGER.debug(formattedDate);
                LOGGER.debug(formattedTime);
                pageContext.getOut().write("<p>" + formattedDate +  "</p>");
                pageContext.getOut().write("<p>" + formattedTime + "</p>");
            } else {
                pageContext.getOut().write("<p class=>failed to get date and time</p>");
            }
        } catch (IOException e) {
            LOGGER.error(e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private String formatEnTime(String hours, String minutes, String seconds) {

    StringBuilder stringBuilder = new StringBuilder();
    int hourValue = Integer.parseInt(hours);
        if (Integer.parseInt(hours) > AM_PM_INDICATOR) {
            stringBuilder.append(hourValue-AM_PM_INDICATOR);
            stringBuilder.append(COLON_DELIMITER);
            stringBuilder.append(minutes);
            stringBuilder.append(COLON_DELIMITER);
            stringBuilder.append(seconds);
            stringBuilder.append(WHITESPACE_DELIMITER);
            stringBuilder.append(POST_MERIDIEM);
        } else {
            stringBuilder.append(hourValue);
            stringBuilder.append(COLON_DELIMITER);
            stringBuilder.append(minutes);
            stringBuilder.append(COLON_DELIMITER);
            stringBuilder.append(seconds);
            stringBuilder.append(WHITESPACE_DELIMITER);
            stringBuilder.append(ANTE_MERIDIEM);
        }
        return stringBuilder.toString();
    }






}
