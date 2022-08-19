package wood.mike

import grails.validation.ValidationException

import java.time.LocalDate

import static org.springframework.http.HttpStatus.*

class MostInterestingEventController {

    MostInterestingEventService mostInterestingEventService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def viewCache() {
        [cache: mostInterestingEventService.getCache()]
    }

    def clearCache() {
        mostInterestingEventService.getCache().clear()
        flash.message = 'Cache cleared successfully'
        redirect(action: 'viewCache')
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond mostInterestingEventService.list(params), model:[mostInterestingEventCount: mostInterestingEventService.count()]
    }

    def show( String date ) {
        respond mostInterestingEventService.get(LocalDate.parse(date))
    }

    def create() {
        respond new MostInterestingEvent(params)
    }

    def save(MostInterestingEvent mostInterestingEvent) {
        if (mostInterestingEvent == null) {
            notFound()
            return
        }

        try {
            mostInterestingEventService.save(mostInterestingEvent)
        } catch (ValidationException e) {
            respond mostInterestingEvent.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mostInterestingEvent.label', default: 'MostInterestingEvent'), mostInterestingEvent.id])
                redirect( action: 'show', params: [date: mostInterestingEvent.date.toString()] )
            }
            '*' { respond mostInterestingEvent, [status: CREATED] }
        }
    }

    def edit(LocalDate date) {
        respond mostInterestingEventService.get(date)
    }

    def update(MostInterestingEvent mostInterestingEvent) {
        if (mostInterestingEvent == null) {
            notFound()
            return
        }

        try {
            mostInterestingEventService.save(mostInterestingEvent)
        } catch (ValidationException e) {
            respond mostInterestingEvent.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mostInterestingEvent.label', default: 'MostInterestingEvent'), mostInterestingEvent.id])
                redirect mostInterestingEvent
            }
            '*'{ respond mostInterestingEvent, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        mostInterestingEventService.delete(MostInterestingEvent.get(id))

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mostInterestingEvent.label', default: 'MostInterestingEvent'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mostInterestingEvent.label', default: 'MostInterestingEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
