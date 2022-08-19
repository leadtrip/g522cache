package g522cache

import grails.gorm.transactions.Transactional
import wood.mike.MostInterestingEvent

import java.time.LocalDate

class BootStrap {

    def init = { servletContext ->
        addEvents()
    }

    @Transactional
    def addEvents() {
        new MostInterestingEvent(date: LocalDate.now().minusDays(1), description: 'Bought milk').save()
        new MostInterestingEvent(date: LocalDate.now().minusDays(2), description: 'Plumped a cushion').save()
        new MostInterestingEvent(date: LocalDate.now().minusDays(3), description: 'Scored from the half way line').save()
        new MostInterestingEvent(date: LocalDate.now().minusDays(4), description: 'Rescued a crab').save()
        new MostInterestingEvent(date: LocalDate.now().minusDays(5), description: 'Died').save()
        new MostInterestingEvent(date: LocalDate.now().minusDays(6), description: 'Created universe').save()
        new MostInterestingEvent(date: LocalDate.now().minusDays(7), description: 'Woke up').save()
    }

    def destroy = {
    }
}
