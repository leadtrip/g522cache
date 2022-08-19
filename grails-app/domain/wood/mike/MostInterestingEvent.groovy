package wood.mike

import java.time.LocalDate

class MostInterestingEvent {

    LocalDate date
    String description

    static constraints = {
        date unique: true, nullable: false
        description blank: false, nullable: false
    }

    String toString(){
        "$date - $description"
    }

}
