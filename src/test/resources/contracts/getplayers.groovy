package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "should return All Player List"

    request {
        url "/api/fetch-all-players"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                file("files/allPlayers.json")
        )
    }
}