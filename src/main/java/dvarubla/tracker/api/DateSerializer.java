package dvarubla.tracker.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateSerializer extends StdSerializer<LocalDateTime> {
    static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy HH:mm";
    private static DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    public DateSerializer() {
        this(null);
    }

    private DateSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalDateTime value, JsonGenerator gen, SerializerProvider arg2
    ) throws IOException {
        gen.writeString(formatter.format(value));
    }
}
