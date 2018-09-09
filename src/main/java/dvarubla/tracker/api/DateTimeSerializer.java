package dvarubla.tracker.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class DateTimeSerializer extends StdSerializer<LocalDateTime> {
    static final String DATE_TIME_FORMAT_PATTERN = "dd.MM.yyyy HH:mm";
    private static DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);

    public DateTimeSerializer() {
        this(null);
    }

    private DateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalDateTime value, JsonGenerator gen, SerializerProvider arg2
    ) throws IOException {
        gen.writeString(formatter.format(value));
    }
}
