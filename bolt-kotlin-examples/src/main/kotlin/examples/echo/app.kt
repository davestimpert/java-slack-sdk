package examples.echo

import com.slack.api.bolt.App
import com.slack.api.bolt.jetty.SlackAppServer

fun main() {

    // export SLACK_BOT_TOKEN=xoxb-***
    // export SLACK_SIGNING_SECRET=123abc***
    val app = App()

    app.command("/echo") { req, ctx ->
        val text = "You said ${req.payload.text} at <#${req.payload.channelId}|${req.payload.channelName}>"
        val res = ctx.respond { it.text(text) }
        ctx.logger.info("respond result - {}", res)
        ctx.ack()
    }
    val server = SlackAppServer(app)
    server.start()
}