package dvarubla.trackertest.api

import spock.lang.Shared
import spock.lang.Specification

import static APIName.all

abstract class Spec extends Specification {
    @Shared
    public client = new APIClient("https://localhost:12347/api/")

    def cleanup() {
        client.delete(all, [:])
    }
}
