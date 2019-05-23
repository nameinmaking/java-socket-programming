package com.nu.distsystems.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class HTMLFormatter extends Formatter {
  @Override
  public String format(LogRecord record) {
    StringBuffer stringBuffer = new StringBuffer(1000);
    stringBuffer.append("<tr>\n");

    if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
      stringBuffer.append("\t<td style=\"color:red\">");
      stringBuffer.append("<b>");
      stringBuffer.append(record.getLevel());
      stringBuffer.append("</b>");
    } else {
      stringBuffer.append("\t<td style=\"color:green\">");
      stringBuffer.append("<b>");
      stringBuffer.append(record.getLevel());
      stringBuffer.append("</b>");
    }
    stringBuffer.append("</td>\n");
    stringBuffer.append("\t<td>");
    stringBuffer.append(currentTimestamp(record.getMillis()));
    stringBuffer.append("</td>\n");
    stringBuffer.append("\t<td>");
    stringBuffer.append(formatMessage(record));
    stringBuffer.append("</td>\n");
    stringBuffer.append("</tr>\n");

    return stringBuffer.toString();
  }

  private String currentTimestamp(long millis) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
    Date currentDate = new Date(millis);
    return dateFormat.format(currentDate);
  }

  public String pageHead() {
    return "<!DOCTYPE html>\n<head>\n<style>\n"
            + "table { width: 100% }\n"
            + "th { font:bold 12pt Arial; }\n"
            + "td { font:normal 12pt Arial; }\n"
            + "h1 {font:normal 14pt Arial;}\n"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<h1>" + (new Date()) + "</h1>\n"
            + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
            + "<tr align=\"left\">\n"
            + "\t<th style=\"width:10%\">Loglevel</th>\n"
            + "\t<th style=\"width:15%\">Time</th>\n"
            + "\t<th style=\"width:75%\">Log Message</th>\n"
            + "</tr>\n";
  }

  public String pageBodyEnd() {
    return "</table>\n</body>\n</html>";
  }
}
