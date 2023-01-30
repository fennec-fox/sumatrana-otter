package io.mustelidae.otter.sumatrana.api.domain.api

import io.mustelidae.otter.sumatrana.api.config.FlowTestSupport
import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryResources
import io.mustelidae.otter.sumatrana.utils.fromJson
import org.junit.jupiter.api.Test

class SentryForwardControllerTest: FlowTestSupport() {

    @Test
    fun sentryToSlackNodeFormat() {
        val sentryForwardControllerFlow = SentryForwardControllerFlow(mockMvc)
        val type = "error"
        val payload = jsonOfNodeFormat.fromJson<SentryResources.Payload>()
        // applaction.yml
        val key = "STSM1"

        sentryForwardControllerFlow.sentryToSlack(type, key, payload)
    }


    @Test
    fun sentryToSlackJavaFormat() {
        val sentryForwardControllerFlow = SentryForwardControllerFlow(mockMvc)
        val type = "error"
        val payload = jsonOfJavaFormat.fromJson<SentryResources.Payload>()
        // applaction.yml app.tunneling.rule.sentry-to-slack-mappings.key
        val key = "STSM2"
        sentryForwardControllerFlow.sentryToSlack(type, key, payload)
    }

    val jsonOfNodeFormat = """
        {
          "action": "triggered",
          "actor": {
            "id": "sentry",
            "name": "Sentry",
            "type": "application"
          },
          "data": {
            "event": {
              "_ref": 1,
              "_ref_version": 2,
              "contexts": {
                "browser": {
                  "name": "Chrome",
                  "type": "browser",
                  "version": "75.0.3770"
                },
                "os": {
                  "name": "Mac OS X",
                  "type": "os",
                  "version": "10.14.0"
                }
              },
              "culprit": "?(<anonymous>)",
              "datetime": "2019-08-19T21:06:17.677000Z",
              "dist": null,
              "event_id": "e4874d664c3540c1a32eab185f12c5ab",
              "exception": {
                "values": [
                  {
                    "mechanism": {
                      "data": {
                        "message": "heck is not defined",
                        "mode": "stack",
                        "name": "ReferenceError"
                      },
                      "description": null,
                      "handled": false,
                      "help_link": null,
                      "meta": null,
                      "synthetic": null,
                      "type": "onerror"
                    },
                    "stacktrace": {
                      "frames": [
                        {
                          "abs_path": "https://static.jsbin.com/js/prod/runner-4.1.7.min.js",
                          "colno": 10866,
                          "context_line": "{snip} e(a.old),a.active=b,e(a.target,b),setTimeout(function(){c&&c();for(var b,d=a.target.getElementsByTagName(\"iframe\"),e=d.length,f=0,g=a.active {snip}",
                          "data": {
                            "orig_in_app": 1
                          },
                          "errors": null,
                          "filename": "/js/prod/runner-4.1.7.min.js",
                          "function": null,
                          "image_addr": null,
                          "in_app": false,
                          "instruction_addr": null,
                          "lineno": 1,
                          "module": "prod/runner-4.1.7",
                          "package": null,
                          "platform": null,
                          "post_context": null,
                          "pre_context": null,
                          "raw_function": null,
                          "symbol": null,
                          "symbol_addr": null,
                          "trust": null,
                          "vars": null
                        },
                        {
                          "abs_path": "https://static.jsbin.com/js/prod/runner-4.1.7.min.js",
                          "colno": 13924,
                          "context_line": "{snip} e){i._raw(\"error\",e&&e.stack?e.stack:a+\" (line \"+c+\")\")},c.write(f),c.close(),b.postMessage(\"complete\"),k.wrap(e,a.options)})},b[\"console:ru {snip}",
                          "data": {
                            "orig_in_app": 1
                          },
                          "errors": null,
                          "filename": "/js/prod/runner-4.1.7.min.js",
                          "function": null,
                          "image_addr": null,
                          "in_app": false,
                          "instruction_addr": null,
                          "lineno": 1,
                          "module": "prod/runner-4.1.7",
                          "package": null,
                          "platform": null,
                          "post_context": null,
                          "pre_context": null,
                          "raw_function": null,
                          "symbol": null,
                          "symbol_addr": null,
                          "trust": null,
                          "vars": null
                        },
                        {
                          "abs_path": "<anonymous>",
                          "colno": 5,
                          "context_line": null,
                          "data": {
                            "orig_in_app": 1
                          },
                          "errors": null,
                          "filename": "<anonymous>",
                          "function": null,
                          "image_addr": null,
                          "in_app": false,
                          "instruction_addr": null,
                          "lineno": 3,
                          "module": null,
                          "package": null,
                          "platform": null,
                          "post_context": null,
                          "pre_context": null,
                          "raw_function": null,
                          "symbol": null,
                          "symbol_addr": null,
                          "trust": null,
                          "vars": null
                        }
                      ]
                    },
                    "type": "ReferenceError",
                    "value": "heck is not defined"
                  }
                ]
              },
              "fingerprint": ["{{ default }}"],
              "grouping_config": {
                "enhancements": "eJybzDhxY05qemJypZWRgaGlroGxrqHRBABbEwcC",
                "id": "legacy:2019-03-12"
              },
              "hashes": ["29f7ffc4903a8a990408b80a3b4c95a2"],
              "issue_url": "https://sentry.io/api/0/issues/1117540176/",
              "issue_id": "1117540176",
              "key_id": "667532",
              "level": "error",
              "location": "<anonymous>",
              "logger": "",
              "message": "",
              "metadata": {
                "filename": "<anonymous>",
                "type": "ReferenceError",
                "value": "heck is not defined"
              },
              "platform": "javascript",
              "project": 1,
              "received": 1566248777.677,
              "release": null,
              "request": {
                "cookies": null,
                "data": null,
                "env": null,
                "fragment": null,
                "headers": [
                  [
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36"
                  ]
                ],
                "inferred_content_type": null,
                "method": null,
                "query_string": [],
                "url": "https://null.jsbin.com/runner"
              },
              "sdk": {
                "integrations": [
                  "InboundFilters",
                  "FunctionToString",
                  "TryCatch",
                  "Breadcrumbs",
                  "GlobalHandlers",
                  "LinkedErrors",
                  "HttpContext"
                ],
                "name": "sentry.javascript.browser",
                "packages": [
                  {
                    "name": "npm:@sentry/browser",
                    "version": "5.5.0"
                  }
                ],
                "version": "5.5.0"
              },
              "tags": [
                ["browser", "Chrome 75.0.3770"],
                ["browser.name", "Chrome"],
                ["handled", "no"],
                ["level", "error"],
                ["mechanism", "onerror"],
                ["os", "Mac OS X 10.14.0"],
                ["os.name", "Mac OS X"],
                ["user", "ip:162.217.75.90"],
                ["url", "https://null.jsbin.com/runner"]
              ],
              "time_spent": null,
              "timestamp": 1566248777.677,
              "title": "ReferenceError: heck is not defined",
              "type": "error",
              "url": "https://sentry.io/api/0/projects/test-org/front-end/events/e4874d664c3540c1a32eab185f12c5ab/",
              "user": {
                "ip_address": "162.218.85.90"
              },
              "version": "7",
              "web_url": "https://sentry.io/organizations/test-org/issues/1117540176/events/e4874d664c3540c1a32eab185f12c5ab/"
            },
            "triggered_rule": "Very Important Alert Rule!",
            "issue_alert": {
              "title": "Very Important Alert Rule!",
              "settings": [
                {
                  "name": "channel",
                  "value": "#general"
                }
              ]
            }
          },
          "installation": {
            "uuid": "a8e5d37a-696c-4c54-adb5-b3f28d64c7de"
          }
        }
    """.trimIndent() 
    
    val jsonOfJavaFormat = """
        {
          "action":"created",
          "installation":{
            "uuid":"c33d6729-82cc-4827-bb90-689092575864"
          },
          "data":{
            "error":{
              "event_id":"5b073d7f582e40968d2401cf69251e10",
              "project":9,
              "release":null,
              "dist":null,
              "platform":"java",
              "message":"",
              "datetime":"2023-01-26T07:32:47.568000Z",
              "tags":[
                [
                  "environment",
                  "local"
                ],
                [
                  "level",
                  "fatal"
                ],
                [
                  "runtime",
                  "Eclipse Adoptium 11.0.17"
                ],
                [
                  "runtime.name",
                  "Eclipse Adoptium"
                ],
                [
                  "server_name",
                  "sumatrana-otter-api-deployment-8647694f86-zngb9"
                ],
                [
                  "transaction",
                  "POST /v1/sample-error/{productCode}/topic/{topicId}"
                ],
                [
                  "url",
                  "http://local.sumatrana.io/v1/sample-error/Slack/topic/temp"
                ]
              ],
              "_metrics":{
                "bytes.ingested.event":41730,
                "bytes.stored.event":111038
              },
              "_ref":9,
              "_ref_version":2,
              "breadcrumbs":{
                "values":[
                  {
                    "timestamp":1674718367.567,
                    "type":"http",
                    "category":"http",
                    "level":"info",
                    "data":{
                      "method":"POST",
                      "url":"/v1/sample-error/Slack/topic/temp"
                    }
                  }
                ]
              },
              "contexts":{
                "runtime":{
                  "name":"Eclipse Adoptium",
                  "version":"11.0.17",
                  "type":"runtime"
                }
              },
              "culprit":"POST /v1/sample-error/{productCode}/topic/{topicId}",
              "environment":"local",
              "exception":{
                "values":[
                  {
                    "type":"IllegalArgumentException",
                    "value":"No enum constant io.mustelidae.otter.sumatrana.common.ProductCode.Slack",
                    "module":"java.lang",
                    "stacktrace":{
                      "frames":[
                        {
                          "function":"run",
                          "module":"java.lang.Thread",
                          "filename":"Thread.java",
                          "abs_path":"Thread.java",
                          "lineno":829,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"org.xnio.XnioWorker|WorkerThreadFactory|{'|'}1|{'|'}1",
                          "filename":"XnioWorker.java",
                          "abs_path":"XnioWorker.java",
                          "lineno":1282,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"org.jboss.threads.EnhancedQueueExecutor|ThreadBody",
                          "filename":"EnhancedQueueExecutor.java",
                          "abs_path":"EnhancedQueueExecutor.java",
                          "lineno":1449,
                          "in_app":false
                        },
                        {
                          "function":"doRunTask",
                          "module":"org.jboss.threads.EnhancedQueueExecutor|ThreadBody",
                          "filename":"EnhancedQueueExecutor.java",
                          "abs_path":"EnhancedQueueExecutor.java",
                          "lineno":1558,
                          "in_app":false
                        },
                        {
                          "function":"safeRun",
                          "module":"org.jboss.threads.EnhancedQueueExecutor",
                          "filename":"EnhancedQueueExecutor.java",
                          "abs_path":"EnhancedQueueExecutor.java",
                          "lineno":2019,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"org.jboss.threads.ContextClassLoaderSavingRunnable",
                          "filename":"ContextClassLoaderSavingRunnable.java",
                          "abs_path":"ContextClassLoaderSavingRunnable.java",
                          "lineno":35,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"io.undertow.server.HttpServerExchange|{'|'}1",
                          "filename":"HttpServerExchange.java",
                          "abs_path":"HttpServerExchange.java",
                          "lineno":852,
                          "in_app":false
                        },
                        {
                          "function":"executeRootHandler",
                          "module":"io.undertow.server.Connectors",
                          "filename":"Connectors.java",
                          "abs_path":"Connectors.java",
                          "lineno":387,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler|{'|'}1",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":100,
                          "in_app":false
                        },
                        {
                          "function":"access|{'|'}000",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":79,
                          "in_app":false
                        },
                        {
                          "function":"dispatchRequest",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":255,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.core.ContextClassLoaderSetupAction|{'|'}1",
                          "filename":"ContextClassLoaderSetupAction.java",
                          "abs_path":"ContextClassLoaderSetupAction.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.core.ServletRequestContextThreadSetupAction|{'|'}1",
                          "filename":"ServletRequestContextThreadSetupAction.java",
                          "abs_path":"ServletRequestContextThreadSetupAction.java",
                          "lineno":48,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler|{'|'}2",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler|{'|'}2",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":134,
                          "in_app":false
                        },
                        {
                          "function":"access|{'|'}100",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":79,
                          "in_app":false
                        },
                        {
                          "function":"handleFirstRequest",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":275,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.server.handlers.PredicateHandler",
                          "filename":"PredicateHandler.java",
                          "abs_path":"PredicateHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.SendErrorPageHandler",
                          "filename":"SendErrorPageHandler.java",
                          "abs_path":"SendErrorPageHandler.java",
                          "lineno":52,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.server.handlers.PredicateHandler",
                          "filename":"PredicateHandler.java",
                          "abs_path":"PredicateHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.security.handlers.AbstractSecurityContextAssociationHandler",
                          "filename":"AbstractSecurityContextAssociationHandler.java",
                          "abs_path":"AbstractSecurityContextAssociationHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler",
                          "filename":"CachedAuthenticatedSessionHandler.java",
                          "abs_path":"CachedAuthenticatedSessionHandler.java",
                          "lineno":77,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.security.handlers.AuthenticationMechanismsHandler",
                          "filename":"AuthenticationMechanismsHandler.java",
                          "abs_path":"AuthenticationMechanismsHandler.java",
                          "lineno":60,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler",
                          "filename":"ServletConfidentialityConstraintHandler.java",
                          "abs_path":"ServletConfidentialityConstraintHandler.java",
                          "lineno":64,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.security.handlers.AbstractConfidentialityHandler",
                          "filename":"AbstractConfidentialityHandler.java",
                          "abs_path":"AbstractConfidentialityHandler.java",
                          "lineno":46,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.server.handlers.PredicateHandler",
                          "filename":"PredicateHandler.java",
                          "abs_path":"PredicateHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler",
                          "filename":"ServletAuthenticationCallHandler.java",
                          "abs_path":"ServletAuthenticationCallHandler.java",
                          "lineno":57,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.SSLInformationAssociationHandler",
                          "filename":"SSLInformationAssociationHandler.java",
                          "abs_path":"SSLInformationAssociationHandler.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.RedirectDirHandler",
                          "filename":"RedirectDirHandler.java",
                          "abs_path":"RedirectDirHandler.java",
                          "lineno":68,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletDispatchingHandler",
                          "filename":"ServletDispatchingHandler.java",
                          "abs_path":"ServletDispatchingHandler.java",
                          "lineno":36,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletChain|{'|'}1",
                          "filename":"ServletChain.java",
                          "abs_path":"ServletChain.java",
                          "lineno":68,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.ServletSecurityRoleHandler",
                          "filename":"ServletSecurityRoleHandler.java",
                          "abs_path":"ServletSecurityRoleHandler.java",
                          "lineno":62,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.FilterHandler",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":84,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilterInternal",
                          "module":"org.springframework.web.filter.CharacterEncodingFilter",
                          "filename":"CharacterEncodingFilter.java",
                          "abs_path":"CharacterEncodingFilter.java",
                          "lineno":201,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilterInternal",
                          "module":"org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter",
                          "filename":"WebMvcMetricsFilter.java",
                          "abs_path":"WebMvcMetricsFilter.java",
                          "lineno":96,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilterInternal",
                          "module":"io.mustelidae.otter.sumatrana.api.config.RequestResponseLogFilter",
                          "filename":"RequestResponseLogFilter.kt",
                          "abs_path":"RequestResponseLogFilter.kt",
                          "lineno":48,
                          "in_app":true
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":129,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletHandler",
                          "filename":"ServletHandler.java",
                          "abs_path":"ServletHandler.java",
                          "lineno":74,
                          "in_app":false
                        },
                        {
                          "function":"service",
                          "module":"javax.servlet.http.HttpServlet",
                          "filename":"HttpServlet.java",
                          "abs_path":"HttpServlet.java",
                          "lineno":584,
                          "in_app":false
                        },
                        {
                          "function":"service",
                          "module":"org.springframework.web.servlet.FrameworkServlet",
                          "filename":"FrameworkServlet.java",
                          "abs_path":"FrameworkServlet.java",
                          "lineno":883,
                          "in_app":false
                        },
                        {
                          "function":"service",
                          "module":"javax.servlet.http.HttpServlet",
                          "filename":"HttpServlet.java",
                          "abs_path":"HttpServlet.java",
                          "lineno":517,
                          "in_app":false
                        },
                        {
                          "function":"doPost",
                          "module":"org.springframework.web.servlet.FrameworkServlet",
                          "filename":"FrameworkServlet.java",
                          "abs_path":"FrameworkServlet.java",
                          "lineno":909,
                          "in_app":false
                        },
                        {
                          "function":"processRequest",
                          "module":"org.springframework.web.servlet.FrameworkServlet",
                          "filename":"FrameworkServlet.java",
                          "abs_path":"FrameworkServlet.java",
                          "lineno":1006,
                          "in_app":false
                        },
                        {
                          "function":"doService",
                          "module":"org.springframework.web.servlet.DispatcherServlet",
                          "filename":"DispatcherServlet.java",
                          "abs_path":"DispatcherServlet.java",
                          "lineno":964,
                          "in_app":false
                        },
                        {
                          "function":"doDispatch",
                          "module":"org.springframework.web.servlet.DispatcherServlet",
                          "filename":"DispatcherServlet.java",
                          "abs_path":"DispatcherServlet.java",
                          "lineno":1071,
                          "in_app":false
                        },
                        {
                          "function":"handle",
                          "module":"org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter",
                          "filename":"AbstractHandlerMethodAdapter.java",
                          "abs_path":"AbstractHandlerMethodAdapter.java",
                          "lineno":87,
                          "in_app":false
                        },
                        {
                          "function":"handleInternal",
                          "module":"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter",
                          "filename":"RequestMappingHandlerAdapter.java",
                          "abs_path":"RequestMappingHandlerAdapter.java",
                          "lineno":808,
                          "in_app":false
                        },
                        {
                          "function":"invokeHandlerMethod",
                          "module":"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter",
                          "filename":"RequestMappingHandlerAdapter.java",
                          "abs_path":"RequestMappingHandlerAdapter.java",
                          "lineno":895,
                          "in_app":false
                        },
                        {
                          "function":"invokeAndHandle",
                          "module":"org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod",
                          "filename":"ServletInvocableHandlerMethod.java",
                          "abs_path":"ServletInvocableHandlerMethod.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"invokeForRequest",
                          "module":"org.springframework.web.method.support.InvocableHandlerMethod",
                          "filename":"InvocableHandlerMethod.java",
                          "abs_path":"InvocableHandlerMethod.java",
                          "lineno":146,
                          "in_app":false
                        },
                        {
                          "function":"getMethodArgumentValues",
                          "module":"org.springframework.web.method.support.InvocableHandlerMethod",
                          "filename":"InvocableHandlerMethod.java",
                          "abs_path":"InvocableHandlerMethod.java",
                          "lineno":179,
                          "in_app":false
                        },
                        {
                          "function":"resolveArgument",
                          "module":"org.springframework.web.method.support.HandlerMethodArgumentResolverComposite",
                          "filename":"HandlerMethodArgumentResolverComposite.java",
                          "abs_path":"HandlerMethodArgumentResolverComposite.java",
                          "lineno":122,
                          "in_app":false
                        },
                        {
                          "function":"resolveArgument",
                          "module":"org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver",
                          "filename":"AbstractNamedValueMethodArgumentResolver.java",
                          "abs_path":"AbstractNamedValueMethodArgumentResolver.java",
                          "lineno":125,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.validation.DataBinder",
                          "filename":"DataBinder.java",
                          "abs_path":"DataBinder.java",
                          "lineno":729,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.beans.TypeConverterSupport",
                          "filename":"TypeConverterSupport.java",
                          "abs_path":"TypeConverterSupport.java",
                          "lineno":53,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.beans.TypeConverterSupport",
                          "filename":"TypeConverterSupport.java",
                          "abs_path":"TypeConverterSupport.java",
                          "lineno":73,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.beans.TypeConverterDelegate",
                          "filename":"TypeConverterDelegate.java",
                          "abs_path":"TypeConverterDelegate.java",
                          "lineno":129,
                          "in_app":false
                        },
                        {
                          "function":"convert",
                          "module":"org.springframework.core.convert.support.GenericConversionService",
                          "filename":"GenericConversionService.java",
                          "abs_path":"GenericConversionService.java",
                          "lineno":192,
                          "in_app":false
                        },
                        {
                          "function":"invokeConverter",
                          "module":"org.springframework.core.convert.support.ConversionUtils",
                          "filename":"ConversionUtils.java",
                          "abs_path":"ConversionUtils.java",
                          "lineno":41,
                          "in_app":false
                        },
                        {
                          "function":"convert",
                          "module":"org.springframework.core.convert.support.GenericConversionService|ConverterFactoryAdapter",
                          "filename":"GenericConversionService.java",
                          "abs_path":"GenericConversionService.java",
                          "lineno":437,
                          "in_app":false
                        },
                        {
                          "function":"convert",
                          "module":"org.springframework.core.convert.support.StringToEnumConverterFactory|StringToEnum",
                          "filename":"StringToEnumConverterFactory.java",
                          "abs_path":"StringToEnumConverterFactory.java",
                          "lineno":39,
                          "in_app":false
                        },
                        {
                          "function":"convert",
                          "module":"org.springframework.core.convert.support.StringToEnumConverterFactory|StringToEnum",
                          "filename":"StringToEnumConverterFactory.java",
                          "abs_path":"StringToEnumConverterFactory.java",
                          "lineno":54,
                          "in_app":false
                        },
                        {
                          "function":"valueOf",
                          "module":"java.lang.Enum",
                          "filename":"Enum.java",
                          "abs_path":"Enum.java",
                          "lineno":240,
                          "in_app":false
                        }
                      ]
                    },
                    "thread_id":2997
                  },
                  {
                    "type":"ConversionFailedException",
                    "value":"Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.PathVariable io.mustelidae.otter.sumatrana.common.ProductCode] for value 'Slack'; nested exception is java.lang.IllegalArgumentException: No enum constant io.mustelidae.otter.sumatrana.common.ProductCode.Slack",
                    "module":"org.springframework.core.convert",
                    "stacktrace":{
                      "frames":[
                        {
                          "function":"run",
                          "module":"java.lang.Thread",
                          "filename":"Thread.java",
                          "abs_path":"Thread.java",
                          "lineno":829,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"org.xnio.XnioWorker|WorkerThreadFactory|{'|'}1|{'|'}1",
                          "filename":"XnioWorker.java",
                          "abs_path":"XnioWorker.java",
                          "lineno":1282,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"org.jboss.threads.EnhancedQueueExecutor|ThreadBody",
                          "filename":"EnhancedQueueExecutor.java",
                          "abs_path":"EnhancedQueueExecutor.java",
                          "lineno":1449,
                          "in_app":false
                        },
                        {
                          "function":"doRunTask",
                          "module":"org.jboss.threads.EnhancedQueueExecutor|ThreadBody",
                          "filename":"EnhancedQueueExecutor.java",
                          "abs_path":"EnhancedQueueExecutor.java",
                          "lineno":1558,
                          "in_app":false
                        },
                        {
                          "function":"safeRun",
                          "module":"org.jboss.threads.EnhancedQueueExecutor",
                          "filename":"EnhancedQueueExecutor.java",
                          "abs_path":"EnhancedQueueExecutor.java",
                          "lineno":2019,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"org.jboss.threads.ContextClassLoaderSavingRunnable",
                          "filename":"ContextClassLoaderSavingRunnable.java",
                          "abs_path":"ContextClassLoaderSavingRunnable.java",
                          "lineno":35,
                          "in_app":false
                        },
                        {
                          "function":"run",
                          "module":"io.undertow.server.HttpServerExchange|{'|'}1",
                          "filename":"HttpServerExchange.java",
                          "abs_path":"HttpServerExchange.java",
                          "lineno":852,
                          "in_app":false
                        },
                        {
                          "function":"executeRootHandler",
                          "module":"io.undertow.server.Connectors",
                          "filename":"Connectors.java",
                          "abs_path":"Connectors.java",
                          "lineno":387,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler|{'|'}1",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":100,
                          "in_app":false
                        },
                        {
                          "function":"access|{'|'}000",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":79,
                          "in_app":false
                        },
                        {
                          "function":"dispatchRequest",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":255,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.core.ContextClassLoaderSetupAction|{'|'}1",
                          "filename":"ContextClassLoaderSetupAction.java",
                          "abs_path":"ContextClassLoaderSetupAction.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.core.ServletRequestContextThreadSetupAction|{'|'}1",
                          "filename":"ServletRequestContextThreadSetupAction.java",
                          "abs_path":"ServletRequestContextThreadSetupAction.java",
                          "lineno":48,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler|{'|'}2",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"call",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler|{'|'}2",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":134,
                          "in_app":false
                        },
                        {
                          "function":"access|{'|'}100",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":79,
                          "in_app":false
                        },
                        {
                          "function":"handleFirstRequest",
                          "module":"io.undertow.servlet.handlers.ServletInitialHandler",
                          "filename":"ServletInitialHandler.java",
                          "abs_path":"ServletInitialHandler.java",
                          "lineno":275,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.server.handlers.PredicateHandler",
                          "filename":"PredicateHandler.java",
                          "abs_path":"PredicateHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.SendErrorPageHandler",
                          "filename":"SendErrorPageHandler.java",
                          "abs_path":"SendErrorPageHandler.java",
                          "lineno":52,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.server.handlers.PredicateHandler",
                          "filename":"PredicateHandler.java",
                          "abs_path":"PredicateHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.security.handlers.AbstractSecurityContextAssociationHandler",
                          "filename":"AbstractSecurityContextAssociationHandler.java",
                          "abs_path":"AbstractSecurityContextAssociationHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler",
                          "filename":"CachedAuthenticatedSessionHandler.java",
                          "abs_path":"CachedAuthenticatedSessionHandler.java",
                          "lineno":77,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.security.handlers.AuthenticationMechanismsHandler",
                          "filename":"AuthenticationMechanismsHandler.java",
                          "abs_path":"AuthenticationMechanismsHandler.java",
                          "lineno":60,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler",
                          "filename":"ServletConfidentialityConstraintHandler.java",
                          "abs_path":"ServletConfidentialityConstraintHandler.java",
                          "lineno":64,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.security.handlers.AbstractConfidentialityHandler",
                          "filename":"AbstractConfidentialityHandler.java",
                          "abs_path":"AbstractConfidentialityHandler.java",
                          "lineno":46,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.server.handlers.PredicateHandler",
                          "filename":"PredicateHandler.java",
                          "abs_path":"PredicateHandler.java",
                          "lineno":43,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler",
                          "filename":"ServletAuthenticationCallHandler.java",
                          "abs_path":"ServletAuthenticationCallHandler.java",
                          "lineno":57,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.SSLInformationAssociationHandler",
                          "filename":"SSLInformationAssociationHandler.java",
                          "abs_path":"SSLInformationAssociationHandler.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.RedirectDirHandler",
                          "filename":"RedirectDirHandler.java",
                          "abs_path":"RedirectDirHandler.java",
                          "lineno":68,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletDispatchingHandler",
                          "filename":"ServletDispatchingHandler.java",
                          "abs_path":"ServletDispatchingHandler.java",
                          "lineno":36,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletChain|{'|'}1",
                          "filename":"ServletChain.java",
                          "abs_path":"ServletChain.java",
                          "lineno":68,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.security.ServletSecurityRoleHandler",
                          "filename":"ServletSecurityRoleHandler.java",
                          "abs_path":"ServletSecurityRoleHandler.java",
                          "lineno":62,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.FilterHandler",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":84,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilterInternal",
                          "module":"org.springframework.web.filter.CharacterEncodingFilter",
                          "filename":"CharacterEncodingFilter.java",
                          "abs_path":"CharacterEncodingFilter.java",
                          "lineno":201,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilterInternal",
                          "module":"org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter",
                          "filename":"WebMvcMetricsFilter.java",
                          "abs_path":"WebMvcMetricsFilter.java",
                          "lineno":96,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilterInternal",
                          "module":"io.mustelidae.otter.sumatrana.api.config.RequestResponseLogFilter",
                          "filename":"RequestResponseLogFilter.kt",
                          "abs_path":"RequestResponseLogFilter.kt",
                          "lineno":48,
                          "in_app":true
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":131,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.core.ManagedFilter",
                          "filename":"ManagedFilter.java",
                          "abs_path":"ManagedFilter.java",
                          "lineno":61,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"org.springframework.web.filter.OncePerRequestFilter",
                          "filename":"OncePerRequestFilter.java",
                          "abs_path":"OncePerRequestFilter.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"doFilter",
                          "module":"io.undertow.servlet.handlers.FilterHandler|FilterChainImpl",
                          "filename":"FilterHandler.java",
                          "abs_path":"FilterHandler.java",
                          "lineno":129,
                          "in_app":false
                        },
                        {
                          "function":"handleRequest",
                          "module":"io.undertow.servlet.handlers.ServletHandler",
                          "filename":"ServletHandler.java",
                          "abs_path":"ServletHandler.java",
                          "lineno":74,
                          "in_app":false
                        },
                        {
                          "function":"service",
                          "module":"javax.servlet.http.HttpServlet",
                          "filename":"HttpServlet.java",
                          "abs_path":"HttpServlet.java",
                          "lineno":584,
                          "in_app":false
                        },
                        {
                          "function":"service",
                          "module":"org.springframework.web.servlet.FrameworkServlet",
                          "filename":"FrameworkServlet.java",
                          "abs_path":"FrameworkServlet.java",
                          "lineno":883,
                          "in_app":false
                        },
                        {
                          "function":"service",
                          "module":"javax.servlet.http.HttpServlet",
                          "filename":"HttpServlet.java",
                          "abs_path":"HttpServlet.java",
                          "lineno":517,
                          "in_app":false
                        },
                        {
                          "function":"doPost",
                          "module":"org.springframework.web.servlet.FrameworkServlet",
                          "filename":"FrameworkServlet.java",
                          "abs_path":"FrameworkServlet.java",
                          "lineno":909,
                          "in_app":false
                        },
                        {
                          "function":"processRequest",
                          "module":"org.springframework.web.servlet.FrameworkServlet",
                          "filename":"FrameworkServlet.java",
                          "abs_path":"FrameworkServlet.java",
                          "lineno":1006,
                          "in_app":false
                        },
                        {
                          "function":"doService",
                          "module":"org.springframework.web.servlet.DispatcherServlet",
                          "filename":"DispatcherServlet.java",
                          "abs_path":"DispatcherServlet.java",
                          "lineno":964,
                          "in_app":false
                        },
                        {
                          "function":"doDispatch",
                          "module":"org.springframework.web.servlet.DispatcherServlet",
                          "filename":"DispatcherServlet.java",
                          "abs_path":"DispatcherServlet.java",
                          "lineno":1071,
                          "in_app":false
                        },
                        {
                          "function":"handle",
                          "module":"org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter",
                          "filename":"AbstractHandlerMethodAdapter.java",
                          "abs_path":"AbstractHandlerMethodAdapter.java",
                          "lineno":87,
                          "in_app":false
                        },
                        {
                          "function":"handleInternal",
                          "module":"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter",
                          "filename":"RequestMappingHandlerAdapter.java",
                          "abs_path":"RequestMappingHandlerAdapter.java",
                          "lineno":808,
                          "in_app":false
                        },
                        {
                          "function":"invokeHandlerMethod",
                          "module":"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter",
                          "filename":"RequestMappingHandlerAdapter.java",
                          "abs_path":"RequestMappingHandlerAdapter.java",
                          "lineno":895,
                          "in_app":false
                        },
                        {
                          "function":"invokeAndHandle",
                          "module":"org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod",
                          "filename":"ServletInvocableHandlerMethod.java",
                          "abs_path":"ServletInvocableHandlerMethod.java",
                          "lineno":117,
                          "in_app":false
                        },
                        {
                          "function":"invokeForRequest",
                          "module":"org.springframework.web.method.support.InvocableHandlerMethod",
                          "filename":"InvocableHandlerMethod.java",
                          "abs_path":"InvocableHandlerMethod.java",
                          "lineno":146,
                          "in_app":false
                        },
                        {
                          "function":"getMethodArgumentValues",
                          "module":"org.springframework.web.method.support.InvocableHandlerMethod",
                          "filename":"InvocableHandlerMethod.java",
                          "abs_path":"InvocableHandlerMethod.java",
                          "lineno":179,
                          "in_app":false
                        },
                        {
                          "function":"resolveArgument",
                          "module":"org.springframework.web.method.support.HandlerMethodArgumentResolverComposite",
                          "filename":"HandlerMethodArgumentResolverComposite.java",
                          "abs_path":"HandlerMethodArgumentResolverComposite.java",
                          "lineno":122,
                          "in_app":false
                        },
                        {
                          "function":"resolveArgument",
                          "module":"org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver",
                          "filename":"AbstractNamedValueMethodArgumentResolver.java",
                          "abs_path":"AbstractNamedValueMethodArgumentResolver.java",
                          "lineno":125,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.validation.DataBinder",
                          "filename":"DataBinder.java",
                          "abs_path":"DataBinder.java",
                          "lineno":729,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.beans.TypeConverterSupport",
                          "filename":"TypeConverterSupport.java",
                          "abs_path":"TypeConverterSupport.java",
                          "lineno":53,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.beans.TypeConverterSupport",
                          "filename":"TypeConverterSupport.java",
                          "abs_path":"TypeConverterSupport.java",
                          "lineno":73,
                          "in_app":false
                        },
                        {
                          "function":"convertIfNecessary",
                          "module":"org.springframework.beans.TypeConverterDelegate",
                          "filename":"TypeConverterDelegate.java",
                          "abs_path":"TypeConverterDelegate.java",
                          "lineno":129,
                          "in_app":false
                        },
                        {
                          "function":"convert",
                          "module":"org.springframework.core.convert.support.GenericConversionService",
                          "filename":"GenericConversionService.java",
                          "abs_path":"GenericConversionService.java",
                          "lineno":192,
                          "in_app":false
                        },
                        {
                          "function":"invokeConverter",
                          "module":"org.springframework.core.convert.support.ConversionUtils",
                          "filename":"ConversionUtils.java",
                          "abs_path":"ConversionUtils.java",
                          "lineno":47,
                          "in_app":false
                        }
                      ]
                    },
                    "thread_id":2997
                  },
                  {
                    "type":"MethodArgumentTypeMismatchException",
                    "value":"Failed to convert value of type 'java.lang.String' to required type 'io.mustelidae.otter.sumatrana.common.ProductCode'; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.PathVariable io.mustelidae.otter.sumatrana.common.ProductCode] for value 'Slack'; nested exception is java.lang.IllegalArgumentException: No enum constant io.mustelidae.otter.sumatrana.common.ProductCode.Slack",
                    "module":"org.springframework.web.method.annotation",
                    "stacktrace":{
                      "frames":[
                        {
                          "function":"run",
                          "module":"java.lang.Thread",
                          "filename":"Thread.java",
                          "abs_path":"Thread.java",
                          "lineno":829
                        }
                      ]
                    }
                  }
                ]
              }
            }
          }
        }
    """.trimIndent()
}