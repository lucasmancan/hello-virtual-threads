import scala.concurrent.duration._

import scala.util.Random

import util.Try

import io.gatling.commons.validation._
import io.gatling.core.session.Session
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class VirtualThreadsSimulation
  extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:9999")
    .userAgentHeader("Agente do Caos - 2024/Q1")

  val delay3s = scenario("delay3s")
    .exec {s =>
      s
    }
    .exec(
      http("delay")
        .get(s => s"/httpbin/block/sync/3")
        .check(
          status.in(200, 422),
          status.saveAs("httpStatus")
        )
    )

  setUp(
    delay3s.inject(
      rampUsersPerSec(1).to(220).during(2.minutes),
      constantUsersPerSec(220).during(2.minutes)
    )
  ).protocols(httpProtocol)
}