package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "should return single Player for Player name Sushant"

    request {
        urlPath( "/api/fetch-single-players")
        {
            queryParameters {
                parameter("playerName", "TSachin")
            }
        }
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                file("files/single_player.json")
        )
    }
}