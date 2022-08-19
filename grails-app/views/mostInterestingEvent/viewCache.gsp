<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title></title>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#list-mostInterestingEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="index"><g:message code="default.list.label" args="['MostInterestingEvent']" /></g:link></li>
                    <li><g:link class="delete" action="clearCache">Clear cache</g:link></li>
                </ul>
            </div>
        </section>
        <section class="row">
            <div class="col-12 content scaffold-list" role="main">
                <h1>Cache</h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <table class="table-bordered table">
                    <tr>
                        <th>Key</th>
                        <th>Value</th>
                    </tr>
                    <g:each in="${cache.getAllKeys()}" var="aKey">
                        <tr>
                            <td>
                                ${aKey.simpleKey}
                            </td>
                            <td>${cache.get(aKey).get()}</td>
                        </tr>
                    </g:each>
                </table>
            </div>
        </section>
    </div>
</div>
</body>
</html>