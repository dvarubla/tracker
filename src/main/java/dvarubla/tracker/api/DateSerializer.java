package dvarubla.tracker.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class DateSerializer extends StdSerializer<LocalDate> {
    static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
    private static DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    public DateSerializer() {
        this(null);
    }

    private DateSerializer(Class<LocalDate> t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalDate value, JsonGenerator gen, SerializerProvider arg2
    ) throws IOException {
        gen.writeString(formatter.format(value));
    }
}
