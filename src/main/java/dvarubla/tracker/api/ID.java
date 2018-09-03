package dvarubla.tracker.api;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
class ID {
    @NonNull
    @Getter
    @Setter
    private Long id;

}
