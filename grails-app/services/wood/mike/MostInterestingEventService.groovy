package wood.mike

import grails.gorm.transactions.Transactional
import grails.plugin.cache.CacheEvict
import grails.plugin.cache.CachePut
import grails.plugin.cache.Cacheable
import org.grails.plugin.cache.GrailsCacheManager

import java.time.LocalDate

@Transactional
class MostInterestingEventService {

    GrailsCacheManager grailsCacheManager

    @Cacheable(value = 'interestingEvents', key = { date })
    def get(LocalDate date) {
        log.info("Adding event $date")
        MostInterestingEvent.findByDate(date)
    }

    @CachePut(value='interestingEvents', key = { event.date })
    def save( MostInterestingEvent event ) {
        event.save()
    }

    @CacheEvict(value='interestingEvents', key = { event.date })
    def delete( MostInterestingEvent event ) {
        event.delete()
    }

    def list(params) {
        MostInterestingEvent.list(params)
    }

    def count() {
        MostInterestingEvent.count
    }

    def getCache() {
        grailsCacheManager.getCache('interestingEvents')
    }
}
