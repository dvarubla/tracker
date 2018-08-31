package dvarubla.tracker.api;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
class CreatedID {
    @NonNull
    @Getter
    @Setter
    private Long id;

}
