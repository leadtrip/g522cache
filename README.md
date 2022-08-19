Grails app that makes use of the standard caching mechanism offered by Grails.

The app is bootstrapped with a week of events.
An event has a LocalDate and a string description, the cache is keyed on the date and it's also a unique constraint.

Click a link on the list view for the 1st time & it's added to the cache & fetched from the cache on subsequent hits.

Click the view cache button to retrieve the contents of the cache.
Clear the cache with the Clear cache button.

One thing that doesn't work well is violating the unique constraint, the constraint logic works but it screws with
the cache & clicking to view the event that's clearly still in the database is not fetched from the cache.

You have to clear the cache to get things working again for this event.